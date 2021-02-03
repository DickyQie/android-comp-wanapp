package com.zhangqie.common;

import android.app.Application;

public class AppBridge {

    public static Application getApplicationByReflect() {
        return ActivityLifecycleImpl.INSTANCE.getApplicationByReflect();
    }
}
