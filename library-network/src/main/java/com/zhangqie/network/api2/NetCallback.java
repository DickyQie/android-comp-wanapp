package com.zhangqie.network.api2;


import com.zhangqie.network.bean.ApiResponse;

public interface NetCallback<T extends ApiResponse> {

    void onSuccess(T response);

    void onFail(String msg);
}
