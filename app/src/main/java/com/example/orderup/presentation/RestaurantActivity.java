package com.example.orderup.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.R;
import com.example.orderup.logic.RestaurantServices;
import com.example.orderup.logic.Services;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * This is the activity class showing the specific restaurant info.
 */
public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        int position = getIntent().getIntExtra("position", 1);

        RestaurantServices temp = new RestaurantServices(Services.getRestaurantPersistence());
        Restaurant restaurant = temp.getRest(position);

        // Showing the restaurant Image.
        ImageView imageView = (ImageView) findViewById(R.id.Restbg);
        imageView.setBackgroundResource(getResources().getIdentifier(restaurant.getImagesURL(), "drawable", MainActivity.PACKAGE_NAME));

        // Showing the restaurant info.
        TextView restName = (TextView) findViewById(R.id.RestName);
        restName.setText(restaurant.getRestaurantName());
        TextView restDes = (TextView) findViewById(R.id.RestDes);
        restDes.setText(restaurant.getRestaurantDescription());
        TextView restLoca = (TextView) findViewById(R.id.RestLoca);
        restLoca.setText(restaurant.getRestaurant_location());
        TextView servHours = (TextView) findViewById(R.id.RestServ);
        servHours.setText("Service Hours:");
        TextView restHours = (TextView) findViewById(R.id.RestHours);
        restHours.setText(restaurant.getServiceHours());

        // Showing the restaurant food item.
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.MenuRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MenuAdapter(restaurant.getMenuItems()));

        //View cart button listener.
        Button ViewCartButton = (Button) findViewById(R.id.ViewCartButtonXML);
        ViewCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MyCartsActivity.class);
                startActivity(intent); // Start the cart activity class.
            }
        });

        // Comment button listener.
        FloatingActionButton commentButton = findViewById(R.id.comment_Button);
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FrameLayout frameLayout = findViewById(R.id.commentContainer); // Get the comment box.
                loadComment(restaurant); // Update or retrieve data from comment's database to the comment section.
                boolean moveable; // Flag to disable or able the activity of the background when the comment box is turn on.

                if (frameLayout.getHeight() == 0) {

                    frameLayout.getLayoutParams().height = 700;
                    moveable = true; // Deactivation of the background activity.

                } else {

                    frameLayout.getLayoutParams().height = 0;
                    moveable = false;
                }

                frameLayout.setLayoutParams(frameLayout.getLayoutParams()); // Set the comment box size.
                recyclerView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return moveable;
                    }
                });
            }
        });

        // Send button listener.
        Button sendButton = findViewById(R.id.sendComment);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText input = findViewById(R.id.inputComment);
                String msg = input.getText().toString();

                if (!msg.equals("")) { // Message is not empty.

                    temp.insertComment(restaurant, Services.getCurrentUser() + ":\n" + msg);
                    input.setText(""); // Clear the input box.
                    loadComment(restaurant); // Update the comment section.

                } else {
                    ErrorPopUp.errorMsg(view.getContext(), "Input comment cannot be empty.");
                }
            }
        });
    }

    /**
     * This method will update the comment section.
     *
     * @param restaurant the specific restaurant.
     */
    public void loadComment(Restaurant restaurant) {

        TextView commentSec = findViewById(R.id.commentSection);
        List<String> commentList = restaurant.getUserComment();
        String comments = "";

        // Build up the comments.
        for (String i : commentList) {
            comments += i + "\n\n";
        }

        //Display the comments.
        commentSec.setText(comments);
    }
}