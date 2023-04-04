package com.example.orderup.presentation;

import android.content.Context;
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
public class myCartAdapter extends RecyclerView.Adapter<myCartHolder> {

    Context context;
    List<FoodItem> foods;

    myCartAdapter(Context context,List<FoodItem> foods)
    {
        this.context = context;
        this.foods = foods;
        Log.d("myadapterconstructor:     ", this.foods.get(0).getItemDescription());
    }

    @NonNull
    @Override
    public myCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myCartHolder(LayoutInflater.from(context).inflate(R.layout.mycartadapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myCartHolder holder, int position) {

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
        return 0;
    }
}

