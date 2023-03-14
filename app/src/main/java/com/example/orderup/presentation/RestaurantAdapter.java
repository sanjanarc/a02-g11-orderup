package com.example.orderup.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;
import com.example.orderup.Objects.Restaurant;

import java.util.List;

//This class set the restaurant list align and show on the home fragment.
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantHolder>
{
    List<Restaurant> restaurants;
    RestaurantAdapter(List<Restaurant> restaurants)
    {
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new RestaurantHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantHolder holder, int position)
    {
        holder.nameview.setText(restaurants.get(position).getRestaurantName());
        holder.descriptionview.setText(restaurants.get(position).getRestaurantDescription());
        int url = holder.imageview.getResources().getIdentifier(restaurants.get(position).getImagesURL(), "drawable", MainActivity.PACKAGE_NAME);
        holder.imageview.setBackgroundResource(url);
        holder.position = holder.getAdapterPosition(); //Pass current position back to super class and knowing that which restaurant get clicked.
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
