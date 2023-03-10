package com.example.orderup.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;
import com.example.orderup.Objects.Restaurant;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    MyAdapter myAdapter;
    List<Restaurant> restaurants;
    MyAdapter(List<Restaurant> restaurants)
    {
        this.restaurants = restaurants;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameview.setText(restaurants.get(position).getRestaurantName());
        holder.descriptionview.setText(restaurants.get(position).getRestaurantDescription());
       // holder.imageview.setImageResource(restaurants.get(position).getImagesURL());
        holder.position = holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
