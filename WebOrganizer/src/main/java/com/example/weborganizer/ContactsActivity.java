package com.example.weborganizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.list2.R;
import com.example.weborganizer.Containers.Contact;

import java.util.ArrayList;

/**
 * Created by Sasha on 23.11.13.
 */
public class ContactsActivity extends Activity {

    ArrayList<Contact> contactArrayList = new ArrayList<Contact>();
    ListAdapter listAdapter;
    EditText search;
    DatabaseWorker databaseWorker;
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
         databaseWorker =new DatabaseWorker(this);
        contactArrayList=databaseWorker.getContacts();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);

        // создаем адаптер

        listAdapter = new ListAdapter(this, contactArrayList);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.listView);
        lvMain.setAdapter(listAdapter);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        /** Create an option menu from res/menu/items.xml */
        getMenuInflater().inflate(R.menu.search_manu, menu);

        /** Get the action view of the menu item whose id is search */
        View v = (View) menu.findItem(R.id.search).getActionView();

        /** Get the edit text from the action view */
        EditText txtSearch = ( EditText ) v.findViewById(R.id.search_text);

        /** Setting an action listener */
        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(getBaseContext(), "Search : " + v.getText(), Toast.LENGTH_SHORT).show();
                listAdapter.update(databaseWorker.searchContacts(v.getText().toString()));
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // ClipData.Item item1 = (Item)findViewById(R.id.i)
        switch (item.getItemId()){
            case R.id.item1:
                Intent intent = new Intent(this,EnterContacts.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private TextWatcher filterTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {
            Log.e("TextWatcher","after");
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.e("TextWatcher","before");
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.e("TextWatcher","onText");
        }
    };

}