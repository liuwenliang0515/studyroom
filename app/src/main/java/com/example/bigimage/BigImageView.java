package com.example.bigimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

public class BigImageView extends View implements GestureDetector.OnGestureListener {
    private static final String TAG = "BigImageView";

    private BitmapRegionDecoder mDecoder;

    //绘制的区域
    private volatile Rect mRect = new Rect();

    private int mScaledTouchSlop;

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    //图片的宽度和高度
    private int mImageWidth, mImageHeight;
    //手势控制器
    private GestureDetector mGestureDetector;
    private BitmapFactory.Options options;
    BitmapFactory.Options tmpOptions;
    private OverScroller mScroller;

    public BigImageView(Context context) {
        super(context);
        init(context, null);
    }

    public BigImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BigImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
         mScroller = new OverScroller(context);

        //设置显示图片的参数，如果对图片质量有要求，就选择ARGB_8888模式
        options = new BitmapFactory.Options();
//        options.inPreferredConfig = Bitmap.Config.RGB_565;

        mScaledTouchSlop = ViewConfiguration.get(getContext())
                .getScaledTouchSlop();
        Log.d(TAG, "sts:" + mScaledTouchSlop);
        //初始化手势控制器
        mGestureDetector = new GestureDetector(context, this);

        //获取图片的宽高
        InputStream is = null;
        try {
            is = context.getResources().getAssets().open("image.jpg");
            //初始化BitmapRegionDecode，并用它来显示图片
            mDecoder = BitmapRegionDecoder
                    .newInstance(is, false);
            tmpOptions = new BitmapFactory.Options();
            // 设置为true则只获取图片的宽高等信息，不加载进内存
            tmpOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, tmpOptions);
            mImageWidth = 1080;
            mImageHeight = 9306;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
//                if (is != null) {
//                    is.reset();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //把触摸事件交给手势控制器处理
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        mLastX = (int) e.getRawX();
        mLastY = (int) e.getRawY();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        int x = (int) e2.getRawX();
        int y = (int) e2.getRawY();
        move(x, y);

        return true;
    }

    /**
     * 移动的时候更新图片显示的区域
     *
     * @param x
     * @param y
     */
    private void move(int x, int y) {
        int deltaX = x - mLastX;
        int deltaY = y - mLastY;
        Log.d(TAG, "move, deltaX:" + deltaX + " deltaY:" + deltaY);
        //如果图片宽度大于屏幕宽度
        if (mImageWidth > getWidth()) {
            //移动rect区域
            mRect.offset(-deltaX, 0);
            //检查是否到达图片最右端
            if (mRect.right > mImageWidth) {
                mRect.right = mImageWidth;
                mRect.left = mImageWidth - getWidth();
            }

            //检查左端
            if (mRect.left < 0) {
                mRect.left = 0;
                mRect.right = getWidth();
            }

            invalidate();
        }
        //如果图片高度大于屏幕高度
        if (mImageHeight > getHeight()) {
            mRect.offset(0, -deltaY);

            //是否到达最底部
            if (mRect.bottom > mImageHeight) {
                mRect.bottom = mImageHeight;
                mRect.top = mImageHeight - getHeight();
            }

            if (mRect.top < 0) {
                mRect.top = 0;
                mRect.bottom = getHeight();
            }
            //重绘
            invalidate();
        }


        mLastX = x;
        mLastY = y;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        mLastX = (int) e.getRawX();
        mLastY = (int) e.getRawY();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

//        mScroller.fling(mRect.left, mRect.top, (int) velocityX, (int) velocityY, 0, getMeasuredHeight() - mImageWidth, 0, getMeasuredWidth() - mImageHeight);
//        postOnAnimation(new Runnable() {
//            @Override
//            public void run() {
//                startFling();
//            }
//        });

        return true;
    }

    private void startFling() {
        if (mScroller.computeScrollOffset()) {
            move(mScroller.getCurrX(), mScroller.getCurrY());
            postOnAnimation(new Runnable() {
                @Override
                public void run() {
                    startFling();
                }
            });
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //显示图片
        Bitmap bm = mDecoder.decodeRegion(mRect, options);
        canvas.drawBitmap(bm, 0, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        options.inDensity = tmpOptions.outWidth;
        options.inTargetDensity = getMeasuredWidth();

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;

        //默认显示图片的中心区域，开发者可自行选择
        mRect.left = imageWidth / 2 - width / 2;
        mRect.top = imageHeight / 2 - height / 2;
        mRect.right = mRect.left + width;
        mRect.bottom = mRect.top + height;

    }
}
