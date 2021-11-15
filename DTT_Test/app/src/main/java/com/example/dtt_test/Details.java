package com.example.dtt_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Getting house Data from previous activity with shared preferences
         SharedPreferences sharedPreferencesH = getSharedPreferences("House", Context.MODE_PRIVATE);
        int bed_sh = sharedPreferencesH.getInt("bedrooms",0);
        int id_sh = sharedPreferencesH.getInt("id",0);
        int bath_sh = sharedPreferencesH.getInt("bathrooms",0);
        int size_sh = sharedPreferencesH.getInt("size",0);
        int lat_sh = sharedPreferencesH.getInt("latitude",0);
        int lon_sh = sharedPreferencesH.getInt("longitude",0);
        int distance_sh = sharedPreferencesH.getInt("dist",0);
        int price_sh = sharedPreferencesH.getInt("price",0);
        String desc_sh = sharedPreferencesH.getString("description","Null");


        ImageView imagedetail = findViewById(R.id.imagedetail);
        TextView beddetail = findViewById(R.id.beddetail);
        TextView bathdetail = findViewById(R.id.bathdetail);
        TextView sizedetail = findViewById(R.id.sizedetail);
        TextView locdetail = findViewById(R.id.locdetail);
        TextView pricedetail = findViewById(R.id.pricedetail);
        TextView descdetail = findViewById(R.id.description);
        ImageView mapimg=findViewById(R.id.maps);
        //Displaying Static maps picture Error shown in Sheet
        String mapurl = "https://maps.googleapis.com/maps/api/staticmap?center=+"+lat_sh+","+lon_sh+"&zoom=12&size=400x400&key=AIzaSyBpZLbBSGVJwf84-FhjIjgzsfLdTYDmn0o";
        Glide.with(this).load(mapurl).centerCrop().placeholder(R.drawable.ic_launcher_foreground)
                .into(mapimg);

        //Displaying House Picture
        int idim=id_sh-1;
        String imgurl ="https://intern.docker-dev.d-tt.nl/uploads/house"+idim+".jpg";
        Glide.with(this).load(imgurl).centerCrop().placeholder(R.drawable.ic_launcher_foreground)
                .into(imagedetail);

        pricedetail.setText("$ "+Integer.toString(price_sh));
        beddetail.setText(Integer.toString(bed_sh));
        bathdetail.setText(Integer.toString(bath_sh));
        sizedetail.setText(Integer.toString(size_sh));
        locdetail.setText(Integer.toString(distance_sh)+" KM");
        descdetail.setText(desc_sh);
    }
    public void clickEvent(View v)
    {
        Intent i = new Intent(Details.this,MapActivity.class);
        startActivity(i);
    }
}