package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.orderup.R;
import com.example.orderup.persistance.DatabaseHelper;

public class UserAccountFragment extends Fragment {

    DatabaseHelper db;
    TextView infoContainer;
    Button addCardButton, logoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_user_account, container, false);
        //db= new DatabaseHelper(this);
        infoContainer= (TextView) view.findViewById(R.id.infoContainer);
        infoContainer.setText("Name: ABC"+"\nEmail: ABC@ABC");
        infoContainer.setTextSize(20);

        addCardButton= (Button) view.findViewById(R.id.addCardButton);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pop up a dialog to add credit card.
            }
        });

        logoutButton= (Button) view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}