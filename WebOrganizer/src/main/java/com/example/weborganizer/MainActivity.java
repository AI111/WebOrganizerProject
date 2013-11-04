package com.example.weborganizer;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import com.example.list2.R;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends FragmentActivity implements OnClickListener {
	 static ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
	    ArrayList<String> children1 = new ArrayList<String>();
	    ArrayList<String> children2 = new ArrayList<String>();
	    
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	 
	int DIALOG_DATE = 1;
	  int myYear = 2011;
	  int myMonth = 02;
	  int myDay = 03;
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		groups.clear();
        children1.clear();
        children2.clear();


        children1.add("Child_1");
        children1.add("Child_2");
        groups.add(children1);
        children2.add("Child_1");
        children2.add("Child_2");
        children2.add("Child_3");
        groups.add(children2);
        
//       
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
        DatabaseWorker databaseWorker = new DatabaseWorker(this);
        SQLiteDatabase database= databaseWorker.getReadableDatabase();

        Cursor cursor  = database.rawQuery("SELECT Filter_id  FROM Filters",null);
        if(cursor!=null){
            Log.d("D_B", ""+cursor.getInt((cursor.getColumnIndex("Filter_Name"))));

           // Log.d("D_B", cursor.getString(cursor.getColumnIndex("Filter_Name")));
        }
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
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		ImageButton imageButton1,imageButton2,imageButton3,imageButton4;
		OnClickListener clickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Toast.makeText(this, v.getId()+"", Toast.LENGTH_SHORT).show();
				switch (v.getId()) {
				case R.id.imageButton:
			//showDialog(1);
					break;
				case R.id.imageButton1:
					
					break;
				case R.id.imageButton2:
			
					break;
				case R.id.imageButton3:
			
					break;
				default:
					break;
				}
			}
		};
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			 View rootView = inflater.inflate(R.layout.fragment_main, container, false);
	            ExpandableListView listView = (ExpandableListView)rootView.findViewById(R.id.expandableListView);
	            
	            
	         //   imageButton1= (ImageButton)rootView.findViewById(R.id.imageButton);
	         //  imageButton1.setOnClickListener(clickListener);
	            //������� ����� ������ ��� ��������
	            	
	            //������� ������� � �������� context � ������ � �������
	            ExpListAdapter adapter = new ExpListAdapter(rootView.getContext(), groups);
	            listView.setAdapter(adapter);
	            
	            View rootView1 = inflater.inflate(R.layout.child_view, container, false);
	            imageButton1= (ImageButton)rootView1.findViewById(R.id.imageButton);
	          imageButton2= (ImageButton)rootView1.findViewById(R.id.imageButton1);
	          imageButton3= (ImageButton)rootView1.findViewById(R.id.imageButton2);
	          imageButton4= (ImageButton)rootView1.findViewById(R.id.imageButton3);
	          
	          imageButton1.setOnClickListener(clickListener);
	          imageButton2.setOnClickListener(clickListener);
	          imageButton3.setOnClickListener(clickListener);
	          imageButton4.setOnClickListener(clickListener);
//			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
//					container, false);
//			TextView dummyTextView = (TextView) rootView
//					.findViewById(R.id.section_label);
//			dummyTextView.setText(Integer.toString(getArguments().getInt(
//					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
	}

}
