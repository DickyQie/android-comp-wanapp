package com.zhangqie.module_home;

import com.zhangqie.base.BaseApplication;
import com.zhangqie.base.config.ModuleLifecycleConfig;


public class HomeApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
    }
}
