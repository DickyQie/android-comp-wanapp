package com.zhangqie.compwanappmodule.intercept;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

@Interceptor(priority = 1, name = "login")
public class LoginInterceptor implements IInterceptor {
    private String TAG = getClass().getName();

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.e(TAG, "process: " + postcard.toString(), null);
        int isLogin = postcard.getExtras().getInt("isLogin", 0);
//        if (isLogin == 1) {
//            callback.onContinue(postcard);
//        } else {
//            callback.onInterrupt(null);
//        }
        callback.onContinue(postcard);

    }

    @Override
    public void init(Context context) {
        Log.e(TAG, "init: ", null);
    }
}
