package com.example.orderup.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.orderup.R;
import com.example.orderup.logic.Services;
import com.example.orderup.persistance.DatabaseHelper;
import com.example.orderup.persistance.UserPersistence;

public class UserAccountFragment extends Fragment {

    TextView infoContainer, accountBalance;
    Button addCardButton, logoutButton, addAddressButton;

    UserPersistence userPersistence= Services.getUserPersistence();
    DatabaseHelper myDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_user_account, container, false);
        accountBalance = (TextView) view.findViewById(R.id.accountBalance);
        accountBalance.setText("$" + userPersistence.getBalance(getActivity().getIntent().getStringExtra("email")));

        infoContainer= (TextView) view.findViewById(R.id.infoContainer);
        infoContainer.setText(userPersistence.getUserList().get(getActivity().getIntent().getStringExtra("email")).toString());
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
                getActivity().finish();
            }
        });

        Button redeemCardButton = (Button) view.findViewById(R.id.redeemCardButton);
        redeemCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rand = (int)Math.floor(Math.random() * (5) + 1);
                switch(rand) {
                    case 1: userPersistence.addBalance(getActivity().getIntent().getStringExtra("email"), 5.00F);
                        break;
                    case 2: userPersistence.addBalance(getActivity().getIntent().getStringExtra("email"), 10.00F);
                        break;
                    case 3: userPersistence.addBalance(getActivity().getIntent().getStringExtra("email"), 20.00F);
                        break;
                    case 4: userPersistence.addBalance(getActivity().getIntent().getStringExtra("email"), 50.00F);
                        break;
                    case 5: userPersistence.addBalance(getActivity().getIntent().getStringExtra("email"), 100.00F);
                        break;
                }

                accountBalance.setText("$" + userPersistence.getBalance(getActivity().getIntent().getStringExtra("email")));
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void addCardPopUp(){

        myDatabase = new DatabaseHelper(getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter your Credit Card Info:");
        View v = getLayoutInflater().inflate(R.layout.popup_add_credit_card, null);
        builder.setView(v);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText cardNumInput= (EditText) v.findViewById(R.id.cardNumberInput);
                EditText cardCvcInput= (EditText) v.findViewById(R.id.cardCvcInput);
                EditText cardExpiryInput= (EditText) v.findViewById(R.id.cardExpiryInput);

                int cardNum = 0;
                int cardCvc = 0;
                String cardExpiry = cardExpiryInput.getText().toString();

                if(cardNumInput.getText() != null) {
                    cardNum = Integer.parseInt(cardNumInput.getText().toString());
                } else {
                    ErrorPopUp er = new ErrorPopUp();
                    er.errorMsg(getActivity(), "Missing Field: Please check you have entered all fields.");
                }


                if(cardCvcInput.getText() != null) {
                    cardCvc = Integer.parseInt(cardCvcInput.getText().toString());
                } else {
                    ErrorPopUp er = new ErrorPopUp();
                    er.errorMsg(getActivity(), "Missing Field: Please check you have entered all fields.");
                }

                if(Integer.toString(cardNum).length() != 16) {
                    ErrorPopUp er = new ErrorPopUp();
                    er.errorMsg(getActivity(), "Error: Incorrect Card Number Format");
                }

                if(Integer.toString(cardNum).charAt(0) != '2' || Integer.toString(cardNum).charAt(0) != '3'
                        || Integer.toString(cardNum).charAt(0) != '4' || Integer.toString(cardNum).charAt(0) != '5') {
                    ErrorPopUp er = new ErrorPopUp();
                    er.errorMsg(getActivity(), "Error: Card is not Visa, American Express or Mastercard");
                }

                if(Integer.toString(cardCvc).length() != 3 || Integer.toString(cardCvc).length() != 4) {
                    ErrorPopUp er = new ErrorPopUp();
                    er.errorMsg(getActivity(), "Error: Incorrect CVC length");
                }

                if(cardExpiry.length() != 5) {
                    if (cardExpiry.charAt(2) != '/' || (cardExpiry.charAt(0) != 0 && (cardExpiry.charAt(0) != 1))
                    || (cardExpiry.charAt(0) == 1 && Character.getNumericValue(cardExpiry.charAt(1)) >= 3)) {
                        ErrorPopUp er = new ErrorPopUp();
                        er.errorMsg(getActivity(), "Error: Incorrect Expiry");

                    }
                }

                boolean isUpdate = myDatabase.updateData(id,null, null, null,null,cardNum,cardCvc,cardExpiry,null, -1.00F);
                if(isUpdate) {
                    Log.d("this", "USER DATA SUCCESSFULLY UPDATED");
                }
                userPersistence.addCreditCard(getActivity().getIntent().getStringExtra("email"), cardNum, cardCvc, cardExpiry);
            }
        });

        builder.show();
    }

    private void addAddressPopUp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter your Address:");
        View v = getLayoutInflater().inflate(R.layout.popup_add_address, null);
        builder.setView(v);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText addressInput= (EditText) v.findViewById(R.id.addressInput);

                String address= addressInput.getText().toString();

                userPersistence.updateAddress(getActivity().getIntent().getStringExtra("email"), address);
            }
        });

        builder.show();
    }

    // Search by Email or GetID
}