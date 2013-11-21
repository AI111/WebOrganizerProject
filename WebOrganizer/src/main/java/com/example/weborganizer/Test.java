package com.example.weborganizer;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.list2.R;
import com.example.weborganizer.Containers.Task;

import java.util.ArrayList;

/**
 * Created by Sasha on 17.11.13.
 */
public class Test implements ExpandableListAdapter {
    private ArrayList<ArrayList<Task>> mGroups;
    private ArrayList<String> groupeNames;
    private Context mContext;
    LinearLayout previosLL;
    public Test (Context context,ArrayList<ArrayList<Task>> groups, ArrayList<String> groupeNames){
        mContext = context;
        mGroups = groups;
        this.groupeNames=groupeNames;
        Log.d("ARR", groups.toString());
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

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
        return false;
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
        textGroup.setText(groupeNames.get(groupPosition));

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
        //Log.d("CHILD",mGroups.get(1).get(childPosition).toString());
        TextView textChild = (TextView) convertView.findViewById(R.id.textChild);
        textChild.setText(mGroups.get(groupPosition).get(childPosition).taskTitle);
        final LinearLayout lr =(LinearLayout)convertView.findViewById(R.id.linearLayout22);
        lr.setVisibility(View.GONE);

        convertView.setOnClickListener(new View.OnClickListener() {

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
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
