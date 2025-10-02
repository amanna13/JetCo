package com.developerstring.jetco.ui.converters

import android.content.Context
import android.util.TypedValue

// Extension function to convert DP to PX
fun Context.dpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        this.resources.displayMetrics
    )
}

// Extension function to convert SP to PX
fun Context.spToPx(sp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp,
        this.resources.displayMetrics
    )
}

// Extension function to convert PX to DP
fun Context.pxToDp(px: Float): Float {
    return px / this.resources.displayMetrics.density
}