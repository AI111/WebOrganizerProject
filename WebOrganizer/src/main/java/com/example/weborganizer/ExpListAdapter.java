package com.example.weborganizer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.list2.R;
import com.example.weborganizer.Containers.Task;

import java.util.ArrayList;

public class ExpListAdapter extends BaseExpandableListAdapter {
    Task task;
    ImageButton imageButton1,imageButton2,imageButton3,imageButton4;
	private ArrayList<ArrayList<Task>> mGroups;
    private ArrayList<String> groupeNames;
    private Context mContext;
    LinearLayout previosLL;
    public ExpListAdapter (Context context,ArrayList<ArrayList<Task>> groups, ArrayList<String> groupeNames){
        mContext = context;
        mGroups = groups;
        this.groupeNames=groupeNames;
        Log.d("ARR",groups.toString());
    }
    
    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_view, null);
        }



        TextView textGroupName = (TextView) convertView.findViewById(R.id.textGroup);

        textGroupName.setText(groupeNames.get(groupPosition));


        return convertView;

    }
    public void update(ArrayList<ArrayList<Task>> newData){
        this.mGroups=newData;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_view, null);
        }
       // Animation animation = AnimationUtils.loadAnimation(convertView.getContext(), R.anim.push_left_in);
		//convertView.setAnimation(animation);
		//animation=null;
        //final LinearLayout lr =(LinearLayout)convertView.findViewById(R.id.linearLayout22);
        //Log.d("CHILD",mGroups.get(1).get(childPosition).toString());
        TextView textChild = (TextView) convertView.findViewById(R.id.textView);
        TextView textChildDate = (TextView) convertView.findViewById(R.id.textChild);
        textChild.setText(mGroups.get(groupPosition).get(childPosition).taskTitle);
        textChildDate.setText(mGroups.get(groupPosition).get(childPosition).taskTime);
        final LinearLayout lr =(LinearLayout)convertView.findViewById(R.id.linearLayout22);

    	lr.setVisibility(View.GONE);
//            lr.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("C", v.getId()+"");
//                }
//            });
        imageButton1= (ImageButton)convertView.findViewById(R.id.imageButton);
        imageButton2= (ImageButton)convertView.findViewById(R.id.imageButton1);
        imageButton3= (ImageButton)convertView.findViewById(R.id.imageButton2);
        imageButton4= (ImageButton)convertView.findViewById(R.id.imageButton3);

        imageButton1.setOnClickListener(clickListener);
        imageButton2.setOnClickListener(clickListener);
        imageButton3.setOnClickListener(clickListener);
        imageButton4.setOnClickListener(clickListener);
        convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
                 task =(Task) getChild(groupPosition,childPosition);
                Log.d("CHILD", task.toString());
				if(lr.getVisibility()==View.VISIBLE){
					lr.setVisibility(View.GONE);
				}else{
					lr.setVisibility(View.VISIBLE);
                    if(previosLL!=null&&previosLL.getVisibility()==View.VISIBLE&&previosLL!=lr){
                        previosLL.setVisibility(View.GONE);
                    }if(previosLL!=lr)previosLL=lr;
				}
			//lr.getVisibility()=View.VISIBLE ? lr.setVisibility(Viev.GONE):lr.setVisibility(View.VISIBLE)
			}
		});


        return convertView;
    }
    OnClickListener clickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {

//				Toast.makeText(this, v.getId()+"", Toast.LENGTH_SHORT).show();
            switch (v.getId()) {
                case R.id.imageButton:
                    //showDialog(1);
                    Log.d("AAAAAAAAAAAAAAAAAAAA","DDDDDDDDDDDDDDDD");
                    break;
                case R.id.imageButton1:

                    break;
                case R.id.imageButton2:

                    break;
                case R.id.imageButton3:
                    Intent intent = new Intent(mContext,TaskEnter.class);
                    intent.putExtra(DatabaseWorker.collTaskTitle,task.taskTitle);
                    intent.putExtra(DatabaseWorker.collTaskText,task.taskText);
                    intent.putExtra(DatabaseWorker.collTaskDate,task.taskDate);
                    intent.putExtra(DatabaseWorker.collTaskTime,task.taskTime);
                    intent.putExtra(DatabaseWorker.collTaskLast_Editing,task.lastTaskLastEditing);
                    intent.putExtra(DatabaseWorker.collTaskFilter,task.taskFilterId);
                    mContext.startActivity(intent);

                    break;
                default:
                    break;
            }
        }
    };
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
