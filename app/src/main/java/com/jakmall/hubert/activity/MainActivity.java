package com.jakmall.hubert.activity;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.jakmall.hubert.R;
import com.jakmall.hubert.adapter.JokesAdapter;
import com.jakmall.hubert.api.JokesApi;
import com.jakmall.hubert.data.JokesData;
import com.jakmall.hubert.util.Jhc;
import com.jakmall.hubert.util.Listener;

import java.util.ArrayList;
import java.util.LinkedList;

import static android.view.Window.FEATURE_NO_TITLE;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefresh;
    ArrayList<JokesData> listData;
    int shown = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listData = new ArrayList<>();
        getData();

        swipeRefresh = findViewById(R.id.main_swipe_refresh_layout);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupInitialData();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    void getData() {
        final Dialog loading = Jhc.createLoadingDialog(this);

        JokesApi.getJokes(new Listener.RequestListener() {
            @Override
            public void onRequestSuccess() {
                loading.dismiss();
                setupInitialData();
                Jhc.getView(self(), R.id.main_layout_topjokes).setVisibility(View.VISIBLE);
            }

            @Override
            public void onRequestFailed(String errCode, String errMsg) {
                loading.dismiss();
            }
        });
    }

    public void setupAdapter() {
        JokesAdapter adapter = new JokesAdapter(this, listData);
        ListView listview = findViewById(R.id.main_listview_jokes);
        listview.setAdapter(adapter);
    }

    public void setupInitialData() {
        shown = 3;
        listData.clear();

        for (int i = 0; i < shown; i++) {
            listData.add(JokesApi.jokeList.get(i));
        }

        setupAdapter();
        Jhc.getButton(self(), R.id.main_btn_add).setVisibility(View.VISIBLE);
    }

    public void addMoreData(View v) {
        if (shown < 5) {
            shown++;
            listData.add(JokesApi.jokeList.get(shown));
            setupAdapter();

            if (shown == 5) {
                v.setVisibility(View.GONE);
            }
        }
    }

    public Activity self() {
        return this;
    }
}
