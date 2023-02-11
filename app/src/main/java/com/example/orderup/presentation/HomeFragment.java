package com.example.orderup.presentation;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.SearchView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import com.example.orderup.R;
import com.example.orderup.logic.Search_algorithm;




public class HomeFragment extends Fragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        //get ids
        SearchView searchView = findViewById(R.id.searchView);
        LinearLayout container = findViewById(R.id.textview_container);

        //search bar
        searchView.setQuery("Search Restaurants and Cuisines..",false);
        searchView.setOnQueryTextListener(new searchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<String> results = Search_algorithm.searchRestaurant(query);
                return true;

                if(results.isEmpty()){
                    String msg = "Your search does not match any restaurants on OrderUp";
                    TextView textView = new TextView(this);
                    textView.setText(msg);
                    textView.setTextSize(12);
                    container.addView(textView);
                }
                else {
                    for (int i = 0; i < results.size(); i++) {
                        TextView textView = new TextView(this);
                        textView.setText(results.get(i));
                        textView.setTextSize(18);
                        textView.setTextColor(Color.PURPLE);
                        container.addView(textView);
                    }

                }

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // handle search bar text changes here
                return true;
            }
        });
    }






}