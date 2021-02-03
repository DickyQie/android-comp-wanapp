package com.zhangqie.base.base_api.module;

import androidx.lifecycle.LiveData;

import com.zhangqie.base.base_api.res_data.TreeListRes;
import com.zhangqie.network.bean.ApiResponse;

import java.util.List;

import retrofit2.http.GET;

/**
 * Description: 体系api
 */
public interface TreeApi {

    /**
     * 获取轮播图
     *
     * @return
     */
    @GET("tree/json")
    LiveData<ApiResponse<List<TreeListRes>>> listTrees();

    @GET("navi/json")
    LiveData<ApiResponse<List<TreeListRes>>> listNavis();


}
