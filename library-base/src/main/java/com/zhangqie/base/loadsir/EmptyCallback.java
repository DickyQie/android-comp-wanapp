package com.zhangqie.base.loadsir;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.zhangqie.base.R;

public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.base_layout_empty;
    }

    //当前Callback的点击事件，如果返回true则覆盖注册时的onReload()，如果返回false则两者都执行，先执行onReloadEvent()。
    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return false;
    }
}
