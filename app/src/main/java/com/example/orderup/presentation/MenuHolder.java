package com.example.orderup.presentation;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.User;
import com.example.orderup.R;
import com.example.orderup.logic.Services;
import com.example.orderup.persistance.UserPersistence;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//This class is the structure of the menu.
public class MenuHolder extends RecyclerView.ViewHolder {
    ImageView imageview;
    TextView nameview;

    FloatingActionButton addButton = null;
    FloatingActionButton subtractButton = null;
    TextView FoodItemNumber;

    Button submitBButton;

    public MenuHolder(@NonNull View itemView) {
        super(itemView);
        imageview = itemView.findViewById(R.id.foodImage);
        nameview = itemView.findViewById(R.id.foodInfo);

        UserPersistence userPersistence = Services.getUserPersistence();


        addButton = (FloatingActionButton) itemView.findViewById(R.id.addButton);
        subtractButton = (FloatingActionButton) itemView.findViewById(R.id.subtractButton);
        FoodItemNumber = (TextView) itemView.findViewById(R.id.NumberOfFood);

        submitBButton = (Button) itemView.findViewById(R.id.Submit);
    }
}
