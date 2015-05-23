package com.curson.application;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/4/21.
 */
public class ShowListViewActivity extends ActionBarActivity {

    private ArrayList<SaveBean> saveBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        android.widget.ListView listView = (android.widget.ListView) findViewById(R.id.listview);
        saveBeans = Hawk.get("saveBeans");
        listView.setAdapter(mAdapter);
    }

    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item, parent, false);
                holder = new ViewHolder();
                holder.question = (TextView) convertView.findViewById(R.id.question);
                holder.phone = (TextView) convertView.findViewById(R.id.phone);
                holder.email = (TextView) convertView.findViewById(R.id.email);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.question.setText(saveBeans.get(position).getQuestion());
            holder.phone.setText(saveBeans.get(position).getEmail());
            holder.email.setText(saveBeans.get(position).getPhone());

            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return saveBeans.size();
        }
    };

    private class ViewHolder {
        TextView question;
        TextView phone;
        TextView email;
    }
}
