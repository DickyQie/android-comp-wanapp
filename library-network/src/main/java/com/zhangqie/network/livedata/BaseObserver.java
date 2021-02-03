package com.zhangqie.network.livedata;

import com.zhangqie.network.bean.ApiResponse;
import com.zhangqie.network.util.LogUtil;
import com.zhangqie.network.widget.PopUtil;

public class BaseObserver<T> implements IBaseObserver<T> {

    private BaseObserverCallBack<T> baseObserverCallBack;

    public BaseObserver(BaseObserverCallBack<T> baseObserverCallBack) {
        this.baseObserverCallBack = baseObserverCallBack;
    }

    @Override
    public void onChanged(T t) {
        if (t instanceof ApiResponse) {
            ApiResponse apiResponse = (ApiResponse) t;
            if (apiResponse.isSuccess()) {
                baseObserverCallBack.onSuccess(t);
            } else {
                baseObserverCallBack.onFail(apiResponse.getErrorMsg());
                if (baseObserverCallBack.showErrorMsg()) {
                    LogUtil.show(apiResponse.getErrorMsg());
                    PopUtil.show(apiResponse.getErrorMsg());
                }
            }
        } else {
            baseObserverCallBack.onFail("系统繁忙!");
        }
        baseObserverCallBack.onFinish();
    }
}
