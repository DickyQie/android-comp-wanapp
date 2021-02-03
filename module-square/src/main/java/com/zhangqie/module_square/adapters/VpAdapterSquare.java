package com.zhangqie.module_square.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zhangqie.module_square.impl.TabPagerListener;

public class VpAdapterSquare extends FragmentPagerAdapter {

    public TabPagerListener listener;
    private int PAGE_COUNT;

    public void setListener(TabPagerListener listener) {
        this.listener = listener;
    }

    public VpAdapterSquare(FragmentManager fm, int PAGE_COUNT) {
        super(fm);
        this.PAGE_COUNT = PAGE_COUNT;

    }

    @Override
    public Fragment getItem(int position) {
        return listener.getFragment(position);

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }


}
