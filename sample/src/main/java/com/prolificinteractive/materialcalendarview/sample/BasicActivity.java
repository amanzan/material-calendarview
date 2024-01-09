package com.prolificinteractive.materialcalendarview.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateLongClickListener;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

/**
 * Shows off the most basic usage
 */
public class BasicActivity extends AppCompatActivity
    implements OnDateSelectedListener, OnMonthChangedListener, OnDateLongClickListener {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");

  @BindView(R.id.calendarView)
  MaterialCalendarView widget;

  @BindView(R.id.textView)
  TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basic);
    ButterKnife.bind(this);

    widget.setOnDateChangedListener(this);
    widget.setOnDateLongClickListener(this);
    widget.setOnMonthChangedListener(this);

    widget.setSelectionColor(Color.GREEN);

    widget.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE);


    //Setup initial text
    textView.setText("No Selection");

    // Set OnDateSelectedListener to handle date selection
    widget.setOnDateChangedListener(new OnDateSelectedListener() {
      @Override
      public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
        // Handle date selection
        widget.invalidateDecorators();
      }
    });

    // Set OnRangeSelectedListener to handle range selection
    widget.setOnRangeSelectedListener(new OnRangeSelectedListener() {
      @Override
      public void onRangeSelected(MaterialCalendarView widget, List<CalendarDay> dates) {
        // Handle range selection
        widget.invalidateDecorators();
      }
    });

    // Add custom decorators
    widget.addDecorator(new SelectedDateDecorator());
    widget.addDecorator(new SelectedRangeDecorator());
  }

  private class SelectedDateDecorator implements DayViewDecorator {
    @Override
    public boolean shouldDecorate(CalendarDay day) {
      // Decorate if the day is the selected date
      CalendarDay selectedDate = widget.getSelectedDate();
      return selectedDate != null && selectedDate.equals(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
      // Set the text color for the selected date
      view.addSpan(new ForegroundColorSpan(Color.RED));
    }
  }

  // Custom decorator for the selected range
  private class SelectedRangeDecorator implements DayViewDecorator {
    @Override
    public boolean shouldDecorate(CalendarDay day) {
      // Decorate all days in the selected range
      List<CalendarDay> selectedRange = widget.getSelectedDates();
      return selectedRange != null && selectedRange.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
      // Set the text color for the selected range
      view.addSpan(new ForegroundColorSpan(Color.RED));
    }
  }

  @Override
  public void onDateSelected(
      @NonNull MaterialCalendarView widget,
      @NonNull CalendarDay date,
      boolean selected) {
    textView.setText(selected ? FORMATTER.format(date.getDate()) : "No Selection");
  }

  @Override
  public void onDateLongClick(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date) {
    final String text = String.format("%s is available", FORMATTER.format(date.getDate()));
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
    //noinspection ConstantConditions
    getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
  }
}
