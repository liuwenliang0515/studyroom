package com.example.aboutview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.OverScroller
import androidx.core.math.MathUtils
import kotlin.math.max
import kotlin.math.min

class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var onImageClick = {}

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var image: Bitmap
    private var imageWidth = 0
    private var imageHeight = 0
    private var maxScale = 0f

    private var mInitScale = 1f
    private var mCurrentScale = 1f
        get() = field
        set(value) {
            field = value
            if (mTargetScale != 1f) {
                offsetWhenScale(mFocusX, mFocusY, mPreScale, mCurrentScale)
            }
            mPreScale = mCurrentScale
            invalidate()
        }
    private var mPreScale = mCurrentScale
    private var mOffsetX = 0f
    private var mOffsetY = 0f
    private var mFocusX = 0f
    private var mFocusY = 0f
    private var mTargetScale = 0f

    private var scroller = OverScroller(context)

    private var scaleAnimator = ObjectAnimator.ofFloat(this, "mCurrentScale", 0f)

    private var gestureDetector =
        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent?): Boolean {
                return true
            }

            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                onImageClick()
                return super.onSingleTapConfirmed(e)
            }

            override fun onDoubleTap(e: MotionEvent?): Boolean {
                mTargetScale = if (mCurrentScale < maxScale / 2) maxScale else 1f
                scaleAnimator.setFloatValues(mCurrentScale, mTargetScale)

                e?.let {
                    mFocusX = it.x
                    mFocusY = it.y
                }

                mPreScale = mCurrentScale

                scaleAnimator.start()

                return super.onDoubleTap(e)
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                mOffsetX -= distanceX
                mOffsetY -= distanceY
                invalidate()
                return false
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                scroller.fling(
                    mOffsetX.toInt(), mOffsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                    (-(imageWidth * mCurrentScale - width) / 2).toInt(),
                    ((imageWidth * mCurrentScale - width) / 2).toInt(),
                    (-(imageHeight * mCurrentScale - height) / 2).toInt(),
                    ((imageHeight * mCurrentScale - height) / 2).toInt()
                )
                postInvalidateOnAnimation()
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        })

    private var scaleDetector =
        ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

            override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
                mInitScale = mCurrentScale
                return super.onScaleBegin(detector)
            }

            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                detector?.let {
                    mFocusX = it.focusX
                    mFocusY = it.focusY
                    mTargetScale = MathUtils.clamp(mInitScale * it.scaleFactor, 1f, maxScale)
                    mCurrentScale = mTargetScale
                }
                mPreScale = mCurrentScale

                invalidate()
                return super.onScale(detector)
            }
        })

    fun setImageSource(srcId: Int) {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, srcId, options)
        options.inDensity = options.outWidth
        options.inTargetDensity = measuredWidth

        imageWidth = measuredWidth
        imageHeight = (options.outHeight * measuredWidth / options.outWidth.toFloat()).toInt()

        options.inJustDecodeBounds = false
        image = BitmapFactory.decodeResource(resources, srcId, options)

        maxScale = height / imageHeight.toFloat() + 0.2f

        invalidate()

        scaleAnimator.interpolator = LinearInterpolator()
        scaleAnimator.duration = 300
    }

    override fun computeScroll() {
        if (scroller.computeScrollOffset()) {
            mOffsetX = scroller.currX.toFloat()
            mOffsetY = scroller.currY.toFloat()
            postInvalidateOnAnimation()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            fixOffset()
            canvas.translate(mOffsetX, mOffsetY)
            canvas.scale(mCurrentScale, mCurrentScale, width / 2f, height / 2f)
            canvas.drawBitmap(image, 0f, (measuredHeight - imageHeight) / 2f, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var res = scaleDetector.onTouchEvent(event)
        if (!scaleDetector.isInProgress) {
            res = gestureDetector.onTouchEvent(event)
        }
        return res
    }

    private fun offsetWhenScale(x: Float, y: Float, currentScale: Float, targetScale: Float) {
        mOffsetX += (x - width / 2 - mOffsetX) - (x - width / 2 - mOffsetX) / currentScale * targetScale
        mOffsetY += (y - height / 2 - mOffsetY) - (y - height / 2 - mOffsetY) / currentScale * targetScale
    }

    private fun fixOffset() {
        val maxOffsetX = max((imageWidth * mCurrentScale - width) / 2, 0f)
        val maxOffsetY = max((imageHeight * mCurrentScale - height) / 2, 0f)
        mOffsetX = if (mOffsetX > 0) {
            min(maxOffsetX, mOffsetX)
        } else {
            max(-maxOffsetX, mOffsetX)
        }

        mOffsetY = if (mOffsetY > 0) {
            min(maxOffsetY, mOffsetY)
        } else {
            max(-maxOffsetY, mOffsetY)
        }
    }

}