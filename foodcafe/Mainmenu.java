package com.example.foodcafe;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.foodcafe.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Mainmenu extends AppCompatActivity {
    ActivityMainBinding binding;
    GridView gridView;
    MediaPlayer mediaPlayer;
    Vibrator vibrator;
    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog;
    boolean night, Sound_boolean, Vibrator_boolean;
    public static String namey;
    public static  String price;
    public static int img;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root =  db.getReference().child("Favorite");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        gridView = findViewById(R.id.listview);
        getSupportActionBar().setTitle("Home Cafe");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FD330100"));
        actionBar.setBackgroundDrawable(colorDrawable);
        BottomNavigationView  navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setSelectedItemId(R.id.home);
        grid();
       mediaPlayer=MediaPlayer.create(this,R.raw.tapclick);
       vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        progressDialog = new ProgressDialog(this);
        if (night){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }
        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.home:
                        if ( Vibrator_boolean){
                            vibrator.vibrate(30);
                        }
                        if (Sound_boolean){
                            mediaPlayer.start();
                        }
                        break;

                    case R.id.coffee:
                        startActivity(new Intent(getApplicationContext(),Coffee.class));
                        overridePendingTransition(0,0);
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

    public void grid(){
        String[] item_name = {"Cup Cakes", "Frappe coffee", "Iced Coffee", "Flores Cake", "Strawberry Cream Cake", "Gold Coffee", "Latte Coffee", "Butterscotch Cake" };
        String[] prices ={"Rs 700", "Rs 500", "Rs 400", "Rs 1000", "Rs 3000", "Rs 300", "Rs 500", "Rs 2000"};
        String [] del_charges={"Rs 70", "Rs 50", "Rs 40", "Rs 100", "Rs 300", "Rs 50", "Rs 100", "Rs 200"};
        int[]   images ={R.drawable.cupcakes, R.drawable.frappe_coffee,
                R.drawable.iced_coffee,R.drawable.flores_cake,R.drawable.stawb_cake,R.drawable.gold_coffee
                , R.drawable.latte,R.drawable.butter_cake};
        int[] det_img={R.drawable.cupcake21,R.drawable.frappecoffee1,R.drawable.icedcoffee1,R.drawable.flores_cake,R.drawable.strawberrycake21,R.drawable.goldcoffee1,R.drawable.lattecoffee1,R.drawable.buttersoctch21};
        Grid_Adapter gridAdapter = new Grid_Adapter(Mainmenu.this,item_name,images,prices,del_charges,det_img);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Mainmenu.this,MainActivity.class);
                intent.putExtra("name",item_name[position]);
                intent.putExtra("price",prices[position]);
                intent.putExtra("img",det_img[position]);
                intent.putExtra("del",del_charges[position]);
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

    @Override
    protected void onResume() {
        SharedPreferences getsoundsp = getSharedPreferences("sound",MODE_PRIVATE);
        SharedPreferences getvibrationsp = getSharedPreferences("vib",MODE_PRIVATE);
       SharedPreferences  preferences=getSharedPreferences(Settings.Mode, 0);
        night=preferences.getBoolean("night",false);
        Sound_boolean=getsoundsp.getBoolean("sound",false);
       Vibrator_boolean= getvibrationsp.getBoolean("vib",false);

        super.onResume();
    }

    public boolean onSupportNavigateUp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Mainmenu.this);
        builder.setMessage("Do you want to close the app?");
        builder.setTitle("Alert !!");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish();

            moveTaskToBack(true);
        });
        builder.setNegativeButton("NO", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();

        alertDialog.show();
        if ( Vibrator_boolean){
            vibrator.vibrate(30);
        }
        if (Sound_boolean){
            mediaPlayer.start();
        }
        return true;
    }
    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Mainmenu.this);
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



/*
Toast.makeText(Mainmenu.this, "Clicked"+item_name[position], Toast.LENGTH_SHORT).show();
                namey=item_name[position];
                price=prices[position];
                img=images[position];

                //  HashMap<String, Integer> usermap= new HashMap<>();
                HashMap<String, String > use =new HashMap<>();
                use.put("name",namey);
                use.put("price",price);
                //  usermap.put("img",img);

                root.push().setValue(use);
                // root.push().setValue(usermap);

 */
}