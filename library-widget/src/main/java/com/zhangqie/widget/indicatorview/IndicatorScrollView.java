package com.zhangqie.widget.indicatorview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class IndicatorScrollView extends NestedScrollView implements NestedScrollView.OnScrollChangeListener {

    private OnScrollChangeListener onUserScrollChangeListener;
    private OnScrollChangedListener onScrollChangedListener;

    @Override
    public void setOnScrollChangeListener(@Nullable OnScrollChangeListener onScrollChangeListener) {
        super.setOnScrollChangeListener(this);
        if (onScrollChangeListener != this) {
            this.onUserScrollChangeListener = onScrollChangeListener;
        }
    }

    public IndicatorScrollView(@NonNull Context context) {
        super(context);
    }

    public IndicatorScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IndicatorScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOnScrollChangeListener(this);
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (onUserScrollChangeListener != null) {
            onUserScrollChangeListener.onScrollChange(nestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY);
        }
        if (nestedScrollView != null) {
            if (getChildCount() > 0) {
                if (onScrollChangedListener != null) {
                    onScrollChangedListener.onChanged(scrollX, scrollY,
                            Math.abs(getMeasuredHeight() - getChildAt(0).getMeasuredHeight()));
                }
            }
        }
    }

    public void bindIndicatorView(IndicatorView indicatorView) {
        onScrollChangedListener = indicatorView;
    }
}
