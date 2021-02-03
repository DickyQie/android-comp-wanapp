package com.zhangqie.module_square.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhangqie.base.base_api.res_data.TreeListRes;
import com.zhangqie.base.base_api.util.ApiUtil;
import com.zhangqie.base.constant.RouterFragmentPath;
import com.zhangqie.network.livedata.BaseObserver;
import com.zhangqie.network.livedata.BaseObserverCallBack;
import com.zhangqie.network.bean.ApiResponse;
import com.zhangqie.module_square.base.BaseSquareFg;

import java.util.List;

/**
 * Description: 体系
 */
@Route(path = RouterFragmentPath.Square.PAGER_SYSTEM)
public class SystemFg extends BaseSquareFg {

    @Override
    protected void initViews() {
        super.initViews();
        showLoading(indicatorScrollView);
        listSystem();
    }

    private void listSystem() {
        ApiUtil.getTreeApi().listTrees().observe(this,
                new BaseObserver<>(new BaseObserverCallBack<ApiResponse<List<TreeListRes>>>() {
                    @Override
                    public void onSuccess(ApiResponse<List<TreeListRes>> data) {
                        setData(data.getData(), true);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        showSuccess();
                    }
                }));
    }
}
