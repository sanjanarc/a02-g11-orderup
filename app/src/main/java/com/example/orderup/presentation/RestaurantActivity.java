package com.example.orderup.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderup.R;
import com.example.orderup.logic.Services;
import com.example.orderup.persistance.RestaurantPersistence;

public class RestaurantActivity extends AppCompatActivity {

    RestaurantPersistence restaurantPersistence = Services.getRestaurantPersistence();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        int position = getIntent().getIntExtra("position", 1);
        Log.d("this", ""+ restaurantPersistence.getRestaurantSequential().get(position).getRestaurantName());

        TextView restinfo;
        restinfo = (TextView) findViewById(R.id.RestInfo);
        restinfo.setText(restaurantPersistence.getRestaurantSequential().get(position).toString());

        ImageView Restbg;
        Restbg = (ImageView) findViewById(R.id.Restbg);
        Restbg.setImageResource(restaurantPersistence.getRestaurantSequential().get(position).getImagesURL());

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.MenuRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MenuAdapter(restaurantPersistence.getRestaurantSequential().get(position).getMenuItems()));



    }
}