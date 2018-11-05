package com.zhangzhouhyx.myapplication.notification;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Yangyihui
 * @create 2018/7/5
 * @Describe
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TCThreadMode {

    public static final int MAIN_ThreadMode = 1;
    public static final int ASYNC_ThreadMode = 2;

    int threadMode() default MAIN_ThreadMode;
}
