package com.example.weborganizer.test;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.list2.R;


/**
 * Created by Sasha on 28.11.13.
 */
public class CustomActionProvider extends ActionProvider{
    private static final Intent sSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
    Context mContext;
    View.OnLongClickListener onLongClickListener;
    View.OnClickListener onClickListener;
    public CustomActionProvider(Context context){
           super(context);
            mContext = context;
    }

    @Override
    public View onCreateActionView() {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.test_btn, null);

        ImageButton button = (ImageButton) view.findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Respond to normal click
                Toast.makeText(mContext,"Short",Toast.LENGTH_SHORT).show();
              //  mContext.startActivity(sSettingsIntent);
               // MainActivity.addFiltr();
            }

        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext,"LONG",Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        return null;
    }
}
