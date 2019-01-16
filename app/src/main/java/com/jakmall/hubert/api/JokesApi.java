package com.jakmall.hubert.api;

import com.jakmall.hubert.data.JokesData;
import com.jakmall.hubert.util.Listener;
import com.jakmall.hubert.util.Parse;
import com.jakmall.hubert.util.Request;

import java.util.ArrayList;
import java.util.HashMap;

public class JokesApi {

    public static ArrayList<JokesData> jokeList;

    public static void getJokes(final Listener.RequestListener listener) {
        String url = "https://api.icndb.com/jokes/random/10-";
        HashMap<String, String> params = new HashMap<>();

        Request request = new Request(
            "get",
            url,
            "",
            params,
            new Listener.HttpListener() {
                @Override
                public void onRequestSuccess(String result) {
                    if (Parse.jokes(result)) {
                        listener.onRequestSuccess();
                    } else {
                        listener.onRequestFailed("json", "Parse failed.");
                    }
                }

                @Override
                public void onRequestFailed(String errCode, String errMsg) {
                    listener.onRequestFailed(errCode, errMsg);
                }
            }
        );
        request.execute();
    }
}
