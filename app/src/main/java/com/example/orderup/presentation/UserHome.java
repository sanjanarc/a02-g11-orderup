package com.example.orderup.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.R;
import com.example.orderup.persistance.stub.RestaurantPersistenceStub;

import java.util.List;

public class UserHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RestaurantPersistenceStub restaurants = new RestaurantPersistenceStub() ;
        List<Restaurant> restaurantsList = restaurants.getRestaurants();
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), restaurantsList));

    }
}


