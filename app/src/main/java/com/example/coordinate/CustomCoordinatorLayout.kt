package com.example.coordinate

import android.content.Context
import android.util.AttributeSet
import androidx.coordinatorlayout.widget.CoordinatorLayout

class CustomCoordinatorLayout(context: Context, attrs: AttributeSet?) :
    CoordinatorLayout(context, attrs) {

    override fun requestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }

}