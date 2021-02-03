package com.zhangqie.base.base_api.util;

import com.zhangqie.base.base_api.module.ArticleApi;
import com.zhangqie.base.base_api.module.LoginApi;
import com.zhangqie.base.base_api.module.ProjectApi;
import com.zhangqie.base.base_api.module.TreeApi;
import com.zhangqie.base.base_api.module.UserApi;
import com.zhangqie.network.livedata.RetrofitCreateLiveDataHelper;
import com.zhangqie.network.constrant.U;

/**
 * Description: 不同模块BASE_URL可能不同
 */
public class ApiUtil {

    public static ProjectApi getProjectApi() {
        return RetrofitCreateLiveDataHelper.getInstance().create(U.BASE_URL, ProjectApi.class);
    }

    public static ArticleApi getArticleApi() {
        return RetrofitCreateLiveDataHelper.getInstance().create(U.BASE_URL, ArticleApi.class);
    }

    public static TreeApi getTreeApi() {
        return RetrofitCreateLiveDataHelper.getInstance().create(U.BASE_URL, TreeApi.class);
    }

    public static LoginApi getLoginApi() {
        return RetrofitCreateLiveDataHelper.getInstance().create(U.BASE_URL, LoginApi.class);
    }

    public static UserApi getUserApi() {
        return RetrofitCreateLiveDataHelper.getInstance().create(U.BASE_URL, UserApi.class);
    }


}
