package com.zhangqie.base.widget.shinebutton;

import android.animation.ValueAnimator;
import android.graphics.Canvas;

import com.zhangqie.base.widget.shinebutton.interpolator.Ease;
import com.zhangqie.base.widget.shinebutton.interpolator.EasingInterpolator;


public class ShineAnimator extends ValueAnimator {

    float MAX_VALUE = 1.5f;
    long ANIM_DURATION = 1500;
    Canvas canvas;

    ShineAnimator() {
        setFloatValues(1f, MAX_VALUE);
        setDuration(ANIM_DURATION);
        setStartDelay(200);
        setInterpolator(new EasingInterpolator(Ease.QUART_OUT));
    }
    ShineAnimator(long duration,float max_value,long delay) {
        setFloatValues(1f, max_value);
        setDuration(duration);
        setStartDelay(delay);
        setInterpolator(new EasingInterpolator(Ease.QUART_OUT));
    }

    void startAnim() {
        start();
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }


}
