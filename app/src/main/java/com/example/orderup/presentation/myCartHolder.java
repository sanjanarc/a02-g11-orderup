package com.example.orderup.presentation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

public class myCartHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView;

    public myCartHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.CartfoodInfo);
        textView = itemView.findViewById(R.id.CartfoodInfo);
    }
}
