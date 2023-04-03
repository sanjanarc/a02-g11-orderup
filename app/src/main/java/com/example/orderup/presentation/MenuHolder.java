package com.example.orderup.presentation;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//This class is the structure of the menu.
public class MenuHolder extends RecyclerView.ViewHolder {
    ImageView imageview, imageViewCart;
    TextView nameview,nameViewCart;

    FloatingActionButton addButton = null;
    FloatingActionButton subtractButton = null;
    TextView FoodItemNumber;
    TextView myCartFoodItemNumber;


    Button submitBButton,ViewCartButton;

    public MenuHolder(@NonNull View itemView) {
        super(itemView);
        imageview = itemView.findViewById(R.id.foodImage);
        nameview = itemView.findViewById(R.id.foodInfo);

        imageViewCart = itemView.findViewById(R.id.CartfoodImage);
        nameViewCart = itemView.findViewById(R.id.CartfoodInfo);


        addButton = (FloatingActionButton) itemView.findViewById(R.id.addButton);
        subtractButton = (FloatingActionButton) itemView.findViewById(R.id.subtractButton);
        FoodItemNumber = (TextView) itemView.findViewById(R.id.NumberOfFood);



        submitBButton = (Button) itemView.findViewById(R.id.Submit);

    }
}
