package com.horoscope.app;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osman on 9.7.2014.
 */
public class CustomAdapter extends ArrayAdapter<Zodiac> {

    List<Zodiac> list;

    public CustomAdapter(Context context, int textViewResourceId,
                         List<Zodiac> objects) {
        super(context, R.layout.main, objects);
        list=(List<Zodiac>) objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.main, null);
        }


        Typeface typeFace= Typeface.createFromAsset(v.getResources().getAssets(), "fonts/absolutamente.ttf");


        TextView name = (TextView) v.findViewById(R.id.name);
        TextView date = (TextView) v.findViewById(R.id.date);
        ImageView icon = (ImageView) v.findViewById(R.id.icon);
        RelativeLayout item = (RelativeLayout) v.findViewById(R.id.item);

        name.setTypeface(typeFace);

        name.setText(list.get(position).getIdentifier().getName());
        date.setText(list.get(position).getDate());
        icon.setImageResource(list.get(position).getIcon());
        item.setBackgroundColor(v.getResources().getColor(list.get(position).getColor()));

        return v;

    }
}
