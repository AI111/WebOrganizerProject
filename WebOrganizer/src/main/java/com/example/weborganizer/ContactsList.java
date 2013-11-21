package com.example.weborganizer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.list2.R;
import com.example.weborganizer.Containers.Task;

/**
 * Created by Sasha on 22.11.13.
 */
public class ContactsList extends ArrayAdapter<Task> {
    public ContactsList(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){

            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();

            convertView = inflater.inflate(R.layout.contacts, parent, false);

        }



        TextView textViewItem = (TextView) convertView.findViewById(R.id.textView);

        textViewItem.setText("");

        textViewItem.setTag("");

        return convertView;

    }
}
