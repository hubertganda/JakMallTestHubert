package com.jakmall.hubert.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jakmall.hubert.R;
import com.jakmall.hubert.data.JokesData;
import com.jakmall.hubert.util.Jhc;

import java.util.ArrayList;

public class JokesAdapter extends BaseAdapter {
    private Context context;
    ArrayList<JokesData> jokes;

    public JokesAdapter(Context context, ArrayList<JokesData> jokes) {
        this.context = context;
        this.jokes = jokes;
    }

    public int getCount() {
        return jokes.size();
    }

    public Object getItem(int position) {
        return jokes.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                R.layout.list_top_jokes,
                parent,
                false
            );
        }

        final JokesData data = jokes.get(position);

        TextView txt = convertView.findViewById(R.id.topjokes_txt_jokes);
        txt.setText(data.getJoke());
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jhc.createDialog((Activity) context, data.getJoke(), "ok");
            }
        });

        Button btn = convertView.findViewById(R.id.topjokes_btn_gotop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapData(position);
            }
        });

        if (position == 0) {
            btn.setVisibility(View.GONE);
        } else {
            Jhc.getTextView(convertView, R.id.topjokes_txt_ontop).setVisibility(View.GONE);
        }

        return convertView;
    }

    public void swapData(int position) {
        JokesData data = jokes.get(position);
        jokes.remove(position);
        jokes.add(0, data);
        notifyDataSetChanged();
    }
}
