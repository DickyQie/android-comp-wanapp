package com.zhangqie.widget.viewpager.animviewpager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;


public class FixedSpeedScroller extends Scroller {
    private int scrollerDuration = 1000;

    public FixedSpeedScroller(Context context, int duration) {
        this(context, null, duration);
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator, int duration) {
        this(context, interpolator, false, duration);
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel, int duration) {
        super(context, interpolator, flywheel);
        this.scrollerDuration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, this.scrollerDuration);
    }
}
