package com.zhangqie.module_login;

import com.zhangqie.base.BaseApplication;
import com.zhangqie.base.config.ModuleLifecycleConfig;

public class LoginApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
    }
}
