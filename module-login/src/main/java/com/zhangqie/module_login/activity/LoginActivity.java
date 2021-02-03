package com.zhangqie.module_login.activity;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhangqie.base.base_ac.BaseAc;
import com.zhangqie.base.constant.RouterActivityPath;
import com.zhangqie.module_login.R;

/**
 * Created by zhangqie on 2021/1/24
 * Describe:
 */

@Route(path = RouterActivityPath.Login.PAGER_LOGIN)
public class LoginActivity extends BaseAc {

    @Override
    protected int getLayoutId() {
        return R.layout.ac_login;
    }
}
