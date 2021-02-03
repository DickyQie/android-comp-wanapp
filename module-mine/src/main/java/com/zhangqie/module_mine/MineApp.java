package com.zhangqie.module_mine;

import com.zhangqie.base.BaseApplication;
import com.zhangqie.base.config.ModuleLifecycleConfig;

public class MineApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
    }
}
