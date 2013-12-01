package com.example.weborganizer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.list2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sasha on 27.11.13.
 */
public class EnterContacts extends Activity {
    int  myYear , myMonth ,myDay ;
    TextView tvDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_add);
        tvDate = (TextView)findViewById(R.id.textView4);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DAY_OF_YEAR, 1);

            tvDate.setText(format1.format(calendar.getTime()));
            Date date = calendar.getTime();

            myYear=calendar.get(Calendar.YEAR);//format1.format();
            myMonth =calendar.get(Calendar.MONTH);
            myDay=calendar.get(Calendar.DATE);

    }
    public void onclick(View view) {
        switch (view.getId()){
            case R.id.textView4:
                showDialog(1);
                break;
              default:
                break;
        }

    }
    protected Dialog onCreateDialog(int id) {
            if(id==1){
                DatePickerDialog tpd = new DatePickerDialog(this, myCallBack1, myYear, myMonth, myDay);
                return tpd;
            }
        return super.onCreateDialog(id);

    }


    DatePickerDialog.OnDateSetListener myCallBack1 = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = ++monthOfYear;
            myDay = dayOfMonth;
            tvDate.setText( myDay + "-" + myMonth + "-" + myYear);

        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_insert, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
