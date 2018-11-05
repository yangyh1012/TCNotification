package com.zhangzhouhyx.myapplication.notification;

/**
 * @author Yangyihui
 * @create 2018/7/5
 * @Describe
 */
public class TCNotification {

    public Object object;
    public String methodName;
    public String name;

    @Override
    public boolean equals(Object obj) {

        TCNotification notification = (TCNotification)obj;

        String notifi1 = notification.object.toString() + " " + notification.methodName + " " + notification.name;
        String notifi2 = this.object.toString() + " " + this.methodName + " " + this.name;

        return (notifi1.equals(notifi2));
    }

    public Object getObject() {

        return object;
    }

    public void setObject(Object object) {

        this.object = object;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
