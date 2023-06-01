package com.example.foodcafe;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;

import com.example.foodcafe.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

public class Coffee extends AppCompatActivity {
    ActivityMainBinding binding;
    ProgressDialog progressDialog;
    GridView gridView;
    MediaPlayer mediaPlayer;
    Vibrator vibrator;
    boolean night, Sound_boolean, Vibrator_boolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_coffee);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        gridView = findViewById(R.id.listview);
        getSupportActionBar().setTitle("Coffees");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FD330100"));
        actionBar.setBackgroundDrawable(colorDrawable);
        mediaPlayer=MediaPlayer.create(this,R.raw.tapclick);
        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        BottomNavigationView  navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setSelectedItemId(R.id.coffee);
        grid();
        progressDialog = new ProgressDialog(this);
        //setContentView(binding.getRoot());
        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Mainmenu.class));
                        overridePendingTransition(0,0);
                        if ( Vibrator_boolean){
                            vibrator.vibrate(30);
                        }
                        if (Sound_boolean){
                            mediaPlayer.start();
                        }
                        break;

                    case R.id.coffee:
                        if ( Vibrator_boolean){
                            vibrator.vibrate(30);
                        }
                        if (Sound_boolean){
                            mediaPlayer.start();
                        }
                        break;

                    case R.id.cake:
                        startActivity(new Intent(getApplicationContext(),Cake.class));
                        overridePendingTransition(0,0);
                        if ( Vibrator_boolean){
                            vibrator.vibrate(30);
                        }
                        if (Sound_boolean){
                            mediaPlayer.start();
                        }
                        break;
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topnav,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences(SignIn.PRE,0);
                SharedPreferences.Editor editor= preferences.edit();
                editor.putBoolean("has",false);
                editor.commit();
                logout();
                if ( Vibrator_boolean){
                    vibrator.vibrate(30);
                }
                if (Sound_boolean){
                    mediaPlayer.start();
                }
                break;
            case R.id.favourite:
                startActivity(new Intent(getApplicationContext(),Favourite.class));
                overridePendingTransition(0,0);
                if ( Vibrator_boolean){
                    vibrator.vibrate(30);
                }
                if (Sound_boolean){
                    mediaPlayer.start();
                }
                break;
            case R.id.about:
                startActivity(new Intent(getApplicationContext(), About.class));
                if ( Vibrator_boolean){
                    vibrator.vibrate(30);
                }
                if (Sound_boolean){
                    mediaPlayer.start();
                }
                break;
            case R.id.tp:
                startActivity(new Intent(getApplicationContext(), Terms.class));
                if ( Vibrator_boolean){
                    vibrator.vibrate(30);
                }
                if (Sound_boolean){
                    mediaPlayer.start();
                }
                break;
            case R.id.setting:
                startActivity(new Intent(getApplicationContext(), Settings.class));
                if ( Vibrator_boolean){
                    vibrator.vibrate(30);
                }
                if (Sound_boolean){
                    mediaPlayer.start();
                }
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        SharedPreferences getsoundsp = getSharedPreferences("sound",MODE_PRIVATE);
        SharedPreferences getvibrationsp = getSharedPreferences("vib",MODE_PRIVATE);
        Sound_boolean=getsoundsp.getBoolean("sound",false);
        Vibrator_boolean= getvibrationsp.getBoolean("vib",false);

        super.onResume();
    }
    public void grid(){
        String[] item_name = { "Frappe coffee", "Iced Coffee",  "Gold Coffee", "Latte Coffee", "Cappuccino Coffee", "Black Coffee" };
        String[] prices ={ "Rs 500", "Rs 400",  "Rs 300", "Rs 500" , "Rs 700" , "Rs 200" };
        int[]   images ={ R.drawable.frappe_coffee, R.drawable.iced_coffee,R.drawable.gold_coffee, R.drawable.latte, R.drawable.cappuccino, R.drawable.blackcoffee};
        String [] del_charges={"Rs 70", "Rs 50", "Rs 40", "Rs 100", "Rs 300", "Rs 50", "Rs 100", "Rs 200"};
        int[] det_img={R.drawable.frappecoffee1,R.drawable.icedcoffee1,R.drawable.goldcoffee1,R.drawable.lattecoffee1,R.drawable.cappuccinocoffee1,R.drawable.blackcoffee2};
        Grid_Adapter gridAdapter = new Grid_Adapter(Coffee.this,item_name,images,prices,del_charges,det_img);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Coffee.this,MainActivity.class);
                intent.putExtra("name",item_name[position]);
                intent.putExtra("price",prices[position]);
                intent.putExtra("del",del_charges[position]);
                intent.putExtra("img",det_img[position]);

                startActivity(intent);
                if ( Vibrator_boolean){
                    vibrator.vibrate(30);
                }
                if (Sound_boolean){
                    mediaPlayer.start();
                }

            }
        });
    }
    public boolean onSupportNavigateUp(){
        Intent intent = new Intent(Coffee.this, Mainmenu.class);
        startActivity(intent);
        if ( Vibrator_boolean){
            vibrator.vibrate(30);
        }
        if (Sound_boolean){
            mediaPlayer.start();
        }
        return true;
    }
    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Coffee.this);
        builder.setMessage("Are you sure you want to Logout?");
        builder.setTitle("Alert !!");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {

            progressDialog.setMessage("Please wait while Logout....");
            progressDialog.setTitle("Logout");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            if(progressDialog.isShowing()){
                startActivity(new Intent(getApplicationContext(), SignIn.class));
            }
            // finish();
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

}