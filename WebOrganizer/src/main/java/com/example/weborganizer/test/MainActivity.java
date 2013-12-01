package com.example.weborganizer.test;

/**
 * Created by Sasha on 29.11.13.
 */


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.list2.R;

import java.util.ArrayList;

public class MainActivity extends Activity implements ActionBar.OnNavigationListener{

    // action bar
    private ActionBar actionBar;

    // Title navigation Spinner data
    private ArrayList<SpinnerNavItem> navSpinner,navItems;

    // Navigation adapter
    private TitleNavigationAdapter adapter,adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        //actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setIcon(R.drawable.ic_action_new);

        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflator.inflate(R.layout.custom_action_bar, null);



//       actionBar = getActionBar();
//
//        // Hide the action bar title
//        actionBar.setDisplayShowTitleEnabled(false);
//
//        // Enabling Spinner dropdown navigation
       actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//
//        // Spinner title navigation data
       // Spinner spinner = (Spinner)v.findViewById(R.id.spinner);
       // Spinner spinner1 =(Spinner)v.findViewById(R.id.spinner2);

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(), "Short", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        navSpinner = new ArrayList<SpinnerNavItem>();
        navSpinner.add(new SpinnerNavItem("", R.drawable.ic_action_new));
        navSpinner.add(new SpinnerNavItem("", R.drawable.abc_ic_clear));
        navSpinner.add(new SpinnerNavItem("", R.drawable.ic_action_labels));
        //navSpinner.add(new SpinnerNavItem("", R.drawable.icon));
        navItems=new ArrayList<SpinnerNavItem>();
        navItems.add(new SpinnerNavItem("Contacts", R.drawable.ic_action_new));
        navItems.add(new SpinnerNavItem("EXIT", R.drawable.abc_ic_clear));
        navItems.add(new SpinnerNavItem("SIGN in", R.drawable.ic_action_labels));

        // title drop down adapter
        adapter1=new TitleNavigationAdapter(getApplicationContext(),navItems,R.drawable.abc_ic_menu_moreoverflow_normal_holo_dark,null);
        adapter = new TitleNavigationAdapter(getApplicationContext(), navSpinner,R.drawable.ic_action_labels,null);
//        spinner.setAdapter(adapter);
//        spinner1.setAdapter(adapter1);
        actionBar.setCustomView(v);

//        // assigning the spinner navigation
//        LayoutInflater layoutInflater = LayoutInflater.from(this);
//        View view = layoutInflater.inflate(R.layout.test_btn, null);
//
//        ImageButton button = (ImageButton) view.findViewById(R.id.imageButton);
//        actionBar.setCustomView(view);

        //actionBar.setListNavigationCallbacks(adapter, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

     return false;
    }

    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       Toast.makeText(getBaseContext(), "Short", Toast.LENGTH_SHORT).show();
       return  false;
    }

    /**
     * Actionbar navigation item select listener
     * */
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        // Action to be taken after selecting a spinner item
        Toast.makeText(getBaseContext(), "Selected", Toast.LENGTH_SHORT).show();
        Log.d("BTN", itemPosition + " " + itemId);
        return false;
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Toast.makeText(getBaseContext(), "long press", Toast.LENGTH_SHORT).show();
        Log.d("BTN", keyCode + " " + event.getAction());
        return super.onKeyLongPress(keyCode, event);
    }
}
