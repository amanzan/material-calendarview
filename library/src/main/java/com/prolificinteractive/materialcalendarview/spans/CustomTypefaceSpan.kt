package com.prolificinteractive.materialcalendarview.spans

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

class CustomTypefaceSpan(private val newType: Typeface) : TypefaceSpan("") {
    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeface(ds, newType)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeface(paint, newType)
    }

    private fun applyCustomTypeface(paint: Paint, tf: Typeface) {
        paint.setTypeface(tf)
    }
}