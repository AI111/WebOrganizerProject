package com.example.weborganizer.test;

/**
 * Created by Sasha on 29.11.13.
 */


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.example.list2.R;

import java.util.ArrayList;

public class MainActivity extends Activity implements ActionBar.OnNavigationListener{

    // action bar
    private ActionBar actionBar;

    // Title navigation Spinner data
    private ArrayList<SpinnerNavItem> navSpinner;

    // Navigation adapter
    private TitleNavigationAdapter adapter;

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

        View v = inflator.inflate(R.layout.search, null);



//       actionBar = getActionBar();
//
//        // Hide the action bar title
//        actionBar.setDisplayShowTitleEnabled(false);
//
//        // Enabling Spinner dropdown navigation
       actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//
//        // Spinner title navigation data
        Spinner spinner = (Spinner)v.findViewById(R.id.spinner);
        navSpinner = new ArrayList<SpinnerNavItem>();
        navSpinner.add(new SpinnerNavItem("Local", R.drawable.ic_action_new));
        navSpinner.add(new SpinnerNavItem("My Places", R.drawable.abc_ic_clear));
        navSpinner.add(new SpinnerNavItem("Checkins", R.drawable.ic_action_labels));
        navSpinner.add(new SpinnerNavItem("Latitude", R.drawable.icon));

        // title drop down adapter
        adapter = new TitleNavigationAdapter(getApplicationContext(), navSpinner);
        spinner.setAdapter(adapter);
//
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
      //  Toast.makeText(getBaseContext(), "Short", Toast.LENGTH_SHORT).show();
       return  false;
    }

    /**
     * Actionbar navigation item select listener
     * */
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        // Action to be taken after selecting a spinner item
      //  Toast.makeText(getBaseContext(), "Short", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
      //  Toast.makeText(getBaseContext(), "Short", Toast.LENGTH_SHORT).show();
        return super.onKeyLongPress(keyCode, event);
    }
}
