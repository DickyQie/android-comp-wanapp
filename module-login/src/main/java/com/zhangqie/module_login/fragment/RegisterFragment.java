package com.zhangqie.module_login.fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.navigation.fragment.NavHostFragment;

import com.zhangqie.base.base_api.res_data.UserInfo;
import com.zhangqie.base.base_api.util.ApiUtil;
import com.zhangqie.base.base_fg.BaseFg;
import com.zhangqie.base.database.MMkvHelper;
import com.zhangqie.module_login.R;
import com.zhangqie.module_login.R2;
import com.zhangqie.network.bean.ApiResponse;
import com.zhangqie.network.livedata.BaseObserver;
import com.zhangqie.network.livedata.BaseObserverCallBack;
import com.zhangqie.network.widget.PopUtil;
import com.zhangqie.widget.ClearEditText;
import com.zhangqie.widget.submit_button.SubmitButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangqie on 2021/1/24
 * Describe:
 */
public class RegisterFragment extends BaseFg {

    @BindView(R2.id.etAccount)
    ClearEditText etAccount;
    @BindView(R2.id.etPassword)
    EditText etPassword;
    @BindView(R2.id.btnLogin)
    SubmitButton btnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.fg_account_register;
    }

    @Override
    protected void initViews() {
        super.initViews();
        btnLogin.setText("注册");
    }

    @OnClick(R2.id.ivCha)
    public void cha() {
        NavHostFragment.findNavController(this).navigateUp();
    }

    private void register() {
        String username = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            PopUtil.show("请输入账号");
            return;
        }
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            PopUtil.show("请输入密码");
            return;
        }
        btnLogin.startAnim();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ApiUtil.getLoginApi().register(username, password, password).observe(getViewLifecycleOwner(),
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
                                btnLogin.success();
                            }
                        }));
            }
        }, 2000);
    }

    @Override
    public void onStop() {
        super.onStop();
        btnLogin.reset();
    }

    @OnClick(R2.id.btnLogin)
    public void onViewClicked() {
        register();
    }

}
