package com.example.orderup.presentation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.R;

import java.util.List;

//This class is the structure of all the adapter.
public class myCartAdapter extends RecyclerView.Adapter<MenuHolder> {

    List<FoodItem> foods;

    myCartAdapter(List<FoodItem> foods)
    {
        this.foods = foods;
    }
    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mycartadapter, parent, false);

        return new MenuHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycartadapter,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {

        FoodItem foodItem = foods.get(position);
        String foodName = foodItem.getItemName();
        String foodDes = foodItem.getItemDescription();
        String foodPrice = String.valueOf(foodItem.getItemPrice());
        String info = foodName+"\n"+foodDes+"\nPrice: "+foodPrice;
        holder.nameViewCart.setText(info);
        int url = holder.imageViewCart.getResources().getIdentifier(foodItem.getImageUrl(), "drawable", MainActivity.PACKAGE_NAME);
        holder.imageViewCart.setBackgroundResource(url);
        Log.d("The myCartAdapter class:::::::::", foodName);


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

