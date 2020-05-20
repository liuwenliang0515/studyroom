package com.example.aboutview

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.OverScroller
import androidx.core.math.MathUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.coordinate.toPx
import kotlin.math.max
import kotlin.math.min

class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var onDeviceClicked: (id: Int, rect: Rect) -> Unit = { _, _ -> }

    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var mImage: Bitmap
    private var mImageWidth = 0
    private var mImageHeight = 0
    private var mMinScale = 0f
    private var mMaxScale = 0f

    private var mBeginScale = 1f
    private var mCurrentScale = mMinScale
        get() = field
        set(value) {
            mPreScale = field
            field = value
            offsetWhenScale(mFocusX, mFocusY, mPreScale, mCurrentScale)
            invalidate()
        }
    private var mPreScale = mCurrentScale
    private var mOffsetX = 0f
    private var mOffsetY = 0f
    private var mFocusX = 0f
    private var mFocusY = 0f

    private var mRectPadding = 3.toPx()

    //tools
    private var mScroller = OverScroller(context)
    private lateinit var mScaleAnimator: ObjectAnimator
    private lateinit var mGestureDetector: GestureDetector
    private lateinit var mScaleDetector: ScaleGestureDetector

    //data
    private var mIsHighQuilty = false
    private var mImageUrl: String = ""
    private var mOriginalData: List<DeviceModel> = listOf()
    private var mDataList: ArrayList<LocaleModel> = arrayListOf()

    private var mSelectedData: LocaleModel? = null
    private var mHighLightData: List<LocaleModel> = listOf()

    init {
        //初始化工具类
        initPaint()
        initAnimator()
        initGestureDetector()
    }

    /**
     * 设置初始数据
     * @param url 图片url
     * @param data 数据源
     * @param isHighQuality true：高质量  false：低质量
     */
    fun setDataSource(url: String, data: List<DeviceModel>, isHighQuality: Boolean) {
        mIsHighQuilty = isHighQuality
        mImageUrl = url
        mOriginalData = data
        if (measuredWidth == 0) return
        initView()
    }

    /**
     * @param ids 需要高亮显示的零件id
     */
    fun setHighLightDataIds(ids: ArrayList<Int>) {
        mHighLightData = mDataList.filter {
            ids.contains(it.partId)
        }
        invalidate()
    }

    /**
     * 在postInvalidateOnAnimation()后被回调，处理fling的动作
     */
    override fun computeScroll() {
        //判断fling是否结束
        if (mScroller.computeScrollOffset()) {
            mOffsetX = mScroller.currX.toFloat()
            mOffsetY = mScroller.currY.toFloat()
            postInvalidateOnAnimation()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //当前是否有初始数据；如果没有数据，onDraw什么都不需要做
        if (mMinScale == 0f) return
        canvas?.apply {
            //修正offset值，限制其在一定范围内
            fixOffset()
            //做偏移
            translate(mOffsetX, mOffsetY)
            //放缩
            scale(mCurrentScale, mCurrentScale, width / 2f, height / 2f)
            //画图，保证图片初始在画面中心显示
            drawBitmap(mImage, (measuredWidth - mImageWidth) / 2f, (measuredHeight - mImageHeight) / 2f, mPaint)

            //draw highlight rects
            mPaint.color = Color.YELLOW
            mHighLightData.forEach {
                drawRect(it.translateRect, mPaint)
            }

            //draw selected rect
            mPaint.color = Color.RED
            mSelectedData?.let {
                drawRect(it.translateRect, mPaint)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //事件先交给mScaleDetector，如果返回false、说明当前不是双指操作，然后把事件交给mGestureDetector处理
        var res = mScaleDetector.onTouchEvent(event)
        if (!mScaleDetector.isInProgress) {
            res = mGestureDetector.onTouchEvent(event)
        }
        return res
    }

    /**
     * 初始化和屏幕旋转时的回调方法
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        initView()
        super.onSizeChanged(w, h, oldw, oldh)
    }

    /**
     * 初始化数据和图片
     */
    private fun initView() {
        //把API数据转换成本地数据，后续使用更方便
        mDataList.clear()
        mOriginalData.forEach { bean ->
            bean.illustrations.forEach { model ->
                model.positions.forEach {
                    mDataList.add(LocaleModel(bean.part_id, it))
                }
            }
        }

        //加载图片。 此处是加载的本地图片，后续可以改成url
        Glide.with(context).asBitmap().load(R.drawable.image_l)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    //获取原始图片size，根据size确定最小和最大缩放值
                    mImage = resource
                    mImageWidth = mImage.width
                    mImageHeight = mImage.height

                    val heightScale = measuredHeight / mImageHeight.toFloat()
                    val widthScale = measuredWidth / mImageWidth.toFloat()
                    if (heightScale < widthScale) {
                        mMinScale = heightScale
                        mMaxScale = width / mImageWidth.toFloat() * 2
                    } else {
                        mMinScale = widthScale
                        mMaxScale = height / mImageHeight.toFloat() * 2
                    }
                    mCurrentScale = mMinScale

                    //在屏幕转换时，下面三个转换方法，可以把坐标转换为当前正确的值
                    mDataList.forEach {
                        it.translateRect()
                    }

                    mHighLightData.forEach {
                        it.translateRect()
                    }

                    mSelectedData?.translateRect()

                    invalidate()
                }
            })
    }

    private fun initPaint() {
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 2.toPx().toFloat()
    }

    private fun initGestureDetector() {
        mGestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {

                override fun onDown(e: MotionEvent?): Boolean {
                    return true
                }

                /**
                 * 单击
                 */
                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    e?.let {event ->
                        mSelectedData = mDataList.find {
                            it.rectOnScreen.contains(event.x.toInt(), event.y.toInt())
                        }
                        mSelectedData?.let {
                            onDeviceClicked(it.partId, it.originalRect)
                        }
                        invalidate()
                    }

                    return super.onSingleTapConfirmed(e)
                }

                override fun onDoubleTap(e: MotionEvent?): Boolean {
                    e?.let {
                        mFocusX = it.x
                        mFocusY = it.y
                    }

                    mScaleAnimator.setFloatValues(mCurrentScale, if ((mCurrentScale - mMinScale) < (mMaxScale - mMinScale) / 2) mMaxScale else mMinScale)

                    mScaleAnimator.start()
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
                    mScroller.fling(
                        mOffsetX.toInt(), mOffsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                        (-(mImageWidth * mCurrentScale - width) / 2).toInt(),
                        ((mImageWidth * mCurrentScale - width) / 2).toInt(),
                        (-(mImageHeight * mCurrentScale - height) / 2).toInt(),
                        ((mImageHeight * mCurrentScale - height) / 2).toInt()
                    )
                    postInvalidateOnAnimation()
                    return super.onFling(e1, e2, velocityX, velocityY)
                }
            })

        mScaleDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

            override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
                mBeginScale = mCurrentScale
                return super.onScaleBegin(detector)
            }

            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                detector?.let {
                    mFocusX = it.focusX
                    mFocusY = it.focusY
                    mCurrentScale = MathUtils.clamp(mBeginScale * it.scaleFactor, mMinScale, mMaxScale)
                }

                invalidate()
                return super.onScale(detector)
            }
        })
    }

    private fun initAnimator() {
        mScaleAnimator = ObjectAnimator.ofFloat(this, "mCurrentScale", 0f)
        mScaleAnimator.interpolator = LinearInterpolator()
        mScaleAnimator.duration = 300
    }

    /**
     * 根据缩放值，计算当前需要的偏移值
     */
    private fun offsetWhenScale(x: Float, y: Float, currentScale: Float, targetScale: Float) {
        mOffsetX += (x - width / 2 - mOffsetX) - (x - width / 2 - mOffsetX) / currentScale * targetScale
        mOffsetY += (y - height / 2 - mOffsetY) - (y - height / 2 - mOffsetY) / currentScale * targetScale
    }

    /**
     * 把offset限定在范围之内
     */
    private fun fixOffset() {
        val maxOffsetX = max((mImageWidth * mCurrentScale - width) / 2, 0f)
        val maxOffsetY = max((mImageHeight * mCurrentScale - height) / 2, 0f)
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

    inner class LocaleModel(val partId: Int, val positions: DeviceModel.IllustrationsBean.PositionsBean) {
        val originalRect = Rect(
            (positions.start_x * if (mIsHighQuilty) 1.0 else 0.4).toInt(),
            (positions.start_y * if (mIsHighQuilty) 1.0 else 0.4).toInt(),
            (positions.end_x * if (mIsHighQuilty) 1.0 else 0.4).toInt(),
            (positions.end_y * if (mIsHighQuilty) 1.0 else 0.4).toInt()
        )
        var translateRect = Rect()

        /**
         * 把canvas的初始坐标，转换成缩放和位移后的真实坐标
         */
        var rectOnScreen = Rect()
            get() = field.apply {
                left = (measuredWidth / 2 - (measuredWidth / 2 - translateRect.left) * mCurrentScale + mOffsetX).toInt()
                right = (measuredWidth / 2 - (measuredWidth / 2 - translateRect.right) * mCurrentScale + mOffsetX).toInt()
                top = (measuredHeight / 2 - (measuredHeight / 2 - translateRect.top) * mCurrentScale + mOffsetY).toInt()
                bottom = (measuredHeight / 2 - (measuredHeight / 2 - translateRect.bottom) * mCurrentScale + mOffsetY).toInt()
            }

        fun translateRect() {
            /**
             * 把原始数据，转换成canvas坐标
             */
            translateRect.apply {
                left = originalRect.left + (measuredWidth - mImageWidth) / 2 - mRectPadding
                right = originalRect.right + (measuredWidth - mImageWidth) / 2 + mRectPadding
                top = originalRect.top + (measuredHeight - mImageHeight) / 2 - mRectPadding
                bottom = originalRect.bottom + (measuredHeight - mImageHeight) / 2 + mRectPadding
            }
        }
    }
}