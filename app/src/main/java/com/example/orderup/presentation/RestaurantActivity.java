package com.example.orderup.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.R;
import com.example.orderup.logic.RestaurantServices;

//This is the activity class showing the specific restaurant info.
public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        int position = getIntent().getIntExtra("position", 1);
        Restaurant restaurant = RestaurantServices.getRest(position);

        //Showing the restaurant Image.
        ImageView imageView = (ImageView) findViewById(R.id.Restbg);
        imageView.setBackgroundResource(getResources().getIdentifier(restaurant.getImagesURL(),"drawable", MainActivity.PACKAGE_NAME));

        //Showing the restaurant info.
        String restName, restDes, restLoca;
        restName = restaurant.getRestaurantName();
        restDes = restaurant.getRestaurantDescription();
        restLoca = restaurant.getRestaurant_location();
        TextView restInfo = (TextView) findViewById(R.id.RestInfo);
        restInfo.setText(restName+"\n"+restDes+"\n"+restLoca);

        //Showing the restaurant food item.
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.MenuRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MenuAdapter(RestaurantServices.getRestList().get(position).getMenuItems()));
    }
}