package com.example.orderup.presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.persistance.RestaurantPersistance;
import com.example.orderup.persistance.stub.RestaurantPersistenceStub;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Restaurant> restaurants;


    MyAdapter(Context context, List<Restaurant> restaurants)
    {
        this.context = context;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameview.setText(restaurants.get(position).getRestaurantName());
        holder.descriptionview.setText(restaurants.get(position).getRestaurantDescription());
      //  holder.imageview.setImageResource(restaurants.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
