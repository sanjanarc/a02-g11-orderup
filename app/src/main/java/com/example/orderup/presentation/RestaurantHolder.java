package com.example.orderup.presentation;

import static com.example.orderup.logic.Services.getUserPersistence;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.User;
import com.example.orderup.R;
import com.example.orderup.logic.Services;

//This class provide function for MyAdapter and also handle the click event and call related restaurant activity.
public class RestaurantHolder extends RecyclerView.ViewHolder
{
    ImageView imageview;
    TextView nameview, descriptionview;

    int position = 0;

    public RestaurantHolder(@NonNull View itemView)
    {
        super(itemView);
        imageview = itemView.findViewById(R.id.imageview);
        nameview = itemView.findViewById(R.id.Name);
        descriptionview = itemView.findViewById(R.id.description);
        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), RestaurantActivity.class);
                intent.putExtra("position", position);
                view.getContext().startActivity(intent);
            }
        });

    }
}
