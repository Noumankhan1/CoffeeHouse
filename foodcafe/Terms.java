package com.example.foodcafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.WindowManager;
import android.widget.TextView;

public class Terms extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Vibrator vibrator;
    boolean night, Sound_boolean, Vibrator_boolean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_terms);
        getSupportActionBar().setTitle("Terms and Conditions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FD330100"));
        actionBar.setBackgroundDrawable(colorDrawable);
        mediaPlayer= MediaPlayer.create(this,R.raw.tapclick);
        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        TextView textView = findViewById(R.id.textView6);
        TextView textView2 = findViewById(R.id.textView7);

        SharedPreferences preferences=getSharedPreferences(Settings.Mode, 0);
        boolean night=preferences.getBoolean("night",false);
        if (night){
            textView.setTextColor(getResources().getColor(R.color.white));
            textView2.setTextColor(getResources().getColor(R.color.white));
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
    @Override
    protected void onResume() {
        SharedPreferences getsoundsp = getSharedPreferences("sound",MODE_PRIVATE);
        SharedPreferences getvibrationsp = getSharedPreferences("vib",MODE_PRIVATE);
        Sound_boolean=getsoundsp.getBoolean("sound",false);
        Vibrator_boolean= getvibrationsp.getBoolean("vib",false);

        super.onResume();
    }
    public boolean onSupportNavigateUp(){
        Intent intent = new Intent(Terms.this, Mainmenu.class);
        startActivity(intent);
        if ( Vibrator_boolean){
            vibrator.vibrate(30);
        }
        if (Sound_boolean){
            mediaPlayer.start();
        }
        return true;
    }

}