package com.zhangzhouhyx.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.zhangzhouhyx.myapplication.notification.TCNotificationCenter;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        TCNotificationCenter.defaultCenter().addObserver(this, "printSelf", "MainActivity");
        TCNotificationCenter.defaultCenter().addObserver(this, "printSelf", "MainActivity");
        TCNotificationCenter.defaultCenter().addObserver(this, "printSelf", "MainActivity");
        TCNotificationCenter.defaultCenter().addObserver(this, "printSelf3", "MainActivity2");
    }

    public void onClick(View view) {

//        Intent intent = new Intent(this, Main2Activity.class);//显示intent
        Intent intent = new Intent(this, Main4Activity.class);//显示intent
        startActivity(intent);
    }

    public void printSelf (Map<String, String> param) {

        Log.d("dddd", "Main2Activity printSelf 这个方法被调用了！");
    }

    public void printSelf3 (Map<String, String> param) {

        Log.d("dddd", "Main2Activity printSelf3 这个方法被调用了！");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        TCNotificationCenter.defaultCenter().removeObserver(this);
        Log.d("dddd", "Main2Activity onDestroy 这个方法被调用了！");
    }
}
