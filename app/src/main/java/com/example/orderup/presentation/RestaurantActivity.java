package com.example.orderup.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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
    }
}