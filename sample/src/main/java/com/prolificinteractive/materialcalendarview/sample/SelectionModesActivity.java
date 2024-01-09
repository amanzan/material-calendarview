package com.prolificinteractive.materialcalendarview.sample;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;
import com.prolificinteractive.materialcalendarview.sample.decorators.RangeDayDecorator;
import java.util.List;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * An activity that demonstrate the multiple selection mode that the calendar offers.
 * The mode can be set through xml or in java.
 *
 * @see MaterialCalendarView.SelectionMode
 */
public class SelectionModesActivity extends AppCompatActivity
    implements OnDateSelectedListener, OnRangeSelectedListener {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");

  ViewGroup parent;
  MaterialCalendarView single;
  MaterialCalendarView multi;
  MaterialCalendarView range;
  MaterialCalendarView none;

  private RangeDayDecorator decorator;

  @Override protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_selection_modes);

    parent = findViewById(R.id.parent);
    single = findViewById(R.id.calendar_view_single);
    multi = findViewById(R.id.calendar_view_multi);
    range = findViewById(R.id.calendar_view_range);
    none = findViewById(R.id.calendar_view_none);

    decorator = new RangeDayDecorator(this);

    single.setOnDateChangedListener(this);
    multi.setOnDateChangedListener(this);
    range.setOnDateChangedListener(this);
    range.setOnRangeSelectedListener(this);
    range.addDecorator(decorator);
    none.setOnDateChangedListener(this);

    CheckBox checkBox = findViewById(R.id.calendar_mode);
    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
          TransitionManager.beginDelayedTransition(parent);
        }
        final CalendarMode mode = checked ? CalendarMode.WEEKS : CalendarMode.MONTHS;
        single.state().edit().setCalendarDisplayMode(mode).commit();
        multi.state().edit().setCalendarDisplayMode(mode).commit();
        range.state().edit().setCalendarDisplayMode(mode).commit();
        none.state().edit().setCalendarDisplayMode(mode).commit();
      }
    });
  }

  @Override public void onDateSelected(
      @NonNull final MaterialCalendarView widget,
      @NonNull final CalendarDay date,
      final boolean selected) {
    final String text = selected ? FORMATTER.format(date.getDate()) : "No Selection";
    Toast.makeText(SelectionModesActivity.this, text, Toast.LENGTH_SHORT).show();
  }

  @Override public void onRangeSelected(
      @NonNull final MaterialCalendarView widget,
      @NonNull final List<CalendarDay> dates) {
    if (dates.size() > 0) {
      decorator.addFirstAndLast(dates.get(0), dates.get(dates.size() - 1));
      range.invalidateDecorators();
    }
  }
}
