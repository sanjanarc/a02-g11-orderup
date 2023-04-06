package com.example.orderup.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.R;
import com.example.orderup.logic.RestaurantServices;
import com.example.orderup.logic.Search_algorithm;

import java.util.List;


//This is the home page UI class.
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RestaurantAdapter(RestaurantServices.getRestList()));

        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //Log.d("user input", s);
                String inputText = s;
                List<Restaurant>  newRestaurantList = Search_algorithm.searchRestaurant(inputText);
                recyclerView.setAdapter(new RestaurantAdapter(Search_algorithm.searchRestaurant(inputText)));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
//        searchView.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("hello:---->", "hello");
//            }
//        });
//        boolean searchBar = False;
//        if (searchBar) {
//           String inputText = "user typed in search bar";
//           recyclerView.setAdapter(new RestaurantAdapter(Search_algorithm.searchRestaurant(inputText)));
//        }
//        else {
//            recyclerView.setAdapter(new RestaurantAdapter(RestaurantServices.getRestList()));
//
//        }

        // Inflate the layout for this fragment
        return view;
    }


}