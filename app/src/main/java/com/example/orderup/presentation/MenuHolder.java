package com.example.orderup.presentation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;
import com.google.android.material.button.MaterialButton;

//This class is the structure of the menu.
public class MenuHolder extends RecyclerView.ViewHolder {
    ImageView imageview;
    TextView nameview;
    MaterialButton removeBtn, addBtn;

    public MenuHolder(@NonNull View itemView) {
        super(itemView);
        imageview = itemView.findViewById(R.id.foodImage);
        nameview = itemView.findViewById(R.id.foodInfo);
        removeBtn = itemView.findViewById(R.id.removeItemButton);
        addBtn = itemView.findViewById(R.id.addItemButton);
    }
}
