package com.zhangqie.module_public;

import com.zhangqie.base.BaseApplication;
import com.zhangqie.base.config.ModuleLifecycleConfig;

public class PublicApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
    }
}
