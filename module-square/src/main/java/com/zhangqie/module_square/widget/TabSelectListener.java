package com.zhangqie.module_square.widget;

public interface TabSelectListener {
    /**
     * 选中
     */
    void onSelect(int index, int totalCount);

    /**
     * 未选中
     */
    void onDeselected(int index, int totalCount);
}
