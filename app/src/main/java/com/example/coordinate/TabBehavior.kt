package com.example.coordinate

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

class TabBehavior(context: Context?, attrs: AttributeSet?) : HeaderScrollingViewBehavior(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return (dependency.layoutParams as? CoordinatorLayout.LayoutParams)?.behavior is HeaderBehavior
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        topAndBottomOffset = dependency.bottom - child.top - HEADER_OVER_SCROLL - TAB_HEIGHT
        return super.onDependentViewChanged(parent, child, dependency)
    }

    override fun findFirstDependency(views: MutableList<View>?): View? {
        return views?.find {
            (it.layoutParams as? CoordinatorLayout.LayoutParams)?.behavior is HeaderBehavior
        }
    }

    override fun getScrollRange(v: View): Int {
        return super.getScrollRange(v) - HEADER_MIN_HEIGHT
    }

}