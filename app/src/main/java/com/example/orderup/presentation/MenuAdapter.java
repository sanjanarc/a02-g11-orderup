package com.example.orderup.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.R;
import com.example.orderup.presentation.MyAdapter;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    MyAdapter myAdapter;
    List<FoodItem> foods;
    MenuAdapter(List<FoodItem> foods)
    {
        this.foods = foods;
    }


    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_view,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.nameview.setText(foods.get(position).getItemName());
        holder.descriptionview.setText(foods.get(position).getItemDescription());
//        holder.imageview.setImageResource(foods.get(position).getImageUrl());
        holder.position = holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}

