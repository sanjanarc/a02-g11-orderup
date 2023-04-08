package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.R;
import com.example.orderup.logic.RestaurantServices;
import com.example.orderup.logic.Search_algorithm;
import com.example.orderup.logic.Services;

import java.util.List;

/**
 * This is the home page UI class.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_home, container, false);

        RestaurantServices temp = new RestaurantServices(Services.getRestaurantPersistence());
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RestaurantAdapter(temp.getRestList()));

        // Cart button event listener.
        Button button = (Button) view.findViewById(R.id.ViewCartHomeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), MyCartsActivity.class); // Open the cart activity.
                startActivity(intent); //Start the cart activity class.
            }
        });

        // Search bar event listener.
        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                // Set the new restaurant list to home page.
                recyclerView.setAdapter(new RestaurantAdapter(Search_algorithm.searchRestaurant(s)));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}