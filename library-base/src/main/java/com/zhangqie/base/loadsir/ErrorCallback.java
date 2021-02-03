package com.zhangqie.base.loadsir;

import com.kingja.loadsir.callback.Callback;
import com.zhangqie.base.R;

public class ErrorCallback extends Callback {

    @Override
    protected int onCreateView()
    {
        return R.layout.base_layout_error;
    }
}
