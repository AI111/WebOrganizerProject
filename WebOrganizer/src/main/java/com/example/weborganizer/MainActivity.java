package com.example.weborganizer;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.list2.R;
import com.example.weborganizer.Containers.Filter;
import com.example.weborganizer.Containers.Task;
import com.example.weborganizer.test.SpinnerNavItem;
import com.example.weborganizer.test.Test;
import com.example.weborganizer.test.TitleNavigationAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends FragmentActivity   {
	 static ArrayList<ArrayList<ArrayList<Task>>> groups = new ArrayList<ArrayList<ArrayList<Task>>>();
	 static ArrayList<String> groupeNames = new ArrayList<String>();

    static  DatabaseWorker databaseWorker ;
    SpinnerAdapter spinnerAdapter;
	 boolean create;

    Context context;
    // Title navigation Spinner data
    private ArrayList<SpinnerNavItem> navSpinner,navItems;

    // Navigation adapter
    private TitleNavigationAdapter adapter,adapter1;

    /**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;


	  int myYear;
	  int myMonth;
	  int myDay;
    int numberOfFragments;


    private ArrayList<Filter> pageTitles;

	/**
	 * The {@link android.support.v4.view.ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	private OnDateSetListener myCallBack;
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
        return tpd;
	}

    @Override
    protected void onRestart() {
        Log.d("onRestart()()", " " + this.toString());
        super.onRestart();
    }



    @Override
    protected void onStart() {
        Log.d("onStart()", " " + this.toString());
//        if(!adapters.isEmpty()){
//            onResume();
//        }
        Log.d("FragmentChildSuper", " " + this.toString());
       // DatabaseWorker databaseWorker = new DatabaseWorker(this);
       update();
       // databaseWorker.close();
        super.onStart();
    }
    public void update(){
        pageTitles = databaseWorker.getFilters();
        numberOfFragments=pageTitles.size();
        groups.clear();
        for(int i=1;i<=numberOfFragments;i++)
        {
            groups.add(databaseWorker.getLists(i));

        }

        groupeNames.add(getString(R.string.yesterday));
        groupeNames.add(getString(R.string.today));
        groupeNames.add(getString(R.string.tomorrow));
        groupeNames.add(getString(R.string.in_the_future));
    }
    @Override
    protected void onPause() {
        Log.d("onPause()", " " + this.toString());

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d("onDestroy()", " " + this.toString());
        super.onDestroy();
    }


    @Override
    protected void onResume() {

        Log.d("ADAPTERS",""+groups.toString());

        super.onResume();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //ClipData.Item item1 = (ClipData.Item)item.findViewById(R.id.item2);


        switch (item.getItemId()){
            case R.id.item1:


                break;
            case R.id.item2:

               databaseWorker.printTable(DatabaseWorker.tableFilter);
                databaseWorker.printTable(DatabaseWorker.tableTask);
                break;
            case R.id.item3:
                //DatabaseWorker databaseWorker = new DatabaseWorker(getBaseContext());
                Intent intent1 = new Intent(this,ContactsActivity.class);
                startActivity(intent1);
                //databaseWorker.printTable(DatabaseWorker.tableTask);
               // databaseWorker.close();
                break;
            case R.id.item4:
                Intent intent2=new Intent(this,Test.class);
                startActivity(intent2);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        this.context=getApplicationContext();
        databaseWorker = new DatabaseWorker(this);
        //imageButton5= (ImageButton)findViewById(R.id.imageButton);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
       // lblButton=CustomActionProvider.getBtn();
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		 myCallBack = new OnDateSetListener() {

		    public void onDateSet(DatePicker view, int year, int monthOfYear,
		        int dayOfMonth) {
		      myYear = year;
		      myMonth = monthOfYear;
		      myDay = dayOfMonth;
		      //Toast.makeText(this, "Today is " + myDay + "/" + myMonth + "/" + myYear, Toast.LENGTH_SHORT).ma
		    }
		    };

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);


        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflator.inflate(R.layout.custom_action_bar, null);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//
//        // Spinner title navigation data
        final Spinner spinner = (Spinner)v.findViewById(R.id.spinner);

        ImageButton imageButton =(ImageButton)v.findViewById(R.id.imageButton);
        ImageButton imageButton1=(ImageButton)v.findViewById(R.id.imageButton2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // spinnerAdapter.update(databaseWorker.getFilters());
                if(create){
                Log.d("FILTERS",databaseWorker.getFilters().toString());
                Filter f = (Filter)parent.getItemAtPosition(position);
                    databaseWorker.deleteFilters(f);
                Toast.makeText(getBaseContext(), f.filterId+" del "+f.filterName, Toast.LENGTH_SHORT).show();

                update();
                mSectionsPagerAdapter.notifyDataSetChanged();
                }else{
                    create=true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TaskEnter.class);
                startActivityForResult(intent, RESULT_OK);

            }
        });
        imageButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getBaseContext(), "Long", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFilter();
            }
        });
        imageButton1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                spinnerAdapter.clear();
                spinnerAdapter.addAll(databaseWorker.getFilters());
                //spinnerAdapter.update(databaseWorker.getFilters());
                spinnerAdapter.notifyDataSetChanged();
                Log.d("FILTERS",databaseWorker.getFilters().toString());
                spinner.performClick();
                return true;
            }
        });

       spinnerAdapter  = new SpinnerAdapter(this,android.R.layout.simple_spinner_item,databaseWorker.getFilters());
        spinner.setAdapter(spinnerAdapter);
        actionBar.setCustomView(v);
	}
    void addFilter(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle(getString(R.string.add_tag));
        alert.setMessage(getString(R.string.add_tag_text));

// Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                Log.d("DIALOG",value);
                //  DatabaseWorker databaseWorker = new DatabaseWorker(getBaseContext());
                databaseWorker.printTable(DatabaseWorker.tableFilter);
                databaseWorker.insertFilter(value);
                databaseWorker.printTable(DatabaseWorker.tableFilter);
                mSectionsPagerAdapter.getCount();
                update();
                mSectionsPagerAdapter.notifyDataSetChanged();

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
		
	}

	/**
	 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position);
			fragment.setArguments(args);
			return fragment;

		}

		@Override
		public int getCount() {
			// Show 3 total pages.
            if(numberOfFragments ==0)
            {
               // DatabaseWorker databaseWorker = new DatabaseWorker(getBaseContext());
                pageTitles = databaseWorker.getFilters();
                numberOfFragments=pageTitles.size();
               // databaseWorker.close();
            }

			return numberOfFragments;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();

			return pageTitles.get(position).filterName.toUpperCase();
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
        ExpListAdapter adapter;
		ImageButton imageButton1,imageButton2,imageButton3,imageButton4;
        int fragmentID=-1;
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {

		}
        public void  updateELV(){

            groups.set(fragmentID, databaseWorker.getLists((fragmentID+1)));

            adapter.update(groups.get(fragmentID));
            adapter.notifyDataSetChanged();
           // adapter.notifyDataSetInvalidated();
            Log.d("updateELV()", getId() + " " + this.toString() + " ");
        }
        @Override
        public void onResume() {
            Log.d("FragmentChild", databaseWorker.getLists((fragmentID+1)).toString()+" "+fragmentID);
            super.onResume();

        }

        @Override
        public void onStart() {
            Log.d("onStart()Child", getId() + " " + this.toString() + " ");
            if(fragmentID!=-1){
            adapter.update(groups.get(fragmentID));
            adapter.notifyDataSetChanged();
            adapter.notifyDataSetInvalidated();
            }

            super.onStart();
        }

        @Override
        public void onPause() {
            Log.d("onPause()Child", getId() + " " + this.toString() + " ");
            super.onPause();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            Log.d("onCreate()Child", getId() + " " + this.toString() + " ");
            super.onCreate(savedInstanceState);
        }

        @Override
        public void onDestroy() {
            Log.d("onDestroy()Child", getId() + " " + this.toString() + " ");
            super.onDestroy();
        }

        @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
            Log.d("onCreateView", getId() + " " + this.toString() + " ");

			 View rootView = inflater.inflate(R.layout.fragment_main, container, false);
	            ExpandableListView listView = (ExpandableListView)rootView.findViewById(R.id.expandableListView);
             fragmentID= (int) getArguments().getInt(ARG_SECTION_NUMBER);



            adapter = new ExpListAdapter(rootView.getContext(), groups.get(fragmentID),groupeNames,this);
            listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    Log.d("GGGGGGGGGGGGGGG", "DDDDDDDDDDDDDDDD");
                    return false;
                }
            });
            listView.setAdapter(adapter);

//            if(adapters.size()>fragmentID){
//                adapters.set((fragmentID),adapter);
//            }else
//            {
//                adapters.add(adapter);
//            }

			return rootView;
		}
	}

}
