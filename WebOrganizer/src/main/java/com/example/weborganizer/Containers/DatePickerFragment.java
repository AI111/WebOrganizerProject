package com.example.weborganizer.Containers;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.list2.R;

public class DatePickerFragment extends DialogFragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.main, null);
//        v.findViewById(R.id.btnYes).setOnClickListener(this);
//        v.findViewById(R.id.btnNo).setOnClickListener(this);
//        v.findViewById(R.id.btnMaybe).setOnClickListener(this);
        return v;
    }

    public void onClick(View v) {
        Log Log;
       // Log.d(LOG_TAG, "Dialog 1: " + ((Button) v).getText());
        dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
       // Log.d(LOG_TAG, "Dialog 1: onDismiss");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
      //  Log.d(LOG_TAG, "Dialog 1: onCancel");
    }
}