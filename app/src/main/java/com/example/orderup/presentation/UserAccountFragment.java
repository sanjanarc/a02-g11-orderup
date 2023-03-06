package com.example.orderup.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import com.example.orderup.logic.UserVerification;
import com.example.orderup.persistance.DatabaseHelper;
import com.example.orderup.persistance.UserPersistence;

public class UserAccountFragment extends Fragment
{

    TextView infoContainer, accountBalance;

    Button addCardButton, logoutButton, addAddressButton;

    UserPersistence userPersistence= Services.getUserPersistence();

    UserVerification verify = new UserVerification();

    DatabaseHelper myDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        myDatabase = new DatabaseHelper(getActivity());

        View view= inflater.inflate(R.layout.fragment_user_account, container, false);
        accountBalance = (TextView) view.findViewById(R.id.accountBalance);
        Cursor res = myDatabase.getAllData();
        accountBalance.setText("$" + getBalance(getActivity().getIntent().getStringExtra("email")));
        infoContainer= (TextView) view.findViewById(R.id.infoContainer);

        //Display the user info.
        //infoContainer.setText(userPersistence.getUserList().get(getActivity().getIntent().getStringExtra("email")).toString());

        //Set the text size.
        //infoContainer.setTextSize(30);

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

                boolean isUpdate = myDatabase.updateData(searchByEmail(getActivity().getIntent().getStringExtra("email")),null, null, null,null,
                        cardNum,cardCvc,cardExpiry,null, -1.00F);
                if(isUpdate) {
                    Log.d("this", "USER DATA SUCCESSFULLY UPDATED");
                }

                if(userPersistence != null) {
                    if (verify.creditCardVerification(cardNum, cardCvc, cardExpiry, getActivity())) {
                        userPersistence.addCreditCard(getActivity().getIntent().getStringExtra("email"), cardNum, cardCvc, cardExpiry);
                    }
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
                    //myDatabase.updateData(null,null,null,null,null,null,null,null, address, -1.00F);
                    userPersistence.updateAddress(getActivity().getIntent().getStringExtra("email"), address);
                }
            }
        });

        builder.show();
    }

    public String searchByEmail(String email) {
        myDatabase = new DatabaseHelper(getActivity());

        String currId = null;
        boolean found = false;
        Cursor res = myDatabase.getAllData();
        while(res.moveToNext() && found == false) {
            if(email.equals(res.getString(1))) {
                found = true;
                currId = res.getString(0);
            }
        }
        return currId;
    }

    public String getBalance(String email) {
        myDatabase = new DatabaseHelper(getActivity());

        String currBal = null;
        boolean found = false;
        Cursor res = myDatabase.getAllData();
        while(res.moveToNext() && found == false) {
            if(email.equals(res.getString(1))) {
                found = true;
                currBal = res.getString(9);
            }
        }
        return currBal;
    }
}