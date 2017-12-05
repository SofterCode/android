package com.example.foster.parkthis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.jar.Attributes;

/**
 * Created by Foster on 27/11/2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context NameActivity;
    String[] type_x;
    String[] num_x;

    public static int[] images={R.drawable.handiparking,R.drawable.membership,R.drawable.parkingspaces};

    public CustomAdapter(NameActivity na, String[] type, String[] number){
        NameActivity = na;
        type_x = type;
        num_x = number;
    }

    @Override
    public int getCount() {
        return type_x.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View row;

        LayoutInflater inflater = (LayoutInflater) NameActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.cust_list, null);

        if(position>0 && images.length >= position){
            ImageView icon = (ImageView) row.findViewById(R.id.icon);
            icon.setImageResource(images[position-1]);
        }

        TextView textView1 = (TextView) row.findViewById(R.id.numbers);
        TextView textView = (TextView) row.findViewById(R.id.desc);

        if(type_x[position].contains("Id")){
            textView1.setText("");
            textView.setText("");

        }else{
            textView1.setText(num_x[position]);
            textView.setText(type_x[position]);
        }

        return row;
    }

}
