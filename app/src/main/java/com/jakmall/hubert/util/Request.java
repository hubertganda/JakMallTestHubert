package com.jakmall.hubert.util;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Request extends AsyncTask<Void, Void, String> {
    private String code;
    private String method;
    private String url;
    private String authorization;
    private Map<String, String> params;
    private Listener.HttpListener listener;

    public Request(
        String method,
        String url,
        String authorization,
        Map<String, String> params,
        Listener.HttpListener listener
    ) {
        this.method = method;
        this.url = url;
        this.authorization = authorization;
        this.params = params;
        this.listener = listener;
    }

    @Override
    public String doInBackground(Void... params) {
        try {
            if (this.method.equals("get")) {

                HttpRequest request = HttpRequest
                    .get(this.url, this.params, true)
                    .accept("application/json")
                    .authorization(this.authorization)
                    .contentType("application/json");

                this.code = String.valueOf(request.code());
                return request.body();

            } else if (this.method.equals("post")) {

                HttpRequest request = HttpRequest
                    .post(this.url)
                    .accept("application/json")
                    .authorization(this.authorization)
                    .contentType("application/json")
                    .form(this.params);

                this.code = String.valueOf(request.code());
                return request.body();

            } else {
                this.code = "503";
                return "Connection error.";
            }
        } catch (HttpRequest.HttpRequestException e) {
            e.printStackTrace();
            this.code = "503";
            return "Connection error.";
        }
    }

    @Override
    public void onPostExecute(String response) {
        if (this.code.equals("200")) {
            listener.onRequestSuccess(response);
        } else if (this.code.equals("503")) {
            JSONObject object = new JSONObject();

            try {
                object.put("message", response);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            listener.onRequestFailed(this.code, object.toString());
        } else {
            listener.onRequestFailed(this.code, response);
        }
    }
}
