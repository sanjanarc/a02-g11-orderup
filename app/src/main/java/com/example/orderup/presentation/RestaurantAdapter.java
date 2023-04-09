package com.example.orderup.presentation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;
import com.example.orderup.Objects.Restaurant;

import java.util.List;

/**
 * This class set the restaurant list align and show on the home fragment.
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantHolder> {

    List<Restaurant> restaurants;

    RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestaurantHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.nameview.setText(restaurant.getRestaurantName()); // Get the restaurant name.
        holder.descriptionview.setText(restaurant.getRestaurantDescription()); // Get restaurant description.

        Log.d("Printing Restaurant name",restaurant.getRestaurantName());
        Log.d("Printing image name",restaurant.getImagesURL());
        int url = holder.imageview.getResources().getIdentifier(restaurant.getImagesURL(), "drawable", MainActivity.PACKAGE_NAME); // Get image url.
        holder.imageview.setBackgroundResource(url); // Set the restaurant background image.
        holder.position = holder.getAdapterPosition(); // Pass current position back to super class and knowing that which restaurant get clicked.
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
