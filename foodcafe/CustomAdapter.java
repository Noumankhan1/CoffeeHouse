package com.example.foodcafe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ItemData> {
    ArrayList<ItemData> data = new ArrayList<ItemData>();
    ImageView image;
    TextView price;
    TextView name;
    CheckBox fav;
    public CustomAdapter(Context context, int textViewResourceId, ArrayList<ItemData> object){
        super(context, textViewResourceId, object);
        data=object;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.fav_list,null);

        image = (ImageView) v.findViewById(R.id.imageView);
        price = (TextView) v.findViewById(R.id.price);
        name = (TextView) v.findViewById(R.id.Name);


        price.setText(data.get(position).getPrices());
        name.setText(data.get(position).getItem_name());
        image.setImageResource(data.get(position).getImages());


        return v;
    }
}

