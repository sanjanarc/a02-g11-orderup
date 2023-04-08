package com.example.orderup.presentation;

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
 * This class is the structure of Menu in restaurant page.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuHolder> {

    List<FoodItem> foods; // The list of food items that will be presented in the menu view

    MenuAdapter(List<FoodItem> foods) {
        this.foods = foods;
    }

    // setting up the adapter that the cart will use to display each foot item
    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_view, parent, false);

        return new MenuHolder(itemView);
    }

    // the viewbindholder that will set the texts, images and the prices for the food items
    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {

        FoodItem foodItem = foods.get(position);
        String foodName = foodItem.getItemName();
        String foodDes = foodItem.getItemDescription();
        String foodPrice = String.valueOf(foodItem.getItemPrice());
        String info = foodName + "\n" + foodDes + "\nPrice: " + foodPrice;
        holder.nameView.setText(info);
        int url = holder.imageview.getResources().getIdentifier(foodItem.getImageUrl(), "drawable", MainActivity.PACKAGE_NAME);
        holder.imageview.setBackgroundResource(url);

        //Event listener of the submit button.
        holder.submitBButton.setText("Add");
        holder.submitBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new UserServices(Services.getUserPersistence()).getUser(Services.getCurrentUser());
                user.addToFoodCart(foodItem, Integer.parseInt(holder.foodItemNumber.getText().toString()));

                //Display message to user that Item has been added to cart.
                ErrorPopUp.errorMsg(view.getContext(), "Item added");
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}

