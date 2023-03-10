package com.example.orderup.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.logic.RestaurantServices;

import java.util.List;

//This class is the structure of the My view holder class.
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>
{
    List<Restaurant> restaurants;
    MyAdapter(List<Restaurant> restaurants)
    {
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.nameview.setText(RestaurantServices.getRestList().get(position).getRestaurantName());
        holder.descriptionview.setText(RestaurantServices.getRestList().get(position).getRestaurantDescription());
        int url = holder.imageview.getResources().getIdentifier(RestaurantServices.getRestList().get(position).getImagesURL(), "drawable", MainActivity.PACKAGE_NAME);
        holder.imageview.setImageResource(url);
        holder.position = holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return RestaurantServices.getRestList().size();
    }
}
