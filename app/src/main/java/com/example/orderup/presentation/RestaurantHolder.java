package com.example.orderup.presentation;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;

/**
 * This class provide function for Restaurant adapter and also handle the click event and call related restaurant activity.
 */
public class RestaurantHolder extends RecyclerView.ViewHolder {

    ImageView imageview;
    TextView nameview, descriptionview;

    int position = 0;

    /**
     * Constructor.
     *
     * @param itemView the view object of the parent.
     */
    public RestaurantHolder(@NonNull View itemView) {

        super(itemView);

        // Get the reference from .xml file.
        imageview = itemView.findViewById(R.id.imageview);
        nameview = itemView.findViewById(R.id.Name);
        descriptionview = itemView.findViewById(R.id.description);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Go to the specific restaurant.
                Intent intent = new Intent(view.getContext(), RestaurantActivity.class);
                intent.putExtra("position", position + 1); // Pass the info that which restaurant got selected.
                view.getContext().startActivity(intent);

            }
        });
    }
}