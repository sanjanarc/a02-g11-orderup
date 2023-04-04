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

import java.util.List;

public class myCartActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);


        RecyclerView recyclerView= findViewById(R.id.myCartRecyclerView);

       List <FoodItem> foodList = UserServices.getUser().getFoodCart();
       Log.d("activityCart:",foodList.get(0).getItemName());
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Your Cart:");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new myCartAdapter(getApplicationContext(),foodList));
    }
}