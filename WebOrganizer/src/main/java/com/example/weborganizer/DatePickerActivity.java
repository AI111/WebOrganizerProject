package com.example.weborganizer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.list2.R;

import java.util.Calendar;

/**
 * Created by Sasha on 11.11.13.
 */
public class DatePickerActivity extends Activity {
    private CalendarView calendarView;
    private int yr, mon, dy;
    private Calendar selectedDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Calendar c = Calendar.getInstance();
        yr = c.get(Calendar.YEAR);
        mon = c.get(Calendar.MONTH);
        dy = c.get(Calendar.DAY_OF_MONTH);
        Button datePickerButton = (Button) findViewById(
                R.id.date_picker_button);
        calendarView = (CalendarView) findViewById(
                R.id.calendar_view);
        datePickerButton.setOnClickListener(new View.OnClickListener() {

              public void onClick(View v) {
               new DatePickerDialog(DatePickerActivity.this, dateListener, yr, mon, dy).show();
                                                        }
                                                    });
        calendarView.setOnDateChangeListener(new
                                                     CalendarView.OnDateChangeListener() {
                                                         @Override
                                                         public void onSelectedDayChange(CalendarView view,
                                                                                         int year, int month, int dayOfMonth) {
                                                             Toast.makeText(getApplicationContext(),"Selecteddate is "+(month+1)+"-"+dayOfMonth+"-"+
                                                                     year, Toast.LENGTH_SHORT). show();
                                                         }
                                                     });
    }

    private DatePickerDialog.OnDateSetListener dateListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int
                        monthOfYear, int dayOfMonth){
                    selectedDate=Calendar.getInstance();
                    yr=year;
                    mon=monthOfYear;
                    dy=dayOfMonth;
                    selectedDate.set(yr, mon, dy);
                    calendarView.setDate(selectedDate.getTimeInMillis());
                }
            };
}
