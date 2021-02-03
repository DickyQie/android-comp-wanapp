package com.zhangqie.module_square;

import com.zhangqie.base.BaseApplication;
import com.zhangqie.base.config.ModuleLifecycleConfig;

public class SquareApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
    }
}
