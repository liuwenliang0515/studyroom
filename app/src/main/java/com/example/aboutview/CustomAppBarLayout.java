package com.example.aboutview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.appbar.AppBarLayout;

public class CustomAppBarLayout extends AppBarLayout {
    public CustomAppBarLayout(@NonNull Context context) {
        super(context);
    }

    public CustomAppBarLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomAppBarLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        Log.d("lwl", "app bar layout request layout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("lwl", "app bar layout onmeasure");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("lwl", "app bar layout onlayout");
    }
}
