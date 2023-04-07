package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.R;
import com.example.orderup.logic.RestaurantServices;
import com.example.orderup.logic.Services;

/**
 * This is the home page UI class.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.user_home, container, false);
        RestaurantServices temp = new RestaurantServices(Services.getRestaurantPersistence());
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RestaurantAdapter(temp.getRestList()));


        Button button = (Button) view.findViewById(R.id.ViewCartHomeButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyCartsActivity.class);

                //Start the main activity class.
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}