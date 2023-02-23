package com.example.orderup.presentation;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

public class MenuViewHolder extends RecyclerView.ViewHolder {
    ImageView imageview;
    TextView nameview, descriptionview;
    int position = 0;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
//        imageview = itemView.findViewById(R.id.imageview);
        nameview = itemView.findViewById(R.id.foodname);
        descriptionview = itemView.findViewById(R.id.fooddescription);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), RestaurantActivity.class);
//                intent.putExtra("position", position);
//                view.getContext().startActivity(intent);
//                Log.d("this", ""+position);
//            }

//        });
    }
}
