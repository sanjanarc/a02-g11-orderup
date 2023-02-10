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
import com.example.orderup.logic.Services;

public class UserAccountFragment extends Fragment {

    TextView infoContainer;
    Button addCardButton, logoutButton, addAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_user_account, container, false);

        infoContainer= (TextView) view.findViewById(R.id.infoContainer);
        infoContainer.setText(Services.getUserPersistence().getUserList().get(getActivity().getIntent().getStringExtra("email")).toString());
        infoContainer.setTextSize(30);

        addCardButton= (Button) view.findViewById(R.id.addCardButton);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pop up a dialog to add credit card.
            }
        });

        addAddress= (Button) view.findViewById(R.id.addAddressButton);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pop up a dialog to add address.
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