package com.example.orderup.presentation;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.R;
import com.example.orderup.logic.UserServices;

public class myCartActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);

        //getting the food item from the user's cart
        int position = getIntent().getIntExtra("position", 0);

        FoodItem foodItem = UserServices.getUser().getFoodCart().get(position);

        TextView textView = findViewById(R.id.textView2);
        textView.setText("Your Cart:");



        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.myCartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new myCartAdapter(UserServices.getUser().getFoodCart()));

        Log.d("foodinacartttttttttttt", UserServices.getUser().getFoodCart().get(position).getItemName());
    }
}