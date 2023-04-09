package com.example.orderup.presentation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.User;
import com.example.orderup.R;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserServices;

import java.util.List;

/**
 * This class is the structure of cart adapter.
 */
public class MyCartAdapter extends RecyclerView.Adapter<MenuHolder> {

    List<FoodItem> foods; // The list of food items that will be presented in the cart view.

    /**
     * Constructor.
     *
     * @param foods a list of food item.
     */
    MyCartAdapter(List<FoodItem> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.menu_view, parent, false);
        return new MenuHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {

        FoodItem foodItem = foods.get(position);
        holder.nameView.setText(foodItem.getItemName()); // Set the food name.
        Log.d("----------------------",String.valueOf(foodItem.getItem_id()));
//        holder.imageview.setImageResource(foodItem.getItem_id());
        int url = holder.imageview.getResources().getIdentifier(foodItem.getImageUrl(), "drawable", MainActivity.PACKAGE_NAME);
        holder.imageview.setBackgroundResource(url); // Set the background image.
        holder.foodItemNumber.setText(String.valueOf(foodItem.getNumItems()));

        // Submit button listener.
        holder.submitBButton.setText("Remove");
        holder.submitBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new UserServices(Services.getUserPersistence()).getUser(Services.getCurrentUser());
                user.removeFoodFromCart(foodItem, Integer.parseInt(holder.foodItemNumber.getText().toString()));

                ErrorPopUp.errorMsg(view.getContext(), "Item Removed");
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}

