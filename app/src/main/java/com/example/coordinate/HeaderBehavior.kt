package com.example.coordinate

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.view.animation.DecelerateInterpolator
import android.widget.OverScroller
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class HeaderBehavior(context: Context?, attrs: AttributeSet?) :
    ViewOffsetBehavior<View>(context, attrs) {

    private var moveSlop = ViewConfiguration.get(context).scaledTouchSlop
    private var downY = 0f
    private var lastY = 0f
    private var isDragging = false

    private val velocityTracker = VelocityTracker.obtain()
    private var viewConfiguration = ViewConfiguration.get(context)
    private val maxVelocity = viewConfiguration.scaledMaximumFlingVelocity.toFloat()
    private val minVelocity = viewConfiguration.scaledMinimumFlingVelocity.toFloat()

    private val overScroller = OverScroller(context)

    private var isActionUp = false
    private var mDownEvent: MotionEvent? = null
    private var touchOffset = 0f

    //回弹
    private var revertAnimator: ValueAnimator? = null

    private var onOffsetChanged: (offset: Int) -> Unit = {}

    var nestedImage: View? = null
    var nestedCover: View? = null
    var contentViews: HashSet<View> = hashSetOf()

    override fun onMeasureChild(
        parent: CoordinatorLayout,
        child: View,
        parentWidthMeasureSpec: Int,
        widthUsed: Int,
        parentHeightMeasureSpec: Int,
        heightUsed: Int
    ): Boolean {
        Log.d("lwl", "on measure child")
        if ((child.layoutParams as? CoordinatorLayout.LayoutParams)?.behavior is HeaderBehavior) {
            child.translationY = -HEADER_OVER_SCROLL.toFloat()
        }
        return false
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        val result = (axes and ViewCompat.SCROLL_AXIS_VERTICAL) != 0
        if (result && getRevertAnimator(child).isRunning) {
            getRevertAnimator(child).cancel()
        }
        return result
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        if (dy > 0 || !target.canScrollVertically(-1)) {
            consumed[1] = offsetChildIfNeed(-dy, child, type)
        }
    }

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        type: Int
    ) {
        if (topAndBottomOffset > 0) {
            getRevertAnimator(child).start()
        }
    }

    private fun getRevertAnimator(child: View): ValueAnimator {
        if (revertAnimator == null) {
            revertAnimator = ValueAnimator()
            revertAnimator?.apply {
                duration = 300
                interpolator = DecelerateInterpolator()
                addUpdateListener {
                    doOffset(it.animatedValue as Int, child)
                }
            }
        }
        revertAnimator!!.setIntValues(topAndBottomOffset, 0)
        return revertAnimator!!
    }

    private fun offsetChildIfNeed(dy: Int, child: View, type: Int = ViewCompat.TYPE_TOUCH): Int {
        var used: Int
        val maxValue = if (type == ViewCompat.TYPE_TOUCH) HEADER_OVER_SCROLL else 0
        if (dy > 0) {
            used = if (topAndBottomOffset + dy > maxValue) maxValue - topAndBottomOffset else dy
            if (maxValue == 0) used = max(used, 0)
            doOffset(topAndBottomOffset + used, child)
        } else {
            used =
                if (abs(topAndBottomOffset + dy) < HEADER_DEFAULT_HEIGHT - HEADER_MIN_HEIGHT) dy else
                    (HEADER_DEFAULT_HEIGHT - HEADER_MIN_HEIGHT) - abs(topAndBottomOffset)
            doOffset(topAndBottomOffset - abs(used), child)
        }
        return abs(used)
    }

    private fun doOffset(offset: Int, child: View) {
        topAndBottomOffset = offset
        onOffsetChanged(topAndBottomOffset + HEADER_DEFAULT_HEIGHT - HEADER_MIN_HEIGHT)

        if (nestedImage == null || nestedCover == null) {
            (child as ViewGroup).apply {
                for (i in 0 until childCount) {
                    if (getChildAt(i).tag.toString() == "scale") {
                        nestedImage = getChildAt(i)
                    }
                    if (getChildAt(i).tag.toString() == "cover") {
                        nestedCover = getChildAt(i)
                    }
                    if (getChildAt(i).tag.toString() == "content") {
                        contentViews.add(getChildAt(i))
                    }
                }
            }
        }

        contentViews.forEach {
            it.alpha = 1 - (-topAndBottomOffset.toFloat() / (HEADER_DEFAULT_HEIGHT - HEADER_MIN_HEIGHT))
        }
        nestedCover?.alpha = max(0f, min(-topAndBottomOffset.toFloat() / (HEADER_DEFAULT_HEIGHT - HEADER_MIN_HEIGHT), 0.6f))
        nestedImage?.translationY = -(topAndBottomOffset / 1.5f)
        if (topAndBottomOffset > 0) {
            val scale: Float = topAndBottomOffset / HEADER_OVER_SCROLL.toFloat()
            nestedImage?.scaleX = HEADER_MAX_SCALE * scale + 1
            nestedImage?.scaleY = HEADER_MAX_SCALE * scale + 1
        }
    }

    public fun onOffsetChanged(listener: (offset: Int) -> Unit) {
        onOffsetChanged = listener
    }
}






