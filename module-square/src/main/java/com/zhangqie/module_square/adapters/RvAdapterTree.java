package com.zhangqie.module_square.adapters;

import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.flexbox.FlexboxLayout;
import com.zhangqie.base.base_adapter.BaseRecycleAdapter;
import com.zhangqie.base.base_api.res_data.TreeListRes;
import com.zhangqie.base.base_util.RouterUtil;
import com.zhangqie.module_square.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Description: 流式布局
 */
public class RvAdapterTree extends BaseRecycleAdapter<TreeListRes> {

    private LayoutInflater layoutInflater = null;
    private Queue<AppCompatTextView> mFlexItemTextViewCaches = new LinkedList<>();

    public RvAdapterTree(List<TreeListRes> datas) {
        super(datas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rv_item_flex;
    }

    @Override
    protected void bindData(BaseViewHolder holder, TreeListRes res, int position) {
        TextView tvTitle = holder.getView(R.id.tvTitle);
        FlexboxLayout flexLayout = holder.getView(R.id.flexLayout);
        tvTitle.setText(res.getName());
        List<TreeListRes.ChildrenBean> children = res.getChildren();
//            flexLayout.removeAllViews();  //注释这条属性，用下面onViewRecycled()方法也行
        for (int i = 0; i < children.size(); i++) {
            TreeListRes.ChildrenBean datasBean = children.get(i);
            AppCompatTextView labelTv = createOrGetCacheTv(flexLayout);
            labelTv.setText(datasBean.getName());
            labelTv.setOnClickListener(v -> {
                RouterUtil.launchArticleList(datasBean.getId(), datasBean.getName());
            });
            flexLayout.addView(labelTv);
        }
    }


    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        FlexboxLayout flexLayout = holder.getView(R.id.flexLayout);
        for (int i = 0; i < flexLayout.getChildCount(); i++) {
            mFlexItemTextViewCaches.offer((AppCompatTextView) flexLayout.getChildAt(i));
        }
        flexLayout.removeAllViews();
    }

    private AppCompatTextView createOrGetCacheTv(FlexboxLayout flexboxLayout) {
        AppCompatTextView tv = mFlexItemTextViewCaches.poll();
        if (tv != null) {
            return tv;
        }
        return findLabel(flexboxLayout);
    }

    private AppCompatTextView findLabel(FlexboxLayout flexboxLayout) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(flexboxLayout.getContext());
        return (AppCompatTextView) layoutInflater.inflate(R.layout.flextlayout_item_label, flexboxLayout, false);
    }
}
