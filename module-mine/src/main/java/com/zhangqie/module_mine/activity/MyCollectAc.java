package com.zhangqie.module_mine.activity;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhangqie.base.adapters.RvAdapterArticleList;
import com.zhangqie.base.base_ac.BaseAc;
import com.zhangqie.base.base_api.res_data.ArticleBean;
import com.zhangqie.base.base_api.res_data.ArticleListRes;
import com.zhangqie.base.base_api.util.ApiUtil;
import com.zhangqie.base.base_util.RouterUtil;
import com.zhangqie.module_mine.R;
import com.zhangqie.module_mine.R2;
import com.zhangqie.network.livedata.BaseObserver;
import com.zhangqie.network.livedata.BaseObserverCallBack;
import com.zhangqie.network.bean.ApiResponse;
import com.zhangqie.widget.CustomItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Description: 我的收藏
 */
public class MyCollectAc extends BaseAc implements OnRefreshLoadMoreListener {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private RvAdapterArticleList adapterArticleList;



    public static void launch(Context context) {
        context.startActivity(new Intent(context, MyCollectAc.class));
    }
    @Override
    protected int getLayoutId() {
        return R.layout.ac_my_collect;
    }

    @Override
    public void initViews() {
        super.initViews();
        setAcTitle(getString(R.string.mine_collect));
        adapterArticleList = new RvAdapterArticleList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterArticleList);
        recyclerView.addItemDecoration(new CustomItemDecoration(this,
                CustomItemDecoration.ItemDecorationDirection.VERTICAL_LIST, R.drawable.linear_split_line));
        adapterArticleList.setOnItemClickListener((adapter, view, position) -> {
            List<ArticleBean> data = (List<ArticleBean>) adapter.getData();
            RouterUtil.launchWeb(data.get(position).getLink());
        });
        adapterArticleList.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId()==R.id.ivCollect){
                List<ArticleBean> data = (List<ArticleBean>) adapter.getData();
                ArticleBean articleBean = data.get(position);
                ApiUtil.getArticleApi().unCollect(articleBean.getId()).observe(this,apiResponse -> {});
                adapterArticleList.cancelCollect(position);
            }
        });
        smartRefreshLayout.setOnRefreshLoadMoreListener(this);
        showLoading(smartRefreshLayout);
        listMyCollect(true);
    }

    private int page = 0;

    private void listMyCollect(boolean refresh) {
        if (refresh) {
            page = 0;
        } else {
            page++;
        }
        ApiUtil.getArticleApi().listMyCollect(page).observe(this,
                new BaseObserver<>(new BaseObserverCallBack<ApiResponse<ArticleListRes>>() {
                    @Override
                    public void onSuccess(ApiResponse<ArticleListRes> data) {
                        List<ArticleBean> datas = data.getData().getDatas();
                        for (ArticleBean articleBean : datas) {
                            articleBean.setCollect(true);
                        }

                        if (refresh) {
                            adapterArticleList.setList(datas);
                        } else {
                            adapterArticleList.addData(datas);
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishLoadMore();
                        smartRefreshLayout.finishRefresh();
                        if (adapterArticleList.getData().size() <= 0) {
                            showEmpty();
                        } else {
                            showSuccess();
                        }
                    }
                }));
    }

    @Override
    public void onRetryBtnClick() {
        showLoading();
        listMyCollect(true);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        listMyCollect(false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        listMyCollect(true);
    }
}
