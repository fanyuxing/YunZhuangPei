package com.pcassem.yunzhuangpei.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.renderscript.Sampler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.pcassem.yunzhuangpei.training.activities.TrainingCoursesActivity;

/**
 * Created by zhangqi on 2017/12/4.
 */

public class AnimationUtils {

    private float mDensity = 0;
    private int mHiddenViewMeasuredHeight;


    public AnimationUtils(Context context, int height) {
        mDensity = getDensity(context);
        mHiddenViewMeasuredHeight = (int) (mDensity * height + 0.5);
    }


    public float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public void animate(View v,int start,int end){
        ValueAnimator animator = createDropAnimator(v,start,end);
        animator.start();
    }


    public void animateOpen(View v) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0, mHiddenViewMeasuredHeight);
        animator.start();
    }

    public void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);

            }
        });
        return animator;
    }

}
