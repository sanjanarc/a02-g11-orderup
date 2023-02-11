package com.example.orderup.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.orderup.R;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserVerification;
import com.example.orderup.persistance.UserPersistence;

public class UserAccountFragment extends Fragment
{

    TextView infoContainer;

    Button addCardButton, logoutButton, addAddressButton;

    UserPersistence userPersistence= Services.getUserPersistence();

    UserVerification verify = new UserVerification();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_user_account, container, false);

        infoContainer= (TextView) view.findViewById(R.id.infoContainer);

        //Display the user info.
        infoContainer.setText(userPersistence.getUserList().get(getActivity().getIntent().getStringExtra("email")).toString());

        //Set the text size.
        infoContainer.setTextSize(30);

        addCardButton= (Button) view.findViewById(R.id.addCardButton);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCardPopUp();
            }
        });

        addAddressButton= (Button) view.findViewById(R.id.addAddressButton);
        addAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAddressPopUp();
            }
        });

        logoutButton= (Button) view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));

                //Remove current activity.
                getActivity().finish();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void addCardPopUp()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter your Credit Card Info:");
        View v = getLayoutInflater().inflate(R.layout.popup_add_credit_card, null);
        builder.setView(v);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                EditText cardNumInput= (EditText) v.findViewById(R.id.cardNumberInput);
                EditText cardCvcInput= (EditText) v.findViewById(R.id.cardCvcInput);
                EditText cardExpiryInput= (EditText) v.findViewById(R.id.cardExpiryInput);

                String cardNum = cardNumInput.getText().toString();
                String cardCvc = cardCvcInput.getText().toString();
                String cardExpiry = cardExpiryInput.getText().toString();

                if(verify.creditCardVerification(cardNum, cardCvc, cardExpiry, getActivity()))
                {
                    userPersistence.addCreditCard(getActivity().getIntent().getStringExtra("email"), cardNum, cardCvc, cardExpiry);
                }
            }
        });

        builder.show();
    }

    private void addAddressPopUp()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter your Address:");
        View v = getLayoutInflater().inflate(R.layout.popup_add_address, null);
        builder.setView(v);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                EditText addressInput= (EditText) v.findViewById(R.id.addressInput);

                String address= addressInput.getText().toString();

                if(verify.addressVerification(address, getActivity()))
                {
                    userPersistence.updateAddress(getActivity().getIntent().getStringExtra("email"), address);
                }
            }
        });

        builder.show();
    }
}