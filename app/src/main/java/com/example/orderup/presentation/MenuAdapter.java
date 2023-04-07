package com.example.orderup.presentation;

import static com.example.orderup.logic.Services.getCurrentUser;
import static com.example.orderup.logic.Services.getUserPersistence;
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
import com.example.orderup.logic.UserServices;
import com.example.orderup.presentation.MainActivity;
import com.example.orderup.logic.Services;

import java.util.List;

//This class is the structure of all the adapter.
public class MenuAdapter extends RecyclerView.Adapter<MenuHolder> {

    // The list of food items that will be presented in the menu view
    List<FoodItem> foods;
    public  final int MAX_ORDER_ITEMS = 100;

    MenuAdapter(List<FoodItem> foods)
    {
        this.foods = foods;
    }

    // setting up the adapter that the cart will use to display each foot item
    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_view, parent, false);

        return new MenuHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_view,parent,false));
    }

    // the viewbindholder that will set the texts, images and the prices for the food items
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
        holder.FoodItemNumber.setText(String.valueOf(1));

        // the text view listener that will save the number of food items the user wants to order( could be typed using the keyboard)
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
                    return true;
                }
                return false;
            }
        });


        // this button increments the number of the particular food item that could be ordered
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(Integer.parseInt(holder.FoodItemNumber.getText().toString()) < MAX_ORDER_ITEMS)
                {
                    int temp = Integer.parseInt(holder.FoodItemNumber.getText().toString());
                    temp++;
                    holder.FoodItemNumber.setText(String.valueOf(temp));
                }
                else
                {
                    ErrorPopUp.errorMsg(view.getContext(), "Max item number reached");
                }
            }
        });

        // this button is used to decrement the number of food item that the user could order
        holder.subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(holder.FoodItemNumber.getText().toString()) < MAX_ORDER_ITEMS && Integer.parseInt(holder.FoodItemNumber.getText().toString()) > 0)
                {
                    int temp = Integer.parseInt(holder.FoodItemNumber.getText().toString());
                    if(Integer.parseInt(holder.FoodItemNumber.getText().toString()) != 1 )
                        temp--;
                    else
                        ErrorPopUp.errorMsg(view.getContext(), "Minimum item number reached");

                    holder.FoodItemNumber.setText(String.valueOf(temp));
                }
            }
        });

        holder.submitBButton.setText("Add");
        holder.submitBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert UserServices.getUser() != null;
                if(UserServices.FoodItemExists(foodItem))
                {
                    foodItem.setNumItems(Integer.parseInt(holder.FoodItemNumber.getText().toString()) + foodItem.getNumItems());
                }
                else
                {
                    UserServices.getUser().addToFoodCart(foodItem, Integer.parseInt(holder.FoodItemNumber.getText().toString()));
                }
                ErrorPopUp.errorMsg(view.getContext(), "Item added");
            }
        });

    }
    @Override
    public int getItemCount() {
        return foods.size();
    }
}

