package com.zhangqie.compwanappmodule.activity;

import android.content.Intent;

import com.zhangqie.base.base_ac.BaseAc;
import com.zhangqie.base.base_api.res_data.ProjectListRes;
import com.zhangqie.base.base_api.util.ApiUtil;
import com.zhangqie.base.base_util.RouterUtil;
import com.zhangqie.base.database.MMkvHelper;
import com.zhangqie.compwanappmodule.R;
import com.zhangqie.network.bean.ApiResponse;
import com.zhangqie.network.livedata.BaseObserver;
import com.zhangqie.network.livedata.BaseObserverCallBack;

import java.util.List;

import me.wangyuwei.particleview.ParticleView;

/**
 * Created by zhangqie on 2021/1/25
 * Describe:
 */
public class SplashAc extends BaseAc {

    ParticleView particleview;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_splash;
    }

    @Override
    public void initViews() {
        super.initViews();

        particleview = findViewById(R.id.particleview);

        ApiUtil.getProjectApi().listProjectsTab().observe(this, new BaseObserver<>(new BaseObserverCallBack<ApiResponse<List<ProjectListRes>>>() {
            @Override
            public void onSuccess(ApiResponse<List<ProjectListRes>> data) {
                List<ProjectListRes> dataList = data.getData();
                if (dataList.size() > 0) {
                    particleview.setOnParticleAnimListener(() -> {
                        MMkvHelper.getInstance().saveProjectTabs(dataList);
                        RouterUtil.launchMain();
                        //startActivity(new Intent(SplashAc.this, MainActivity.class));
                        finish();
                    });
                }
            }
        }));
        particleview.startAnim();
    }
}