//
//    override fun onInterceptTouchEvent(
//        parent: CoordinatorLayout,
//        child: View,
//        ev: MotionEvent
//    ): Boolean {
//        if (!parent.isPointInChildBounds(child, ev.x.toInt(), ev.y.toInt())) {
//            return false
//        }
//
//        if (ev.action == MotionEvent.ACTION_DOWN) {
//            velocityTracker.clear()
//            overScroller.forceFinished(true)
//        }
//
//        //event on header nestedImage
//        when (ev.action) {
//            MotionEvent.ACTION_DOWN -> {
//                if (mDownEvent != null) {
//                    mDownEvent!!.recycle()
//                    mDownEvent = null
//                }
//                mDownEvent = MotionEvent.obtain(ev)
//                downY = ev.y
//                lastY = ev.y
//            }
//            MotionEvent.ACTION_MOVE -> {
//                if (!isDragging && abs(ev.y - downY) > moveSlop) {
//                    isDragging = true
//                }
//                lastY = ev.y
//            }
//            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
//                isDragging = false
//            }
//        }
//        return false
//    }
//
//    override fun onTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
//        if (!parent.isPointInChildBounds(child, ev.x.toInt(), ev.y.toInt())) {
//            return false
//        }
//        velocityTracker.addMovement(ev)
//        when (ev.action) {
//            MotionEvent.ACTION_DOWN -> {
//                downY = ev.y
//                lastY = ev.y
//            }
//            MotionEvent.ACTION_MOVE -> {
//                if (isDragging) {
////                    offsetChildIfNeed((ev.y - lastY).toInt())
//                } else if (abs(ev.y - downY) > moveSlop) {
//                    isDragging = true
//                }
//                lastY = ev.y
//            }
//            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
//                if (topAndBottomOffset > 0) {
//                    Toast.makeText(child.context, "回弹", Toast.LENGTH_SHORT).show()
//                } else {
//                    velocityTracker.computeCurrentVelocity(1000, maxVelocity)
//                    Log.d("lwl", "speed = ${velocityTracker.yVelocity}")
//                    if (abs(velocityTracker.yVelocity) > minVelocity) {
////                        overScroller.fling(
////                            ev.x.toInt(),
////                            ev.y.toInt(),
////                            0,
////                            velocityTracker.yVelocity.toInt(),
////                            Integer.MIN_VALUE,
////                            Integer.MAX_VALUE,
////                            Integer.MIN_VALUE,
////                            Integer.MAX_VALUE
////                        )
////                        run()
//                    }
//                }
//            }
//        }
//
//        //把事件给recyclerview
//        if (isDragging) {
//            for (i in 0 until parent.childCount) {
//                if ((parent.getChildAt(i).layoutParams as? CoordinatorLayout.LayoutParams)?.behavior is RecyclerViewBehavior) {
//                    if (mDownEvent != null) {
////                        touchOffset = HEADER_MAX_HEIGHT - mDownEvent!!.y + 0.1f
//
//                        mDownEvent?.offsetLocation(0f, HEADER_MAX_HEIGHT.toFloat())
//                        parent.getChildAt(i).dispatchTouchEvent(mDownEvent)
//                        mDownEvent = null
//                    }
//                    ev.offsetLocation(0f, HEADER_MAX_HEIGHT.toFloat())
//                    parent.getChildAt(i).dispatchTouchEvent(ev)
//                }
//            }
//        }
//
//        if (isActionUp) {
//            isDragging = false
//            isActionUp = false
////            touchOffset = 0f
//        }
//
//        return true
//    }
