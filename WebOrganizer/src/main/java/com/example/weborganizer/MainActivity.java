package com.example.weborganizer;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import com.example.list2.R;
import com.example.weborganizer.Containers.Filter;
import com.example.weborganizer.Containers.Task;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends FragmentActivity  {
	 static ArrayList<ArrayList<ArrayList<Task>>> groups = new ArrayList<ArrayList<ArrayList<Task>>>();
	 static ArrayList<String> groupeNames = new ArrayList<String>();
  //  static ArrayList<ExpListAdapter> adapters = new ArrayList<ExpListAdapter>();

	    
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
//        for(ExpListAdapter listAdapter: adapters)
//        {
//            listAdapter.notifyDataSetChanged();
//            Log.d("Updste", " " + listAdapter.toString());
//        }
        super.onRestart();
    }



    @Override
    protected void onStart() {
        Log.d("onStart()", " " + this.toString());
//        if(!adapters.isEmpty()){
//            onResume();
//        }
        Log.d("FragmentChildSuper", " " + this.toString());
        DatabaseWorker databaseWorker = new DatabaseWorker(this);
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
        databaseWorker.close();
        super.onStart();
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

//        for(ExpListAdapter listAdapter: adapters)
//        {
//            listAdapter.notifyDataSetChanged();
//            Log.d("Updste", " " + listAdapter.toString());
//        }
        Log.d("ADAPTERS",""+groups.toString());

        super.onResume();
    }

//    @Override
//    protected void onResumeFragments() {
//        Log.d("FragmentPhaser"," "+this.toString()+" "+adapters.size());
//        super.onResume();
//        super.onResumeFragments();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("8888888888888888888888888888888888888888888888", "");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent intent = new Intent(this,TaskEnter.class);
               startActivityForResult(intent, RESULT_OK);
                

                break;
            case R.id.item2:

                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("Title");
                alert.setMessage("Message");

// Set an EditText view to get user input
                final EditText input = new EditText(this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        Log.d("DIALOG",value);
                        DatabaseWorker databaseWorker = new DatabaseWorker(getBaseContext());
                        databaseWorker.printTable(DatabaseWorker.tableFilter);
                        databaseWorker.insertFilter(0,value);
                        databaseWorker.printTable(DatabaseWorker.tableFilter);
                        databaseWorker.close();

                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();
                break;
            case R.id.item3:
                DatabaseWorker databaseWorker = new DatabaseWorker(getBaseContext());
                databaseWorker.printTable(DatabaseWorker.tableTask);
                databaseWorker.close();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        //imageButton5= (ImageButton)findViewById(R.id.imageButton);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

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
                DatabaseWorker databaseWorker = new DatabaseWorker(getBaseContext());
                pageTitles = databaseWorker.getFilters();
                numberOfFragments=pageTitles.size();
                databaseWorker.close();
            }

			return numberOfFragments;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();

			return pageTitles.get(position).filterName.toLowerCase();
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

        @Override
        public void onResume() {
            Log.d("FragmentChild", getId() + " " + this.toString() + " ");
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



            adapter = new ExpListAdapter(rootView.getContext(), groups.get(fragmentID),groupeNames);
//
//	          listView.setOnChildClickListener(new OnChildClickListener() {
//                  @Override
//                  public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                      adapter.getChild(groupPosition,childPosition);
//                      Log.d("CCCCCCCCCCCCCCCCCCCCCC", (((Task)adapter.getChild(groupPosition,childPosition))).toString());
//
//                      return true;
//                  }
//              });
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
