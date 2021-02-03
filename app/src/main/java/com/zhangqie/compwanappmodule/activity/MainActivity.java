package com.zhangqie.compwanappmodule.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhangqie.base.base_ac.BaseAc;
import com.zhangqie.base.constant.RouterActivityPath;
import com.zhangqie.base.constant.RouterFragmentPath;
import com.zhangqie.compwanappmodule.R;
import com.zhangqie.compwanappmodule.adapter.VpAdapterMain;
import com.zhangqie.compwanappmodule.impl.TabPagerListener;
import com.zhangqie.widget.bubblenavigation.BubbleNavigationConstraintView;
import com.zhangqie.widget.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.zhangqie.widget.viewpager.NoScrollViewPager;

import butterknife.ButterKnife;

/**
 * Created by zhangqie on 2021/1/25
 * Describe:
 */

@Route(path = RouterActivityPath.Main.PAGER_MAIN)
public class MainActivity extends BaseAc implements BubbleNavigationChangeListener, TabPagerListener {


    NoScrollViewPager viewPager;
    BubbleNavigationConstraintView bubbleNavigationLinearView;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_main;
    }

    @Override
    protected boolean canSwipeBack() {
        return false;
    }

    @Override
    public void initViews() {

        viewPager = findViewById(R.id.viewPager);
        bubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear);

        initTab();

        initNav();

        requestPermissions(
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.READ_EXTERNAL_STORAGE"
        );

    }


    private void initNav() {

        bubbleNavigationLinearView.setTypeface(Typeface.createFromAsset(getAssets(), "rubik.ttf"));

        bubbleNavigationLinearView.setBadgeValue(0, null);
        bubbleNavigationLinearView.setBadgeValue(1, null); //invisible badge
        bubbleNavigationLinearView.setBadgeValue(2, null);
        bubbleNavigationLinearView.setBadgeValue(3, null);
        bubbleNavigationLinearView.setBadgeValue(4, null); //empty badge
        bubbleNavigationLinearView.setNavigationChangeListener(this);
    }

    private void initTab() {
        VpAdapterMain adapterMain = new VpAdapterMain(getSupportFragmentManager());
        adapterMain.setListener(this);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setScrollable(false);
        viewPager.setAdapter(adapterMain);
    }


    @Override
    public void onNavigationChanged(View view, int position) {
        viewPager.setCurrentItem(position, false);
    }

    //用户按返回按钮不关闭页面，而是返回到系统桌面。相当于按home键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public Fragment getFragment(int position) {
        if (position == 0) {
            return (Fragment) ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).navigation();
        } else if (position == 1) {
             return (Fragment) ARouter.getInstance().build(RouterFragmentPath.Project.PAGER_PROJECT).navigation();
        } else if (position == 2) {
             return (Fragment) ARouter.getInstance().build(RouterFragmentPath.Square.PAGER_SQUARE).navigation();

        } else if (position == 3) {

            return (Fragment) ARouter.getInstance().build(RouterFragmentPath.Public.PAGER_PUBLIC).navigation();

        } else if (position == 4) {
             return (Fragment) ARouter.getInstance().build(RouterFragmentPath.Mine.PAGER_MINE).navigation();
        }
        return null;
    }


    @Override
    public int count() {
        return 5;
    }
}
