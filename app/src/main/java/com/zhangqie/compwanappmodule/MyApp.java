package com.zhangqie.compwanappmodule;

import com.zhangqie.base.BaseApplication;
import com.zhangqie.base.config.ModuleLifecycleConfig;

/**
 * Created by zhangqie on 2021/1/25
 * Describe:
 */
public class MyApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
    }
}
