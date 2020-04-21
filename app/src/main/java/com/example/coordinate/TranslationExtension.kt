package com.example.coordinate

import android.content.res.Resources

fun Int.toPx(): Int {
    return (Resources.getSystem().getDisplayMetrics().scaledDensity * this).toInt()
}