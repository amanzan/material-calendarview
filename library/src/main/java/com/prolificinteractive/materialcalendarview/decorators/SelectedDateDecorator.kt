package com.prolificinteractive.materialcalendarview.decorators

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class SelectedDateDecorator(val widget: MaterialCalendarView, private val selectedColor: Int) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay): Boolean {
        // Decorate if the day is the selected date
        val selectedDate = widget.selectedDate
        return selectedDate != null && selectedDate == day
    }

    override fun decorate(view: DayViewFacade) {
        // Set the text color for the selected date
        view.addSpan(ForegroundColorSpan(selectedColor))
    }
}
