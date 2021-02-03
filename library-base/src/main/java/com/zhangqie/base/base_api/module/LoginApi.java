package com.zhangqie.base.base_api.module;

import androidx.lifecycle.LiveData;

import com.zhangqie.base.base_api.res_data.UserInfo;
import com.zhangqie.network.bean.ApiResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Description: 体系api
 */
public interface LoginApi {

    /**
     * 登录
     *
     * @param username 账号
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    LiveData<ApiResponse<UserInfo>> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("user/register")
    LiveData<ApiResponse<UserInfo>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    @GET("user/logout/json")
    LiveData<ApiResponse> logout();
}
