package com.example.orderup.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.R;

import java.util.List;

//This class is the structure of all the adapter.
public class MenuAdapter extends RecyclerView.Adapter<MenuHolder> {

    List<FoodItem> foods;
    MenuAdapter(List<FoodItem> foods)
    {
        this.foods = foods;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {
        FoodItem foodItem = foods.get(position);
        String foodName = foodItem.getItemName();
        String foodDes = foodItem.getItemDescription();
        String foodPrice = String.valueOf(foodItem.getItemPrice());
        String info = foodName+"\n"+foodDes+"\nPrice: "+foodPrice;
        holder.nameview.setText(info);
        int url = holder.imageview.getResources().getIdentifier(foodItem.getImageUrl(), "drawable", MainActivity.PACKAGE_NAME);
        holder.imageview.setBackgroundResource(url);

        //Event listener of the remove button of the food item.
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Event listener of the add button of the food item.
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}

