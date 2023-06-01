package com.example.foodcafe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

public class Grid_Adapter extends BaseAdapter {
Context context;
String[] item_name;
int[] images;
String[] prices;
String[] del_charges;
int[] det_img;



LayoutInflater inflater;

    public Grid_Adapter(Context context, String[] item_name, int[] images, String[] prices,String[] del_charges,int[] det_img) {
        this.context = context;
        this.item_name = item_name;
        this.images = images;
        this.prices= prices;
        this.del_charges=del_charges;
        this.det_img=det_img;
    }

    @Override
    public int getCount() {
        return item_name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView==null)
        {
            convertView = inflater.inflate(R.layout.grid_items,null);
        }

        ImageView image = convertView.findViewById(R.id.grid_image);
        TextView name = convertView.findViewById(R.id.item_name);
        TextView price = convertView.findViewById(R.id.item_price);


        image.setImageResource(images[position]);
        name.setText(item_name[position]);
        price.setText(prices[position]);
        SharedPreferences preferences=context.getSharedPreferences(Settings.Mode, 0);
        boolean night=preferences.getBoolean("night",false);
        if (night){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        return convertView;
    }
}
