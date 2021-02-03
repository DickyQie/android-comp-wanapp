package com.zhangqie.base.base_api.module;

import androidx.lifecycle.LiveData;

import com.zhangqie.base.base_api.res_data.RankListRes;
import com.zhangqie.base.base_api.res_data.UserInfo;
import com.zhangqie.network.bean.ApiResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Description: 体系api
 */
public interface UserApi {

    /**
     * 积分排行榜接口
     *
     * @param page
     * @return
     */
    @GET("coin/rank/{page}/json")
    LiveData<ApiResponse<RankListRes>> listScoreRank(@Path("page") int page);

    //获取个人积分
    @GET("lg/coin/userinfo/json")
    LiveData<ApiResponse<UserInfo>> getIntegral();

    /**
     * 获取个人积分列表
     *
     * @param page
     * @return
     */
    @GET("lg/coin/list/{page}/json")
    LiveData<ApiResponse<RankListRes>> listIntegral(@Path("page") int page);


}
