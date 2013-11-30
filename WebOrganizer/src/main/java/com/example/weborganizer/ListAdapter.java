package com.example.weborganizer;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.list2.R;
import com.example.weborganizer.Containers.Contact;

import java.util.ArrayList;

/**
 * Created by Sasha on 22.11.13.
 */
//public class ContactsList/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class ListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater lInflater;
    ArrayList<Contact> objects;
    private int mode=10;
    private DisplayMetrics metrics;
    TextView tv1,tv2,tv3,tv4;
    ListAdapter(Context context, ArrayList<Contact> products) {
        this.context = context;
        objects = products;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    public void update(ArrayList<Contact> products) {
        this.objects=products;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.contact_card, parent, false);
        }

        Contact p = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        tv1=(TextView) view.findViewById(R.id.textView);//).setText(p.contactName);
        tv2=(TextView) view.findViewById(R.id.textView2);//).setText(p.contactSurname + "");
        tv3=(TextView) view.findViewById(R.id.textView3);//).setText(p.contactPhone);
        //tv4=(TextView) view.findViewById(R.id.textView4);//).setText(p.coonTactEmail + "");
        tv1.setText(p.contactName+" "+p.contactSurname);
        tv2.setText(p.contactPhone+"" );
        //tv3.setText(p.coonTactEmail);
        //tv4.setText(p.contactPhone+"");
        Animation animation = null;

        switch (mode) {
            case 1:
//                animation = new TranslateAnimation(metrics_.widthPixels / 2, 0,
//                        0, 0);
                break;

            case 2:
//                animation = new TranslateAnimation(0, 0, metrics_.heightPixels,
//                        0);
                break;

            case 3:
                animation = new ScaleAnimation((float) 1.0, (float) 1.0,
                        (float) 0, (float) 1.0);
                break;

            case 4:
                animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
                break;
            case 5:
                animation = AnimationUtils.loadAnimation(context, R.anim.hyperspace_in);
                break;
            case 6:
                animation = AnimationUtils.loadAnimation(context, R.anim.hyperspace_out);
                break;
            case 7:
                animation = AnimationUtils.loadAnimation(context, R.anim.wave_scale);
                break;
            case 8:
                animation = AnimationUtils.loadAnimation(context, R.anim.push_left_in);
                break;
            case 9:
                animation = AnimationUtils.loadAnimation(context, R.anim.push_left_out);
                break;
            case 10:
                animation = AnimationUtils.loadAnimation(context, R.anim.push_up_in);
                break;
            case 11:
                animation = AnimationUtils.loadAnimation(context, R.anim.push_up_out);
                break;
            case 12:
                animation = AnimationUtils.loadAnimation(context, R.anim.shake);
                break;
        }

			animation.setDuration(500);
        view.startAnimation(animation);
        animation = null;




        return view;
    }

    // товар по позиции
    Contact getProduct(int position) {
        return ((Contact) getItem(position));
    }




}