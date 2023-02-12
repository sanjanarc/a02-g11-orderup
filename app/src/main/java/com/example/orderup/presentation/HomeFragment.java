package com.example.orderup.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import com.example.orderup.R;
import com.example.orderup.logic.Search_algorithm;




public class HomeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);


        //get ids
        SearchView searchView = view.findViewById(R.id.searchView);
        LinearLayout containerText = view.findViewById(R.id.textview_container);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        titleTextView.setText(R.string.home_title);



        //search bar
        searchView.setQuery("Search Restaurants and Cuisines..",true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<String> results = new ArrayList<>();
                results = Search_algorithm.searchRestaurant(query);

                if(results.isEmpty()){
                    TextView textView = new TextView(getActivity());
                    String msg = "Your search does not match any restaurants on OrderUp";
                    textView.setText(msg);

                    containerText.addView(textView);
                }
                else {
                    for (int i = 0; i < results.size(); i++) {
                        TextView textView = new TextView(getActivity());
                        textView.setText(results.get(i));
                        textView.setTextSize(40);
                        //textView.setTextColor(Color.PURPLE);
                        containerText.addView(textView);
                    }

                }
                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // handle search bar text changes here
                return true;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }







}