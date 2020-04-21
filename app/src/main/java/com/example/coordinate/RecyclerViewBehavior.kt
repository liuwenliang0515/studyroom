package com.example.coordinate

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

val HEADER_DEFAULT_HEIGHT = 300.toPx()
val HEADER_MIN_HEIGHT = 100.toPx()
val HEADER_MAX_HEIGHT = 400.toPx()
val HEADER_OVER_SCROLL = HEADER_MAX_HEIGHT - HEADER_DEFAULT_HEIGHT
val TAB_HEIGHT = 40.toPx()
const val HEADER_MAX_SCALE = 1f

class RecyclerViewBehavior(context: Context?, attrs: AttributeSet?) : HeaderScrollingViewBehavior(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return (dependency.layoutParams as? CoordinatorLayout.LayoutParams)?.behavior is TabBehavior
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        ViewCompat.offsetTopAndBottom(child, dependency.bottom - child.top)
        return super.onDependentViewChanged(parent, child, dependency)
    }

    override fun findFirstDependency(views: MutableList<View>?): View? {
        return views?.find {
            (it.layoutParams as? CoordinatorLayout.LayoutParams)?.behavior is TabBehavior
        }
    }

    override fun getScrollRange(v: View): Int {
        return -HEADER_MIN_HEIGHT + TAB_HEIGHT
    }

}