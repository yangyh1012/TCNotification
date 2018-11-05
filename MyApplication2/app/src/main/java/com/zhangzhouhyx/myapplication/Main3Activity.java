package com.zhangzhouhyx.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.zhangzhouhyx.myapplication.R;
import com.zhangzhouhyx.myapplication.notification.TCNotificationCenter;
import com.zhangzhouhyx.myapplication.notification.TCThreadMode;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.zhangzhouhyx.myapplication.notification.TCThreadMode.ASYNC_ThreadMode;
import static com.zhangzhouhyx.myapplication.notification.TCThreadMode.MAIN_ThreadMode;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TCNotificationCenter.defaultCenter().addObserver(this, "printSelf", "MainActivity");
        TCNotificationCenter.defaultCenter().addObserver(this, "printSelf3", "MainActivity2");
    }

    public void onClick(View view) {

//        Intent intent = new Intent(this, Main2Activity.class);//显示intent
        Intent intent = new Intent(this, Main2Activity.class);//显示intent
        startActivity(intent);
    }

    @TCThreadMode()
    public void printSelf (Map<String, String> param) {

        Log.d("dddd", "Main3Activity printSelf 这个方法被调用了！");
        Log.d("dddd", "Thread.currentThread().getName() " + Thread.currentThread().getName());
    }

    @TCThreadMode(threadMode = ASYNC_ThreadMode)
    public void printSelf3 (Map<String, String> param) {

        Log.d("dddd", "Main3Activity printSelf3 这个方法被调用了！");
        Log.d("dddd", "Thread.currentThread().getName() " + Thread.currentThread().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        TCNotificationCenter.defaultCenter().removeObserver(this, "MainActivity2");
        Log.d("dddd", "Main3Activity onDestroy 这个方法被调用了！");
    }
}
