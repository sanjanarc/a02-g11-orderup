package com.example.orderup.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.R;

import java.util.List;

//This class is the structure of all the adapter.
public class MyCartAdapter extends RecyclerView.Adapter<MyCartHolder> {
    List<FoodItem> foods;

    MyCartAdapter(List<FoodItem> foods)
    {
        this.foods = foods;
    }

    @NonNull
    @Override
    public MyCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.mycart_view, parent, false);
        return new MyCartHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyCartHolder holder, int position) {

        holder.textView.setText(foods.get(position).getItemName());
        holder.imageView.setImageResource(foods.get(position).getItem_id());
        int url = holder.imageView.getResources().getIdentifier(foods.get(position).getImageUrl(), "drawable", MainActivity.PACKAGE_NAME);
        holder.imageView.setBackgroundResource(url);

    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {
//
//        FoodItem foodItem = foods.get(position);
//        String foodName = foodItem.getItemName();
//        String foodDes = foodItem.getItemDescription();
//        String foodPrice = String.valueOf(foodItem.getItemPrice());
//        String info = foodName+"\n"+foodDes+"\nPrice: "+foodPrice;
//        holder.nameViewCart.setText(info);
//        int url = holder.imageViewCart.getResources().getIdentifier(foodItem.getImageUrl(), "drawable", MainActivity.PACKAGE_NAME);
//        holder.imageViewCart.setBackgroundResource(url);
//        Log.d("The myCartAdapter class:::::::::", foodName);
//
//
//    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}

