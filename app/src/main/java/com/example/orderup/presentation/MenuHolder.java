package com.example.orderup.presentation;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

/**
 * The card holder class.
 */
public class MenuHolder extends RecyclerView.ViewHolder {

    //All the necessary button that are used in the adapter (names are self explanatory)
    ImageView imageview;
    TextView nameView;
    ImageButton addButton = null;
    ImageButton subtractButton = null;
    TextView foodItemNumber;
    Button submitBButton;
    public final int MAX_ORDER_ITEMS = 100; // The maximum number of each item the user could order

    /**
     * Constructor.
     *
     * @param itemView the context view of the parent activity.
     */
    public MenuHolder(@NonNull View itemView) {

        super(itemView);

        // Get the reference from .xml file.
        imageview = itemView.findViewById(R.id.foodImage);
        nameView = itemView.findViewById(R.id.foodInfo);
        submitBButton = (Button) itemView.findViewById(R.id.Submit);

        // TextView listener that will save the number of food items the user wants to order( could be typed using the keyboard)
        foodItemNumber = (TextView) itemView.findViewById(R.id.NumberOfFood);
        foodItemNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_DONE) {

                    // Handle the "Done" button press event
                    foodItemNumber.setText(foodItemNumber.getText().toString());

                    if (Integer.parseInt(foodItemNumber.getText().toString()) > 100 || Integer.parseInt(foodItemNumber.getText().toString()) < 0) {

                        foodItemNumber.setText(String.valueOf(1));
                        ErrorPopUp.errorMsg(textView.getContext(), "Enter value between 0 and 100");

                    }

                    return true;
                }

                return false;
            }
        });

        // Add button listener increments the number of the particular food item that could be ordered.
        addButton = itemView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.parseInt(foodItemNumber.getText().toString()) < MAX_ORDER_ITEMS) {

                    int temp = Integer.parseInt(foodItemNumber.getText().toString());
                    temp++;
                    foodItemNumber.setText(String.valueOf(temp));

                } else { // Cannot add too much food to cart.

                    ErrorPopUp.errorMsg(view.getContext(), "Max item number reached");

                }
            }
        });

        // Minus button listener is used to decrement the number of food item that the user could order
        subtractButton = itemView.findViewById(R.id.subtractButton);
        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.parseInt(foodItemNumber.getText().toString()) < MAX_ORDER_ITEMS && Integer.parseInt(foodItemNumber.getText().toString()) > 0) {

                    int temp = Integer.parseInt(foodItemNumber.getText().toString());

                    if (Integer.parseInt(foodItemNumber.getText().toString()) != 1)
                        temp--;

                    foodItemNumber.setText(String.valueOf(temp));

                } else { // Cannot go to negative number.

                    ErrorPopUp.errorMsg(view.getContext(), "Minimum item number reached");

                }
            }
        });
    }
}
