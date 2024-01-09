package com.prolificinteractive.materialcalendarview.decorators

import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class DaysColorDecorator(private val daysColor: Int) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return true
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(ForegroundColorSpan(daysColor))
    }
}