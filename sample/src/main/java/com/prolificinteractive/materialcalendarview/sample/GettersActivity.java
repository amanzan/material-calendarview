package com.prolificinteractive.materialcalendarview.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import java.util.List;

/**
 * Because the calendar has a lot of getters method, this activity is here to demonstrate what each
 * getter is returning. For more information, make sure to check the documentation.
 */
public class GettersActivity extends AppCompatActivity {
  public static final CharSequence[] ITEMS =
      new CharSequence[] { "NONE", "SINGLE", "MULTIPLE", "RANGE" };

  MaterialCalendarView widget;

  @Override protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_getters);

    widget = findViewById(R.id.calendarView);

    CheckBox calendarMode = findViewById(R.id.calendar_mode);
    calendarMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        final CalendarMode mode = b ? CalendarMode.WEEKS : CalendarMode.MONTHS;
        widget.state().edit().setCalendarDisplayMode(mode).commit();
      }
    });

    findViewById(R.id.button_selection_mode).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        new AlertDialog.Builder(GettersActivity.this)
                .setTitle("Selection Mode")
                .setSingleChoiceItems(ITEMS, widget.getSelectionMode(), (dialog, which) -> {
                  widget.setSelectionMode(which);
                  dialog.dismiss();
                })
                .show();
      }
    });

    findViewById(R.id.get_current_date).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(GettersActivity.this, widget.getCurrentDate().toString(), Toast.LENGTH_SHORT).show();
        Log.e("GettersActivity", widget.getCurrentDate().toString());
      }
    });

    findViewById(R.id.get_selected_date).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final CalendarDay selectedDate = widget.getSelectedDate();
        if (selectedDate != null) {
          Toast.makeText(GettersActivity.this, selectedDate.toString(), Toast.LENGTH_SHORT).show();
          Log.e("GettersActivity", selectedDate.toString());
        } else {
          Toast.makeText(GettersActivity.this, "No Selection", Toast.LENGTH_SHORT).show();
        }
      }
    });

    findViewById(R.id.get_selected_dates).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final List<CalendarDay> selectedDates = widget.getSelectedDates();
        if (!selectedDates.isEmpty()) {
          Toast.makeText(GettersActivity.this, selectedDates.toString(), Toast.LENGTH_SHORT).show();
          Log.e("GettersActivity", selectedDates.toString());
        } else {
          Toast.makeText(GettersActivity.this, "No Selection", Toast.LENGTH_SHORT).show();
        }
      }
    });

    findViewById(R.id.get_selection_mode).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(GettersActivity.this, ITEMS[widget.getSelectionMode()], Toast.LENGTH_SHORT).show();
        Log.e("GettersActivity", ITEMS[widget.getSelectionMode()].toString());
      }
    });

  }
}
