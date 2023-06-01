package com.example.foodcafe;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        Thread thread = new Thread(){

            public void run(){
                try {
                    sleep(1200);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    SharedPreferences preferences = getSharedPreferences(SignIn.PRE,0);
                    boolean has = preferences.getBoolean("has",false);
                    if (has){
                        Intent intent = new Intent(Splash.this, Mainmenu.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(Splash.this, SignIn.class);
                        startActivity(intent);
                    }

                }
            }
        };thread.start();
    }
}