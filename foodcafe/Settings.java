package com.example.foodcafe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.jar.Manifest;

public class Settings extends AppCompatActivity {
    Switch aSwitch;
    SwitchCompat vibration,sound;
    boolean night;
    TextView textView, soundtxt, vibrationtxt;
    public static String Mode = "MODE";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FD330100"));
        actionBar.setBackgroundDrawable(colorDrawable);
        textView = findViewById(R.id.textView4);
        aSwitch=findViewById(R.id.switch3);
        sound=findViewById(R.id.soundb);
        vibration=findViewById(R.id.vibration);
        soundtxt=findViewById(R.id.sound);
        vibrationtxt=findViewById(R.id.vibrationtxt);
        SharedPreferences getsoundsp = getSharedPreferences("sound",MODE_PRIVATE);
        SharedPreferences getvibrationsp = getSharedPreferences("vib",MODE_PRIVATE);
        sound.setChecked(getsoundsp.getBoolean("sound",false));
        vibration.setChecked(getvibrationsp.getBoolean("vib",false));

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound.isChecked()){
                    SharedPreferences.Editor soundpreferences = getSharedPreferences("sound",MODE_PRIVATE).edit();
                    soundpreferences.putBoolean("sound",true);
                    soundpreferences.apply();
                    sound.setChecked(true);
                }
                else {
                    SharedPreferences.Editor soundpreferences = getSharedPreferences("sound",MODE_PRIVATE).edit();
                    soundpreferences.putBoolean("sound",false);
                    soundpreferences.apply();
                    sound.setChecked(false);
                }
            }
        });
        vibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vibration.isChecked()){
                    SharedPreferences.Editor soundpreferences = getSharedPreferences("vib",MODE_PRIVATE).edit();
                    soundpreferences.putBoolean("vib",true);
                    soundpreferences.apply();
                    vibration.setChecked(true);
                }
                else {
                    SharedPreferences.Editor soundpreferences = getSharedPreferences("vib",MODE_PRIVATE).edit();
                    soundpreferences.putBoolean("vib",false);
                    soundpreferences.apply();
                    vibration.setChecked(false);
                }
            }
        });
        preferences=getSharedPreferences(Settings.Mode, 0);
        aSwitch.setChecked(preferences.getBoolean("night",false));
        if(aSwitch.isChecked()){
            textView.setTextColor(getResources().getColor(R.color.white));
            aSwitch.setTextColor(getResources().getColor(R.color.white));
            vibrationtxt.setTextColor(getResources().getColor(R.color.white));
            soundtxt.setTextColor(getResources().getColor(R.color.white));
        }
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitch.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor=preferences.edit();
                    editor.putBoolean("night",true);
                    aSwitch.setChecked(true);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor=preferences.edit();
                    editor.putBoolean("night",false);

                    aSwitch.setChecked(false);
                }
                editor.apply();
            }
        });


        SeekBar seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setTextSize(progress+1);
                soundtxt.setTextSize(progress+1);
                vibrationtxt.setTextSize(progress+1);
                aSwitch.setTextSize(progress+1);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(Settings.this, Mainmenu.class);
        startActivity(intent);
        return true;
    }

}