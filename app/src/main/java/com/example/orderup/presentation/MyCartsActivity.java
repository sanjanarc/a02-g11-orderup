package com.example.orderup.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.R;
import com.example.orderup.logic.UserServices;

import java.util.List;

public class MyCartsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_carts);

        List<FoodItem> foodList = UserServices.getUser().getFoodCart();

        RecyclerView recyclerView= findViewById(R.id.cartViewRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyCartAdapter(foodList));
    }
}