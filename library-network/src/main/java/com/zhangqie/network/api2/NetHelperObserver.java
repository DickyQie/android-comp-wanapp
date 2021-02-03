package com.zhangqie.network.api2;

import android.util.Log;

import androidx.lifecycle.LifecycleObserver;

import com.zhangqie.network.bean.ApiResponse;
import com.zhangqie.network.util.RxExceptionUtil;

import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class NetHelperObserver <T extends ApiResponse> implements Observer<T>, LifecycleObserver {

    private NetCallback<T> mCallback;

    public NetHelperObserver(NetCallback<T> callback) {
        mCallback = callback;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        if (mCallback != null) {
            if (t.isSuccess()) {
                mCallback.onSuccess(t);
            } else {
                mCallback.onFail(t.getErrorMsg());
            }
        }
    }

    @Override
    public void onError(@NonNull Throwable t) {
        Log.e("请求错误", Objects.requireNonNull(t.getMessage()));
        if (mCallback != null) {
            mCallback.onFail(RxExceptionUtil.exceptionHandler(t));
        }
    }

    @Override
    public void onComplete() {

    }

}