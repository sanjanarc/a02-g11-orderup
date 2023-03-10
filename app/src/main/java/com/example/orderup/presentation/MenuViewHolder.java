package com.example.orderup.presentation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

//This class is the structure of the menu.
public class MenuViewHolder extends RecyclerView.ViewHolder {
    ImageView imageview;
    TextView nameview, descriptionview;
    int position = 0;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        nameview = itemView.findViewById(R.id.foodname);
        descriptionview = itemView.findViewById(R.id.fooddescription);
    }
}
