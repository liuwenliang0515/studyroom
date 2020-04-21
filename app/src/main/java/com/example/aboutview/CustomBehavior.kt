package com.example.aboutview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

class CustomBehavior : CoordinatorLayout.Behavior<View> {

    constructor() : super()
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

}