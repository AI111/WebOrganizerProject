package com.example.weborganizer;

import java.util.ArrayList;



import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.list2.R;

public class ExpListAdapter extends BaseExpandableListAdapter {
	private ArrayList<ArrayList<String>> mGroups;
    private Context mContext;
    LinearLayout previosLL;
    public ExpListAdapter (Context context,ArrayList<ArrayList<String>> groups){
        mContext = context;
        mGroups = groups;
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

        if (isExpanded){
           //�������� ���-������, ���� ������� Group ��������
        }
        else{
            //�������� ���-������, ���� ������� Group ������
        }

        TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);
        textGroup.setText("Group " + Integer.toString(groupPosition));

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_view, null);
        }
       // Animation animation = AnimationUtils.loadAnimation(convertView.getContext(), R.anim.push_left_in);
		//convertView.setAnimation(animation);
		//animation=null;
        //final LinearLayout lr =(LinearLayout)convertView.findViewById(R.id.linearLayout22);
        TextView textChild = (TextView) convertView.findViewById(R.id.textChild);
        textChild.setText(mGroups.get(groupPosition).get(childPosition));
        final LinearLayout lr =(LinearLayout)convertView.findViewById(R.id.linearLayout22);
    	lr.setVisibility(View.GONE);
     //   Button button = (Button)convertView.findViewById(R.id.buttonChild);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//        //    	lr.setVisibility(View.VISIBLE);
//                Toast.makeText(mContext,"button is pressed",5000).show();
//            }
//        });
        convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

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

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
