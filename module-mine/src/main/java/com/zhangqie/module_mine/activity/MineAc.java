package com.zhangqie.module_mine.activity;

import com.zhangqie.base.base_ac.BaseAc;
import com.zhangqie.base.base_api.res_data.UserInfo;
import com.zhangqie.base.base_api.util.ApiUtil;
import com.zhangqie.base.database.MMkvHelper;
import com.zhangqie.module_mine.R;
import com.zhangqie.network.livedata.BaseObserver;
import com.zhangqie.network.livedata.BaseObserverCallBack;
import com.zhangqie.network.bean.ApiResponse;

/**
 * Description: 需要结束进程重新打开该module
 */
public class MineAc extends BaseAc {
    @Override
    protected int getLayoutId() {
        return R.layout.ac_mine;
    }

    @Override
    public void beforeOnCreate() {
        super.beforeOnCreate();
        ApiUtil.getLoginApi().login("zlxx", "111111").observe(this,
                new BaseObserver<>(new BaseObserverCallBack<ApiResponse<UserInfo>>() {
                    @Override
                    public void onSuccess(ApiResponse<UserInfo> data) {
                        MMkvHelper.getInstance().saveUserInfo(data.getData());
                    }

                    @Override
                    public boolean showErrorMsg() {
                        return true;
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                }));
    }

}
