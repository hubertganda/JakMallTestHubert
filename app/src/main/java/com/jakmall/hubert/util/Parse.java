package com.jakmall.hubert.util;

import com.jakmall.hubert.api.JokesApi;
import com.jakmall.hubert.data.JokesData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parse {

    public static boolean jokes(String data) {
        try {
            JokesApi.jokeList = new ArrayList<>();
            JSONObject json = new JSONObject(data);
            JSONArray value = json.getJSONArray("value");

            for (int i = 0; i < value.length(); i++) {
                JSONObject obj = value.getJSONObject(i);
                JSONArray arr = obj.getJSONArray("categories");
                String[] categories = new String[arr.length()];

                JokesData joke = new JokesData(
                    obj.getString("id"),
                    obj.getString("joke")
                );

                for (String s : categories) {
                    joke.addCategories(s);
                }

                JokesApi.jokeList.add(joke);
            }

            return true;
        } catch(JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
