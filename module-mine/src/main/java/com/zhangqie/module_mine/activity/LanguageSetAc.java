package com.zhangqie.module_mine.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhangqie.base.base_ac.BaseAc;
import com.zhangqie.base.base_manage.ActivityUtil;
import com.zhangqie.base.base_util.LanguageUtil;
import com.zhangqie.base.constant.RouterActivityPath;
import com.zhangqie.module_mine.R;
import com.zhangqie.module_mine.R2;
import com.zhangqie.module_mine.adapters.RvAdapterLanguageSet;
import com.zhangqie.module_mine.bean.LanguageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class LanguageSetAc extends BaseAc {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    private RvAdapterLanguageSet adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_language_set;
    }

    @Override
    public void initViews() {
        super.initViews();
        setAcTitle(getString(R.string.mine_language_set));
        adapter = new RvAdapterLanguageSet(getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setListener(position -> {
            LanguageBean languageBean = adapter.getDatas().get(position);
            LanguageUtil.switchLanguage(languageBean.getLocale());
            ActivityUtil.finishAllActivity();
            ARouter.getInstance().build(RouterActivityPath.Main.PAGER_MAIN).navigation();
        });
    }


    private List<LanguageBean> getData() {
        List<LanguageBean> list = new ArrayList<>();
        list.add(new LanguageBean(Locale.SIMPLIFIED_CHINESE, "简体中文"));
        list.add(new LanguageBean(Locale.US, "英文"));
        Locale currentLanguage = LanguageUtil.getCurrentLanguage();
        for (LanguageBean languageBean : list) {
            if (currentLanguage.equals(languageBean.getLocale())) {
                languageBean.setPress(true);
                break;
            }
        }
        return list;
    }
}
