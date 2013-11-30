package com.example.weborganizer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
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


    TextView tvDate;
    TextView tvTime;
    EditText editTitle;
    EditText editText;
    TextView TEMP;
    Spinner spinner;
    Task task;
    int DIALOG_TIME = 1;
    int DIALOG_DATE = 2;
    int myHour, myMinute , myYear , myMonth ,myDay ;
    boolean update;
    DatabaseWorker databaseWorker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_enther);
        task= new Task(null,null,null,null,null, (byte) 0,0,0);
        Intent intent = getIntent();
        if(intent.hasExtra(DatabaseWorker.collTaskLast_Editing)){
            task.taskTitle=getIntent().getStringExtra(DatabaseWorker.collTaskTitle);
            task.taskText=intent.getStringExtra(DatabaseWorker.collTaskText);
            task.taskTime=intent.getStringExtra(DatabaseWorker.collTaskTime);
            task.taskDate=intent.getStringExtra(DatabaseWorker.collTaskDate);
            task.lastTaskLastEditing=intent.getStringExtra(DatabaseWorker.collTaskLast_Editing);
            task.taskFilterId=intent.getIntExtra(DatabaseWorker.collTaskFilter,2);
            Log.d("TASK",task.toString());
            update=true;
        }


        tvDate = (TextView) findViewById(R.id.textView2);
        tvTime=(TextView)findViewById(R.id.textView3);
        TEMP=(TextView)findViewById(R.id.textView);
        editTitle=(EditText)findViewById(R.id.editText);
        editText=(EditText)findViewById(R.id.editText2);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        if(task.taskDate==null){
            tvDate.setText(format1.format(calendar.getTime()));
            Date date = calendar.getTime();

            myYear=calendar.get(Calendar.YEAR);//format1.format();
            myMonth =calendar.get(Calendar.MONTH);
            myDay=calendar.get(Calendar.DATE);
            task.taskDate=myYear+"-"+myMonth+"-"+myDay;
        }else{tvDate.setText(task.taskDate);
//            String[] arr=task.taskDate.split("-");
//            myYear=calendar.get(Integer.getInteger(arr[0]));//format1.format();
//            myMonth =calendar.get(Integer.getInteger(arr[1]));
//            myDay=calendar.get(Integer.getInteger(arr[2]));
        }

        if(task.taskTime==null)
        {
            tvTime.setText(new SimpleDateFormat("HH:mm:ss").format(calendar.getTime()));
            myHour=calendar.get(Calendar.HOUR_OF_DAY);
            myMinute=calendar.get(Calendar.MINUTE);
            task.taskTime=myHour+":"+myMinute+":00";
        }else{tvTime.setText(task.taskTime);}
        if(task.taskText!=null||task.taskText!="null")
        {
            editText.setText(task.taskText);
        }
        if(task.taskTitle!=null)
        {
            editTitle.setText(task.taskTitle);
        }
//tvDate.setText(databaseWorker.getTasks().toString());



         databaseWorker =new DatabaseWorker(this);
        spinner= (Spinner) findViewById(R.id.spinner1);

        SpinnerAdapter spinnerAdapter  =  new SpinnerAdapter(this,android.R.layout.simple_spinner_item,databaseWorker.getFilters());
        spinner.setAdapter(spinnerAdapter);

        spinner.setPrompt("Title");

        spinner.setSelection(task.taskFilterId-1);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Filter f = (Filter)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),f.filterId+" "+f.filterName, Toast.LENGTH_SHORT).show();
                task.taskFilterId=f.filterId;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//
    }
    public void onclick(View view) {
        switch (view.getId()){
            case R.id.textView2:
                showDialog(DIALOG_DATE);
                break;
            case R.id.textView3:
                showDialog(DIALOG_TIME);
                break;
            default:
                break;
        }

    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return tpd;
        }else if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack1, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            tvTime.setText( myHour + ":" + myMinute + ":"+"00");
            task.taskTime=hourOfDay+":"+minute+":00";
        }
    };
    DatePickerDialog.OnDateSetListener myCallBack1 = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = ++monthOfYear;
            myDay = dayOfMonth;
            tvDate.setText( myDay + "-" + myMonth + "-" + myYear);
            task.taskDate=year+"-"+monthOfYear+"-"+dayOfMonth;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_insert, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.item1:
                if(!editTitle.getText().toString().isEmpty())
                {
                    task.taskTitle=editTitle.getText().toString();
                    if(!editText.getText().toString().isEmpty()){
                        task.taskText=editText.getText().toString();
                    }
                    task.taskEditType=0;
                    task.userId=0;
                   // TEMP.setText(task.toString());
                    if(update){
                        databaseWorker.update(task);
                    }else{
                        Log.d("ADD",task.toString());
                        databaseWorker.insertTask(task);
                    }
                    Intent i = new Intent();
                    setResult(RESULT_OK,i);
                    finish();
                }else{

                    Toast.makeText(getApplicationContext(),getResources().getText(R.string.incorect_title), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.item2:
                //TEMP.setText(databaseWorker.getTasks().toString());

                break;
        }
       return true;
    }
}

