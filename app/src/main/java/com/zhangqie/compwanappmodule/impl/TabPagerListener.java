package com.zhangqie.compwanappmodule.impl;

import androidx.fragment.app.Fragment;

public interface TabPagerListener {
    Fragment getFragment(int position);

    int count();
}
