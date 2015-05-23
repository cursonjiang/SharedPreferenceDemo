package com.curson.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private EditText id_question;
    private EditText id_phone;
    private EditText id_email;
    private Button id_post;
    private Button id_show;

    private ArrayList<SaveBean> saveBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Hawk.init(this, "save");
        id_question = (EditText) findViewById(R.id.id_question);
        id_phone = (EditText) findViewById(R.id.id_phone);
        id_post = (Button) findViewById(R.id.id_post);
        id_email = (EditText) findViewById(R.id.id_email);
        id_show = (Button) findViewById(R.id.id_show);

        id_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hawk.contains("saveBeans")) {
                    saveBeans = Hawk.get("saveBeans");
                } else {
                    saveBeans = new ArrayList<SaveBean>();
                }
                SaveBean saveBean = new SaveBean();
                saveBean.setEmail(id_email.getText().toString());
                saveBean.setPhone(id_phone.getText().toString());
                saveBean.setQuestion(id_question.getText().toString());
                saveBeans.add(0, saveBean);
                Hawk.put("saveBeans", saveBeans); // save list

                Toast.makeText(MainActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
            }
        });

        id_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveBeans != null)
                    startActivity(new Intent(MainActivity.this, ShowListViewActivity.class));
                Toast.makeText(MainActivity.this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
