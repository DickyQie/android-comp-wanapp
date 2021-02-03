package com.zhangqie.module_mine.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.zhangqie.base.base_ac.BaseAc;
import com.zhangqie.base.base_util.FileUtils;
import com.zhangqie.module_mine.R;
import com.zhangqie.module_mine.R2;
import com.zhangqie.network.widget.PopUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutAuthorAc extends BaseAc {

    @BindView(R2.id.ivWx)
    ImageView ivWx;
    @BindView(R2.id.ivZfb)
    ImageView ivZfb;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, AboutAuthorAc.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_about_author;
    }

    @Override
    public void initViews() {
        super.initViews();
        setAcTitle(getString(R.string.mine_about_author));

    }

    @OnClick(R2.id.ivWx)
    public void saveWx() {
        FileUtils.saveImage(this, ivWx);
        PopUtil.show(getString(R.string.save_success));
    }

    @OnClick(R2.id.ivZfb)
    public void saveZfb() {
        FileUtils.saveImage(this, ivZfb);
        PopUtil.show(getString(R.string.save_success));
    }

}
