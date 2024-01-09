package com.prolificinteractive.materialcalendarview.sample;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

/**
 * Created by maragues on 17/06/16.
 */
public class CustomTileDimensions extends AppCompatActivity {

  MaterialCalendarView widget;

  private int currentTileWidth;
  private int currentTileHeight;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_custom_tile);

    widget = findViewById(R.id.calendarView);

    currentTileWidth = MaterialCalendarView.DEFAULT_TILE_SIZE_DP;
    currentTileHeight = MaterialCalendarView.DEFAULT_TILE_SIZE_DP;

    widget.addDecorator(new TodayDecorator());

    findViewById(R.id.custom_tile_match_parent).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        widget.setTileSize(LinearLayout.LayoutParams.MATCH_PARENT);
      }
    });

    findViewById(R.id.custom_tile_width_match_parent).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        widget.setTileWidth(LinearLayout.LayoutParams.MATCH_PARENT);
      }
    });

    findViewById(R.id.custom_tile_height_match_parent).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        widget.setTileHeight(LinearLayout.LayoutParams.MATCH_PARENT);
      }
    });

    findViewById(R.id.custom_tile_width_size).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final NumberPicker view2 = new NumberPicker(CustomTileDimensions.this);
        view2.setMinValue(24);
        view2.setMaxValue(64);
        view2.setWrapSelectorWheel(false);
        view2.setValue(currentTileWidth);
        new AlertDialog.Builder(CustomTileDimensions.this)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(@NonNull DialogInterface dialog, int which) {
                    currentTileWidth = view2.getValue();
                    widget.setTileWidthDp(currentTileWidth);
                  }
                })
                .show();
      }
    });

    findViewById(R.id.custom_tile_height_size).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final NumberPicker view3 = new NumberPicker(CustomTileDimensions.this);
        view3.setMinValue(24);
        view3.setMaxValue(64);
        view3.setWrapSelectorWheel(false);
        view3.setValue(currentTileHeight);
        new AlertDialog.Builder(CustomTileDimensions.this)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(@NonNull DialogInterface dialog, int which) {
                    currentTileHeight = view3.getValue();
                    widget.setTileHeightDp(currentTileHeight);
                  }
                })
                .show();
      }
    });

  }

  private class TodayDecorator implements DayViewDecorator {

    private final CalendarDay today;
    private final Drawable backgroundDrawable;

    public TodayDecorator() {
      today = CalendarDay.today();
      backgroundDrawable = getResources().getDrawable(R.drawable.today_circle_background);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
      return today.equals(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
      view.setBackgroundDrawable(backgroundDrawable);
    }
  }
}
