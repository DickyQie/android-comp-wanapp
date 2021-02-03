package com.zhangqie.base.impl;

public interface IAcView {
    void initViews();
    void initEvents();
    void beforeOnCreate();
    void afterOnCreate();
    void initImmersionBar();
}
