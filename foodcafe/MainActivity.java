package com.example.foodcafe;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
Button  Favorite;
TextView name,price,del_ch, item_name, item_price, item_delPrice, item_order, item_Contact,item_number;
ImageView imageView;
Intent intent;
String title, it_price;

FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String, Object> fav = new HashMap<>();
    public static int image;
    MediaPlayer mediaPlayer;
    Vibrator vibrator;
    boolean night, Sound_boolean, Vibrator_boolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        intent=getIntent();
        title=intent.getStringExtra("name");
        it_price=intent.getStringExtra("price");
        image=intent.getIntExtra("img",0);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FD330100"));
        actionBar.setBackgroundDrawable(colorDrawable);

        mediaPlayer= MediaPlayer.create(this,R.raw.tapclick);
        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        item_name=findViewById(R.id.name);
        item_price=findViewById(R.id.price);
        item_delPrice=findViewById(R.id.delivery);
        item_order=findViewById(R.id.Order);
        item_Contact=findViewById(R.id.contact);
        item_number=findViewById(R.id.itemNumber);
        imageView=findViewById(R.id.itemImage);
        name=findViewById(R.id.itemname);
        price=findViewById(R.id.itemprice);
        del_ch=findViewById(R.id.itemDelivery);


        Favorite=findViewById(R.id.add_fav_btn);



        name.setText(intent.getStringExtra("name"));
        price.setText(intent.getStringExtra("price"));
        del_ch.setText(intent.getStringExtra("del"));
        imageView.setImageResource(intent.getIntExtra("img",0));


        SharedPreferences preferences=getSharedPreferences(Settings.Mode, 0);
        boolean night=preferences.getBoolean("night",false);
        if (night){
            name.setTextColor(getResources().getColor(R.color.white));
            price.setTextColor(getResources().getColor(R.color.white));
            del_ch.setTextColor(getResources().getColor(R.color.white));
            item_number.setTextColor(getResources().getColor(R.color.white));
            item_Contact.setTextColor(getResources().getColor(R.color.white));
            item_order.setTextColor(getResources().getColor(R.color.white));
            item_delPrice.setTextColor(getResources().getColor(R.color.white));
            item_name.setTextColor(getResources().getColor(R.color.white));
            item_price.setTextColor(getResources().getColor(R.color.white));
            Favorite.setTextColor(getResources().getColor(R.color.white));
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Item Added to Favorite", Toast.LENGTH_SHORT).show();
                if ( Vibrator_boolean){
                    vibrator.vibrate(30);
                }
                if (Sound_boolean){
                    mediaPlayer.start();
                }
                Firebase_Database();

                image=intent.getIntExtra("img",0);

            }
        });
    }

    protected void onResume() {
        SharedPreferences getsoundsp = getSharedPreferences("sound",MODE_PRIVATE);
        SharedPreferences getvibrationsp = getSharedPreferences("vib",MODE_PRIVATE);
        Sound_boolean=getsoundsp.getBoolean("sound",false);
        Vibrator_boolean= getvibrationsp.getBoolean("vib",false);

        super.onResume();
    }
    public boolean onSupportNavigateUp(){
        onBackPressed();
        if ( Vibrator_boolean){
            vibrator.vibrate(30);
        }
        if (Sound_boolean){
            mediaPlayer.start();
        }
        return true;
    }

    public void Firebase_Database(){

        fav.put("item_name",title);
        fav.put("item_price", it_price);
        fav.put("item_image", image);

        db.collection("Favorite").add(fav).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("Tag" ,"DocumentSnapshot added with ID: " + documentReference.getId());

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

}