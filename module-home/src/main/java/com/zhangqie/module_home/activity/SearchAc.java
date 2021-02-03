package com.zhangqie.module_home.activity;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.zhangqie.base.base_ac.BaseAc;
import com.zhangqie.base.base_api.res_data.SearchBeanRes;
import com.zhangqie.base.base_api.util.ApiUtil;
import com.zhangqie.base.base_util.InputTools;
import com.zhangqie.db.dao.SearchHistoryDao;
import com.zhangqie.db.entity.SearchHistoryEntity;
import com.zhangqie.db.manager.DbUtil;
import com.zhangqie.module_home.R;
import com.zhangqie.module_home.R2;
import com.zhangqie.module_home.adapter.RvAdapterSearchClear;
import com.zhangqie.module_home.adapter.RvAdapterSearchHis;
import com.zhangqie.module_home.adapter.RvAdapterSearchHot;
import com.zhangqie.module_home.bean.SearchBean;
import com.zhangqie.network.bean.ApiResponse;
import com.zhangqie.network.livedata.BaseObserver;
import com.zhangqie.network.livedata.BaseObserverCallBack;
import com.zhangqie.network.widget.PopUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * Created by zhangqie on 2021/1/25
 * Describe:
 */
public class SearchAc extends BaseAc {


    @BindView(R2.id.etSearch)
    AppCompatEditText etSearch;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;


    private SearchHistoryDao searchHistoryDao;

    private VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
    private DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
    private final ArrayList<DelegateAdapter.Adapter> adapters = new ArrayList<>();
    private RvAdapterSearchHot adapterSearchHot;
    private RvAdapterSearchHis adapterSearchHis;



    @Override
    protected int getLayoutId() {
        return R.layout.ac_search;
    }


    @Override
    public void initViews() {
        super.initViews();

        searchHistoryDao = DbUtil.getInstance().getAppDataBase().searchHistoryDao();
        adapterSearchHis = new RvAdapterSearchHis();
        adapters.add(adapterSearchHis);
        RvAdapterSearchClear adapterSearchClear = new RvAdapterSearchClear();
        adapters.add(adapterSearchClear);
        adapterSearchHot = new RvAdapterSearchHot();
        adapters.add(adapterSearchHot);
        delegateAdapter.setAdapters(adapters);
        recyclerView.setLayoutManager(virtualLayoutManager);
        recyclerView.setAdapter(delegateAdapter);
        adapterSearchClear.setOnSearchClearCallBack(() -> {
            //清空历史记录
            searchHistoryDao.deleteAll();
            adapterSearchHis.clear();
            InputTools.hideInputMethod(this);
        });
        adapterSearchHis.setOnSearchHisCallBack(new RvAdapterSearchHis.OnSearchHisCallBack() {
            @Override
            public void onItemClick(String searchContent) {
                //搜索历史
                skipResult(searchContent);
            }

            @Override
            public void onItemDelete(long id) {
                //删除历史
                searchHistoryDao.deleteById(id);
            }
        });
        adapterSearchHot.setOnSearchHotCallBack(new RvAdapterSearchHot.OnSearchHotCallBack() {
            @Override
            public void onHot(String title) {
                //热门搜索
                skipResult(title);
            }
        });



        ApiUtil.getArticleApi().hotSearch().observe(this,
                new BaseObserver<>(new BaseObserverCallBack<ApiResponse<List<SearchBeanRes>>>() {
                    @Override
                    public void onSuccess(ApiResponse<List<SearchBeanRes>> data) {
                        List<SearchBean> list = new ArrayList<>();
                        SearchBean searchBean = new SearchBean();
                        searchBean.setTitle(getString(R.string.hot_search));
                        searchBean.setData(data.getData());
                        list.add(searchBean);
                        adapterSearchHot.refresh(list);


                    }
                }));

        InputTools.showSoftInput(etSearch);

    }

    @OnEditorAction(R2.id.etSearch)
    public boolean OnEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String trim = etSearch.getText().toString().trim();
            if (TextUtils.isEmpty(trim)) {
                PopUtil.show("请输入内容再搜索");
                etSearch.setSelection(0);
            } else {
                skipResult(trim);
                saveDB(trim);
            }
        }
        return false;

    }

    private void saveDB(String trim) {
        List<SearchHistoryEntity> searchHistoryEntities = searchHistoryDao.selectHis();
        long id = 0;
        for (SearchHistoryEntity searchHistoryEntity : searchHistoryEntities) {
            if (TextUtils.equals(searchHistoryEntity.getName(), trim)) {
                id = searchHistoryEntity.getId();
                break;
            }
        }
        if (id != 0) {
            searchHistoryDao.deleteById(id);
        }
        searchHistoryDao.insertPerson(new SearchHistoryEntity(trim));
    }

    @Override
    protected void onResume() {
        super.onResume();

        filterHist();
    }

    private void filterHist() {
        List<SearchHistoryEntity> searchHistoryEntities = searchHistoryDao.selectHis();
        Collections.reverse(searchHistoryEntities);
        adapterSearchHis.refresh(searchHistoryEntities);
    }

    private void skipResult(String keyword) {
//        InputTools.hideInputMethod(this);
        etSearch.setText(keyword);
        Pair<View, String> p = Pair.create(etSearch, "search");
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                p);
        SearchResultAc.start(this, keyword, optionsCompat);
    }

    @OnClick(R2.id.ivBack)
    public void back() {
        finishAfterTransition();

    }
}
