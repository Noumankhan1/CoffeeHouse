package com.example.foodcafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;

import com.example.foodcafe.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Favourite extends AppCompatActivity {

    ActivityMainBinding binding;
    ListView list;
    ArrayList<ItemData> items = new ArrayList<ItemData>();
    CustomAdapter myAdapter;
    MediaPlayer mediaPlayer;
    Vibrator vibrator;
    Button remove;
    boolean night, Sound_boolean, Vibrator_boolean;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    String name,price;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_favourite);
        binding= ActivityMainBinding.inflate(getLayoutInflater());

        getSupportActionBar().setTitle("Favourite");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FD330100"));
        actionBar.setBackgroundDrawable(colorDrawable);
        list=findViewById(R.id.list);
        mediaPlayer= MediaPlayer.create(this,R.raw.tapclick);
        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);

        getFavourite();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                Toast.makeText(Favourite.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                myAdapter.notifyDataSetChanged();
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
        Intent intent = new Intent(Favourite.this, Mainmenu.class);
        startActivity(intent);
        if ( Vibrator_boolean){
            vibrator.vibrate(30);
        }
        if (Sound_boolean){
            mediaPlayer.start();
        }
        return true;
    }
public void getFavourite(){
    db.collection("Favorite").get().
            addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            name=document.getString("item_name");
                            price=document.getString("item_price");

                            items.add(new ItemData(name, image, price));

                            myAdapter=new CustomAdapter(Favourite.this,R.layout.fav_list, items);
                            list.setAdapter(myAdapter);
                            myAdapter.notifyDataSetChanged();
                        }
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

}

}