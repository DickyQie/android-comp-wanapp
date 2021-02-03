package com.zhangqie.module_mine.bean;

import java.util.Locale;

public class LanguageBean {
    private Locale locale;
    private String desc;
    private boolean press;

    public LanguageBean() {
    }

    public LanguageBean(Locale locale, String desc) {
        this.locale = locale;
        this.desc = desc;
    }

    public LanguageBean(Locale locale, String desc, boolean press) {
        this.locale = locale;
        this.desc = desc;
        this.press = press;
    }

    public boolean isPress() {
        return press;
    }

    public void setPress(boolean press) {
        this.press = press;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
