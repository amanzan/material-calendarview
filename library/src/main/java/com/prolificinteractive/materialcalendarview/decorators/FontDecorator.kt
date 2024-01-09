package com.prolificinteractive.materialcalendarview.decorators

import android.graphics.Typeface
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.CustomTypefaceSpan

class FontDecorator(private val typeface: Typeface?) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return true
    }

    override fun decorate(view: DayViewFacade) {
        typeface?.let {
            val typefaceSpan = CustomTypefaceSpan(it)
            view.addSpan(typefaceSpan)
        }
    }
}
