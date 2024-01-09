package com.prolificinteractive.materialcalendarview.decorators

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class SelectedRangeDecorator(val widget: MaterialCalendarView, private val selectedColor: Int) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay): Boolean {
        // Decorate all days in the selected range
        val selectedRange: List<CalendarDay> = widget.selectedDates
        return selectedRange.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        // Set the text color for the selected range
        view.addSpan(ForegroundColorSpan(selectedColor))
    }
}