package com.zhangqie.module_mine.activity;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhangqie.base.base_ac.BaseAc;
import com.zhangqie.base.base_api.res_data.RankBean;
import com.zhangqie.base.base_api.res_data.RankListRes;
import com.zhangqie.base.base_api.util.ApiUtil;
import com.zhangqie.base.base_util.RouterUtil;
import com.zhangqie.base.constant.C;
import com.zhangqie.module_mine.R;
import com.zhangqie.module_mine.R2;
import com.zhangqie.module_mine.adapters.RvAdapterMyScoreHeader;
import com.zhangqie.module_mine.adapters.RvAdapterMyScoreList;
import com.zhangqie.network.livedata.BaseObserver;
import com.zhangqie.network.livedata.BaseObserverCallBack;
import com.zhangqie.network.bean.ApiResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description: 我的积分
 */
public class MyScoreAc extends BaseAc implements OnRefreshLoadMoreListener {


    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;


    private VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
    private DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
    private final ArrayList<DelegateAdapter.Adapter> adapters = new ArrayList<>();

    private RvAdapterMyScoreHeader adapterMyScoreHeader;
    private RvAdapterMyScoreList adapterMyScoreList;
    private List<RankBean> dataList = new ArrayList<>();


    public static void launch(Context context) {
        context.startActivity(new Intent(context, MyScoreAc.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_my_score;
    }

    @Override
    public void initViews() {
        super.initViews();
        setAcTitle("我的积分");
        setRightImg(R.mipmap.ic_question);
        setOnRightImgClickListener(view -> {
            RouterUtil.launchWeb(C.INTERGRAL_URL);
        });

        adapterMyScoreHeader = new RvAdapterMyScoreHeader();
        adapters.add(adapterMyScoreHeader);
        adapterMyScoreList = new RvAdapterMyScoreList(dataList);
        adapters.add(adapterMyScoreList);
        delegateAdapter.setAdapters(adapters);
        recyclerView.setLayoutManager(virtualLayoutManager);
        recyclerView.setAdapter(delegateAdapter);

        smartRefreshLayout.setOnRefreshLoadMoreListener(this);
        showLoading();
        listScore(true);

    }




    private int page = 1;

    private void listScore(boolean refresh) {
        if (refresh) {
            page = 1;
        } else {
            page++;
        }

        ApiUtil.getUserApi().listIntegral(page).observe(this,
                new BaseObserver<>(new BaseObserverCallBack<ApiResponse<RankListRes>>() {
                    @Override
                    public void onSuccess(ApiResponse<RankListRes> data) {
                        if (refresh) {
                            dataList.clear();
                        }
                        dataList.addAll(data.getData().getDatas());
                        adapterMyScoreList.notifyDataSetChanged();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadMore();
                        showSuccess();
                    }
                }));
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        listScore(false);

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        listScore(true);
    }
}
