package com.zhangzhouhyx.myapplication.notification;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.zhangzhouhyx.myapplication.notification.TCThreadMode.ASYNC_ThreadMode;
import static com.zhangzhouhyx.myapplication.notification.TCThreadMode.MAIN_ThreadMode;

/**
 * Created by yangyh on 2018/7/2.
 */
public class TCNotificationCenter {

    public List<TCNotification> notificationList;

    //单例模式
    private volatile static TCNotificationCenter instance = null;

    private TCNotificationCenter() {

        notificationList = new CopyOnWriteArrayList<TCNotification>();
    }

    public static TCNotificationCenter defaultCenter() {

        if (instance == null) {

            synchronized (TCNotificationCenter.class) {

                if (instance == null) {

                    instance = new TCNotificationCenter();
                }
            }
        }

        return instance;
    }

    public void addObserver(Object object, String methodName, String name) {

        if (!((object instanceof Fragment) || (object instanceof Activity))) {

            Log.d("dddd", "消息转发对象不是Activity或者Fragment，造成崩溃");
            Log.d("dddd", "addObserver: " + 1/0);
        }

        TCNotification notification = new TCNotification();
        notification.setObject(object);
        notification.setMethodName(methodName);
        notification.setName(name);

        if (!notificationList.contains(notification)) {

            notificationList.add(notification);
        }
    }

    public void postNotificationName(String name,final Map<String, Object> param) {

        for (final TCNotification notification : notificationList) {

            if (notification.name.equals(name)) {

                try {

                    final Method method = notification.object.getClass().getMethod(notification.methodName, Map.class);

                    TCThreadMode threadMode = method.getAnnotation(TCThreadMode.class);
                    if (threadMode != null) {

                        if (threadMode.threadMode() == MAIN_ThreadMode) {

                            Handler mainHandler = new Handler(Looper.getMainLooper());
                            mainHandler.post(new Runnable() {

                                @Override
                                public void run() {

                                    try {

                                        Log.d("dddd","主线程被调用");
                                        method.invoke(notification.object, param);
                                    } catch (Exception e) {

                                        e.printStackTrace();
                                    }
                                }
                            });
                        } else if (threadMode.threadMode() == ASYNC_ThreadMode) {

                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    try {

                                        Log.d("dddd","子线程被调用");
                                        method.invoke(notification.object, param);
                                    } catch (Exception e) {

                                        e.printStackTrace();
                                    }
                                }
                            });

                            thread.start();
                        }
                    } else {

                        Log.d("dddd","未指定线程");
                        method.invoke(notification.object, param);
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }

    public void removeObserver(Object object) {

        for (TCNotification notification : notificationList) {

            if (notification.object == object) {

                notificationList.remove(notification);
            }
        }
    }

    public void removeObserver(Object object, String name) {

        for (TCNotification notification : notificationList) {

            if (notification.object == object && notification.name.equals(name)) {

                notificationList.remove(notification);
            }
        }
    }
}
