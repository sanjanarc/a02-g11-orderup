package com.example.orderup.presentation;
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
import com.example.orderup.Objects.User;
import com.example.orderup.R;
import com.example.orderup.logic.Services;
import com.example.orderup.persistance.UserPersistence;

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

        UserPersistence userPersistence = Services.getUserPersistence();

        FoodItem foodItem = foods.get(position);
        String foodName = foodItem.getItemName();
        String foodDes = foodItem.getItemDescription();
        String foodPrice = String.valueOf(foodItem.getItemPrice());
        String info = foodName+"\n"+foodDes+"\nPrice: "+foodPrice;
        holder.nameview.setText(info);
        int url = holder.imageview.getResources().getIdentifier(foodItem.getImageUrl(), "drawable", MainActivity.PACKAGE_NAME);
        holder.imageview.setBackgroundResource(url);


        holder.FoodItemNumber.setText(String.valueOf(0));

        holder.FoodItemNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    // Handle the "Done" button press event
                    holder.FoodItemNumber.setText(holder.FoodItemNumber.getText().toString());

                    if( Integer.parseInt(holder.FoodItemNumber.getText().toString()) > 100 || Integer.parseInt(holder.FoodItemNumber.getText().toString()) < 0) {
                        holder.FoodItemNumber.setText(String.valueOf(0));
                        ErrorPopUp.errorMsg(textView.getContext(), "Enter value between 0 and 100");
                    }
                    Log.d("After changing the value in editor action",holder.FoodItemNumber.getText().toString());
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
                    Log.d("AddButton",String.valueOf(temp));

                }
                else {
                    ErrorPopUp.errorMsg(view.getContext(), "Max item number reached");

                }
            }
        });

        holder.subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(holder.FoodItemNumber.getText().toString()) < MAX_ORDER_ITEMS && Integer.parseInt(holder.FoodItemNumber.getText().toString()) >= 0)
                {
                    int temp = Integer.parseInt(holder.FoodItemNumber.getText().toString());
                    if(Integer.parseInt(holder.FoodItemNumber.getText().toString()) != 0 )
                        temp--;
                    else
                        ErrorPopUp.errorMsg(view.getContext(), "Minimum item number reached");


                    holder.FoodItemNumber.setText(String.valueOf(temp));
                    Log.d("AddButton",String.valueOf(temp));

                }

            }
        });

        holder.submitBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = userPersistence.getUserTable().get(Services.getCurrentUser());
                assert user != null;
                user.addToFoodCart(foodItem);
                user.printFoodCart();

            }
        });


    }



    @Override
    public int getItemCount() {
        return foods.size();
    }
}

