package com.prolificinteractive.materialcalendarview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class CustomizeXmlActivity extends AppCompatActivity {

  MaterialCalendarView widget;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customization);

    widget = findViewById(R.id.calendarView);
  }
}
