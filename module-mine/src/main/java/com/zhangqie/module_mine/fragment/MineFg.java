package com.zhangqie.module_mine.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.utils.widget.ImageFilterButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhangqie.aop.checklogin.annotation.CheckLogin;
import com.zhangqie.base.base_api.res_data.UserInfo;
import com.zhangqie.base.base_api.util.ApiUtil;
import com.zhangqie.base.base_fg.BaseFg;
import com.zhangqie.base.constant.RouterFragmentPath;
import com.zhangqie.base.database.MMkvHelper;
import com.zhangqie.module_mine.R;
import com.zhangqie.module_mine.R2;
import com.zhangqie.module_mine.activity.AboutAuthorAc;
import com.zhangqie.module_mine.activity.MyCollectAc;
import com.zhangqie.module_mine.activity.MyScoreAc;
import com.zhangqie.module_mine.activity.MyShareAc;
import com.zhangqie.module_mine.activity.OpenSourceAc;
import com.zhangqie.module_mine.activity.ScoreRankListAc;
import com.zhangqie.module_mine.activity.SettingAc;
import com.zhangqie.network.livedata.BaseObserver;
import com.zhangqie.network.livedata.BaseObserverCallBack;
import com.zhangqie.network.bean.ApiResponse;
import com.zhangqie.widget.waveview.WaveView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: 我的
 */
@Route(path = RouterFragmentPath.Mine.PAGER_MINE)
public class MineFg extends BaseFg {

    @BindView(R2.id.waveView)
    WaveView waveView;
    @BindView(R2.id.llHead)
    LinearLayout llHead;
    @BindView(R2.id.root)
    LinearLayout root;
    @BindView(R2.id.ivAvatar)
    ImageFilterButton ivAvatar;
    @BindView(R2.id.tvName)
    TextView tvName;
    @BindView(R2.id.tvId)
    TextView tvId;
    @BindView(R2.id.tvLevel)
    TextView tvLevel;
    @BindView(R2.id.tvMyScore)
    TextView tvMyScore;
    private UserInfo userInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.fg_mine;
    }

    @Override
    protected boolean immersionBar() {
        return true;
    }

    @Override
    protected void initViews() {
        super.initViews();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) llHead.getLayoutParams();
        waveView.setOnWaveAnimationListener(y -> {
            layoutParams.setMargins(0, (int) y, 0, 0);
            llHead.setLayoutParams(layoutParams);
        });
        getScore();
    }

    @Override
    public void onResume() {
        super.onResume();
        userInfo = MMkvHelper.getInstance().getUserInfo();
        if (userInfo != null) {
            tvName.setText(userInfo.getUsername());
            getScore();
        } else {
            tvId.setText("");
            tvMyScore.setText("");
            tvName.setText(R.string.unlogin);
            tvLevel.setVisibility(View.GONE);
        }
    }


    private void getScore() {
        ApiUtil.getUserApi().getIntegral().observe(this,
                new BaseObserver<>(new BaseObserverCallBack<ApiResponse<UserInfo>>() {
                    @Override
                    public void onSuccess(ApiResponse<UserInfo> data) {

                        tvLevel.setVisibility(View.VISIBLE);
                        UserInfo userInfo1 = data.getData();
                        tvId.setText(String.format("ID: %s", userInfo1.getUserId()));
                        tvLevel.setText(String.format("lv.%d", userInfo1.getLevel()));
                        tvMyScore.setText(String.format(getString(R.string.mine_current_score)+": %s", userInfo1.getCoinCount()));
                        if (userInfo != null) {
                            userInfo1.setUsername(userInfo.getUsername());
                        }
                        MMkvHelper.getInstance().saveUserInfo(userInfo1);
                    }
                }));
    }

    @CheckLogin
    @OnClick({R2.id.ivSet, R2.id.tvName, R2.id.tvScoreRankList, R2.id.llHead, R2.id.llScore,
            R2.id.llCollect, R2.id.llShare, R2.id.llProjects, R2.id.llAbout})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.ivSet) {
            SettingAc.launch(getContext());
        } else if (id == R.id.tvName) {
        } else if (id == R.id.tvScoreRankList) {
            ScoreRankListAc.launch(getContext());
        } else if (id == R.id.llHead) {
        } else if (id == R.id.llScore) {
            MyScoreAc.launch(getContext());
        } else if (id == R.id.llCollect) {
            MyCollectAc.launch(getContext());
        } else if (id == R.id.llShare) {
            MyShareAc.launch(getContext());
        } else if (id == R.id.llProjects) {
            OpenSourceAc.launch(getContext());
        } else if (id == R.id.llAbout) {
            AboutAuthorAc.launch(getContext());
        }
    }
}
