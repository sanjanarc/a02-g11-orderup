package com.example.orderup.presentation;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

//This class provide function for MyAdapter and also handle the click event and call related restaurant activity.
public class MyViewHolder extends RecyclerView.ViewHolder
{
    ImageView imageview;
    TextView nameview, descriptionview;
    int position = 0;

    public MyViewHolder(@NonNull View itemView)
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
