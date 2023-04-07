package com.example.orderup.presentation;

import static android.media.CamcorderProfile.get;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.R;

import java.util.List;

//This class is the structure of all the adapter.
public class MyCartAdapter extends RecyclerView.Adapter<MenuHolder> {
    List<FoodItem> foods;

    public final int MAX_ORDER_ITEMS = 100;

    MyCartAdapter(List<FoodItem> foods)
    {
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

        holder.nameview.setText(foods.get(position).getItemName());
        holder.imageview.setImageResource(foods.get(position).getItem_id());
        int url = holder.imageview.getResources().getIdentifier(foods.get(position).getImageUrl(), "drawable", MainActivity.PACKAGE_NAME);
        holder.imageview.setBackgroundResource(url);

        holder.FoodItemNumber.setText(String.valueOf(foods.get(position).getNumItems()));

        int temp = position;
        holder.FoodItemNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    // Handle the "Done" button press event
                    holder.FoodItemNumber.setText(holder.FoodItemNumber.getText().toString());

                    if( Integer.parseInt(holder.FoodItemNumber.getText().toString()) > 100 || Integer.parseInt(holder.FoodItemNumber.getText().toString()) < 0) {
                        holder.FoodItemNumber.setText(String.valueOf(1));
                        ErrorPopUp.errorMsg(textView.getContext(), "Enter value between 0 and 100");
                    }
                    foods.get(temp).setNumItems(Integer.parseInt(holder.FoodItemNumber.getText().toString()));
                    return true;
                }
                return false;
            }
        });

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Integer.parseInt(holder.FoodItemNumber.getText().toString()) < MAX_ORDER_ITEMS)
                {
                    int temp = Integer.parseInt(holder.FoodItemNumber.getText().toString());
                    temp++;
                    holder.FoodItemNumber.setText(String.valueOf(temp));
                    foods.get(position).setNumItems(Integer.parseInt(holder.FoodItemNumber.getText().toString()));
                }
                else
                {
                    ErrorPopUp.errorMsg(view.getContext(), "Max item number reached");
                }
            }
        });

        holder.subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(holder.FoodItemNumber.getText().toString()) < MAX_ORDER_ITEMS && Integer.parseInt(holder.FoodItemNumber.getText().toString()) > 0) {
                    int temp = Integer.parseInt(holder.FoodItemNumber.getText().toString());
                    if (Integer.parseInt(holder.FoodItemNumber.getText().toString()) != 1)
                        temp--;
                    holder.FoodItemNumber.setText(String.valueOf(temp));
                    foods.get(position).setNumItems(Integer.parseInt(holder.FoodItemNumber.getText().toString()));
                }
                else
                    ErrorPopUp.errorMsg(view.getContext(), "Minimum item number reached");
            }
        });

        holder.submitBButton.setText("Remove");
        holder.submitBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foods.remove(foods.get(temp));
                ErrorPopUp.errorMsg(view.getContext(), "Item Removed");
            }
        });

    }
    @Override
    public int getItemCount() {
        return foods.size();
    }
}

