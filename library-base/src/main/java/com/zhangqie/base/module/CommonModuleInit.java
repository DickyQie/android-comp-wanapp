package com.zhangqie.base.module;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.tencent.mmkv.MMKV;
import com.zhangqie.db.manager.DbUtil;
import com.zhangqie.base.BaseApplication;
import com.zhangqie.base.loadsir.EmptyCallback;
import com.zhangqie.base.loadsir.ErrorCallback;
import com.zhangqie.base.loadsir.LoadingCallback;

public class CommonModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new ClassicsHeader(application));
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(application));
        MMKV.initialize(application);
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(application);
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new EmptyCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();


        DbUtil.getInstance().init(application, "wanandroid");


//        if (LeakCanary.isInAnalyzerProcess(application)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return false;
//        }
//        LeakCanary.install(application);


//        NeverCrashHelper.init((t, e) -> {
//            LogUtils.i(e.getMessage());
//            new Handler(Looper.getMainLooper()).post(() -> {
//                Toast.makeText(application, e.getMessage(), Toast.LENGTH_SHORT).show();
//            });
//        });
        return false;
    }

    @Override
    public boolean onInitAfter(BaseApplication application) {
        return false;
    }
}
