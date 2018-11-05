package com.zhangzhouhyx.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zhangzhouhyx.myapplication.notification.TCNotificationCenter;

import java.lang.reflect.Method;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view) {

//        Intent intent = new Intent(this, Main2Activity.class);//显示intent
        Intent intent = new Intent(this, Main3Activity.class);//显示intent
        startActivity(intent);
    }


}
