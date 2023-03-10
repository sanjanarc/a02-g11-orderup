package com.example.orderup.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.R;

import java.util.List;

//This class is the structure of all the adapter.
public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

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
        holder.position = holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}

