package com.example.weborganizer.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.list2.R;
import com.example.weborganizer.DatabaseWorker;

/**
 * Created by Sasha on 28.11.13.
 */
public class Test extends Activity {
    DatabaseWorker databaseWorker;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layaut);
        databaseWorker=new DatabaseWorker(this);
        textView=(TextView)findViewById(R.id.textView2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"getNotSyncTask");
        menu.add(0,2,0,"between");
        menu.add(0,3,0,"contactCount");
        menu.add(0,4,0,"taskCount");
        menu.add(0,5,0,"maxContacts");
        menu.add(0,6,0,"mail");
        menu.add(0,7,0,"null");
        menu.add(0,8,0,"Update");
        menu.add(0,9,0,"Union");
        //getMenuInflater().inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1: databaseWorker.getNotSyncTask(DatabaseWorker.EDIT_TYPE_INSERT);
                break;
            case 2:textView.setText(databaseWorker.between());
                break;
            case 3:textView.setText(databaseWorker.contactCount(0));
                break;
            case 4:textView.setText(databaseWorker.taskCount());
                break;
            case 5:
                textView.setText(databaseWorker.maxContacts());
                break;
            case 6:textView.setText(databaseWorker.maxContacts("mail.com"));
                break;
            case 7:textView.setText(databaseWorker.notNull(0));
                break;
            case 8:textView.setText(databaseWorker.updatePar());
                break;
            case 9:textView.setText(databaseWorker.union());
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
