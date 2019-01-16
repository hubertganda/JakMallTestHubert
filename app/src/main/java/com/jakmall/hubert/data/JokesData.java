package com.jakmall.hubert.data;

import java.util.ArrayList;

public class JokesData {

    String id;
    String joke;
    ArrayList<String> categories;

    public JokesData(String id, String joke) {
        this.id = id;
        this.joke = joke;
        this.categories = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String getJoke() {
        return this.joke;
    }

    public void addCategories(String data) {
        categories.add(data);
    }
}
