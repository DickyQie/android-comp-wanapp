package com.zhangqie.module_mine.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import com.zhangqie.base.base_ac.BaseAc;
import com.zhangqie.base.base_api.util.ApiUtil;
import com.zhangqie.module_mine.R;
import com.zhangqie.module_mine.R2;
import com.zhangqie.network.livedata.BaseObserver;
import com.zhangqie.network.livedata.BaseObserverCallBack;
import com.zhangqie.network.bean.ApiResponse;
import com.zhangqie.network.widget.PopUtil;
import com.zhangqie.widget.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: 分享文章
 */
public class ShareArticleAc extends BaseAc {

    @BindView(R2.id.etTitle)
    ClearEditText etTitle;
    @BindView(R2.id.etLink)
    EditText etLink;


    public static void launch(Context context) {
        context.startActivity(new Intent(context, ShareArticleAc.class));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.ac_share_article;
    }

    @Override
    public void initViews() {
        super.initViews();
        setAcTitle("分享文章");
    }

    @OnClick(R2.id.tvShare)
    public void onViewClicked() {
        String title = etTitle.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            PopUtil.show("请输入标题");
            return;
        }
        String link = etLink.getText().toString().trim();
        if (TextUtils.isEmpty(link)) {
            PopUtil.show("请输入链接");
        }
        showLoading();
        ApiUtil.getArticleApi().shareArticle(title,link).observe(this,
                new BaseObserver<>(new BaseObserverCallBack<ApiResponse>() {
                    @Override
                    public void onSuccess(ApiResponse data) {
                        PopUtil.show("分享成功", () -> finish());
                    }

                    @Override
                    public boolean showErrorMsg() {
                        return true;
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        showSuccess();
                    }
                }));
    }
}
