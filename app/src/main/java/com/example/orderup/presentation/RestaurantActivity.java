package com.example.orderup.presentation;

import static com.example.orderup.logic.Services.getUserPersistence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.Objects.User;
import com.example.orderup.R;
import com.example.orderup.logic.RestaurantServices;
import com.example.orderup.logic.Services;

import org.w3c.dom.Text;

//This is the activity class showing the specific restaurant info.
public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        int position = getIntent().getIntExtra("position", 1);
        Restaurant restaurant = RestaurantServices.getRest(position);

        //Showing the restaurant Image.
        ImageView imageView = (ImageView) findViewById(R.id.Restbg);
        imageView.setBackgroundResource(getResources().getIdentifier(restaurant.getImagesURL(),"drawable", MainActivity.PACKAGE_NAME));

        //Showing the restaurant info.
        TextView restName = (TextView) findViewById(R.id.RestName);
        restName.setText(restaurant.getRestaurantName());
        TextView restDes = (TextView) findViewById(R.id.RestDes);
        restDes.setText(restaurant.getRestaurantDescription());
        TextView restLoca = (TextView) findViewById(R.id.RestLoca);
        restLoca.setText(restaurant.getRestaurant_location());

        //Showing the restaurant food item.
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.MenuRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MenuAdapter(restaurant.getMenuItems()));


        Button ViewCartButton = (Button) findViewById(R.id.ViewCartButtonXML);

        ViewCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Email: ",MainActivity.user.getEmail());
                MainActivity.user.printFoodCart();

            }
        });
    }
}