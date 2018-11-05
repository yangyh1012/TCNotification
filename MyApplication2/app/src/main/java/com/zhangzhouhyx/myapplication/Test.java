package com.zhangzhouhyx.myapplication;

import com.zhangzhouhyx.myapplication.notification.TCNotificationCenter;

/**
 * @author Yangyihui
 * @create 2018/7/5
 * @Describe
 */
public class Test {

    public Test () {

        TCNotificationCenter.defaultCenter().addObserver(this, "printSelf", "MainActivity");
    }


}
