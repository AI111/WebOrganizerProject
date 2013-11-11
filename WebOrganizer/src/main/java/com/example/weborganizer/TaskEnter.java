package com.example.weborganizer;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.list2.R;
import com.example.weborganizer.Containers.Filter;
import com.example.weborganizer.Containers.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sasha on 07.11.13.
 */
public class TaskEnter extends Activity {
    int DIALOG_DATE = 1;
    int myYear = 2011;
    int myMonth = 02;
    int myDay = 03;
    TextView tvDate;
    TextView tvTime;
    Spinner spinner;
    Task task;
    int DIALOG_TIME = 1;
    int myHour = 14;
    int myMinute = 35;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_enther);

        task= new Task(null,null,null,null,null, (byte) 0,0,0);

        tvDate = (TextView) findViewById(R.id.textView2);
        tvTime=(TextView)findViewById(R.id.textView3);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        tvTime.setText(format1.format(calendar.getTime()));

        DatabaseWorker databaseWorker =new DatabaseWorker(this);
        spinner= (Spinner) findViewById(R.id.spinner1);

        SpinnerAdapter spinnerAdapter  =  new SpinnerAdapter(this,android.R.layout.simple_spinner_item,databaseWorker.getFilters());
        spinner.setAdapter(spinnerAdapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(2);

        tvDate.setText(databaseWorker.getTasks().toString());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Filter f = (Filter)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),f.filterId+" "+f.filterName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//
    }
    public void onclick(View view) {
        showDialog(DIALOG_TIME);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            tvTime.setText("Time is " + myHour + " hours " + myMinute + " minutes");
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.item1:
                break;
            case R.id.item2:
                break;
        }
       return true;
    }
}

