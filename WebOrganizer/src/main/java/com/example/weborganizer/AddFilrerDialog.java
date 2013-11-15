package com.example.weborganizer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Sasha on 15.11.13.
 */
public class AddFilrerDialog extends DialogFragment {
    private static final String LOG_TAG = "";

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
//                .setTitle("Title!").setPositiveButton(R.string.yes, this)
//                .setNegativeButton(R.string.no, this)
//                .setNeutralButton(R.string.maybe, this)
//                .setMessage(R.string.message_text);
        return adb.create();
    }

    public void onClick(DialogInterface dialog, int which) {
        int i = 0;
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
               // i = R.string.yes;
                break;
            case Dialog.BUTTON_NEGATIVE:
                //i = R.string.no;
                break;
            case Dialog.BUTTON_NEUTRAL:
                //i = R.string.maybe;
                break;
        }
        if (i > 0)
            Log.d(LOG_TAG, "Dialog 2: " + getResources().getString(i));
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 2: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 2: onCancel");
    }
}
