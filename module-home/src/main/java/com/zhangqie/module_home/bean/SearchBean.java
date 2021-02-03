package com.zhangqie.module_home.bean;

import com.zhangqie.base.base_api.res_data.SearchBeanRes;

import java.util.List;


public class SearchBean {
    private String title;
    private List<SearchBeanRes> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SearchBeanRes> getData() {
        return data;
    }

    public void setData(List<SearchBeanRes> data) {
        this.data = data;
    }
}
