package com.example.orderup.presentation;

import static java.lang.Integer.parseInt;

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
public class MenuAdapter extends RecyclerView.Adapter<MenuHolder> {

    List<FoodItem> foods;

    public  final int MAX_ORDER_ITEMS = 100;
    MenuAdapter(List<FoodItem> foods)
    {
        this.foods = foods;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_view, parent, false);

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
        holder.FoodItemNumber.setText(String.valueOf(holder.textviewValueForFood));
        holder.FoodItemNumber.setText(String.valueOf(R.id.NumberOfFood));



        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.textviewValueForFood < MAX_ORDER_ITEMS)
                {
                    holder.textviewValueForFood++;
                    holder.FoodItemNumber.setText(String.valueOf(holder.textviewValueForFood));
                    Log.d("this",String.valueOf(holder.textviewValueForFood));

                }
                else {
                    ErrorPopUp.errorMsg(view.getContext(), "Max item number reached");

                }
            }
        });

        holder.subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.textviewValueForFood < MAX_ORDER_ITEMS && holder.textviewValueForFood > 0)
                {
                    holder.textviewValueForFood--;
                    holder.FoodItemNumber.setText(String.valueOf(holder.textviewValueForFood));
                    Log.d("this",String.valueOf(holder.textviewValueForFood));

                }
                else{
                    ErrorPopUp.errorMsg(view.getContext(), "Minimum item number reached");

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}

