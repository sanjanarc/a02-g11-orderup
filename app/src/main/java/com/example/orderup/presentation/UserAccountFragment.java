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
import com.example.orderup.logic.UserServices;
import com.example.orderup.logic.UserVerification;

//This is the User page UI class.
public class UserAccountFragment extends Fragment
{
    EditText giftCardCode;
    TextView infoContainer, accountBalance;

    Button addCardButton, logoutButton, addAddressButton;
    String giftcard;
    String userEmail = Services.getCurrentUser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_user_account, container, false);

        //Organized the user info.
        String display = String.format("First name: %s\n" +
                "Last name: %s\n" +
                "Email: %s\n" +
                "Address: %s", UserServices.getFirstName(userEmail), UserServices.getLastName(userEmail), userEmail, UserServices.getAddress(userEmail));

        //Connect to xml file.
        infoContainer= (TextView) view.findViewById(R.id.infoContainer);
        accountBalance= (TextView) view.findViewById(R.id.accountBalance);
        giftCardCode = (EditText) view.findViewById(R.id.giftCardCode);

        //Display the message to user.
        accountBalance.setText("$" + UserServices.getBalance(userEmail));
        infoContainer.setText(display);
        infoContainer.setTextSize(30);

        //Event listener of the add credit card button.
        addCardButton= (Button) view.findViewById(R.id.addCardButton);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCardPopUp();
            }
        });

        //Event listener of the add address button.
        addAddressButton= (Button) view.findViewById(R.id.addAddressButton);
        addAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAddressPopUp();
            }
        });

        //Event listener of the logout button.
        logoutButton= (Button) view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Change the activity to login page.
                startActivity(new Intent(getActivity(), LoginActivity.class));

                //Tell the system that the current user is logged out.
                Services.setCurrentUser(null);

                //Remove current activity.
                getActivity().finish();
            }
        });

        //Event listener of the redeem button.
        Button redeemCardButton = (Button) view.findViewById(R.id.redeemCardButton);
        redeemCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                giftcard = giftCardCode.getText().toString();
                //Verify and add credit card to database.
                String result = UserVerification.giftCardVerification(userEmail,giftcard);

                //Display the result to user.
                if("" != result) {
                    ErrorPopUp.errorMsg(getActivity(), result);
                } else {
                    accountBalance.setText("$" + UserServices.getBalance(userEmail));
                }

                //addCardBalance();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    //This method will pop up a window to prompt user to enter credit card info.
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
                //Connect to xml file.
                EditText cardNumInput= (EditText) v.findViewById(R.id.cardNumberInput);
                EditText cardCvcInput= (EditText) v.findViewById(R.id.cardCvcInput);
                EditText cardExpiryInput= (EditText) v.findViewById(R.id.cardExpiryInput);

                //Get input data from xml file.
                String cardNum = cardNumInput.getText().toString();
                String cardCvc = cardCvcInput.getText().toString();
                String cardExpiry = cardExpiryInput.getText().toString();

                //Verify and add credit card to database.
                String result = UserVerification.creditCardVerification(userEmail, cardNum, cardCvc, cardExpiry);

                //Display the result to user.
                ErrorPopUp.errorMsg(getActivity(), result);
            }
        });
        builder.show();
    }

    //This method will pop up a window to prompt user to enter their address.
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
                //Connect to xml file.
                EditText streetInput= (EditText) v.findViewById(R.id.streetInput);
                EditText cityInput= (EditText) v.findViewById(R.id.cityInput);
                EditText provinceInput= (EditText) v.findViewById(R.id.provinceInput);
                EditText postalInput= (EditText) v.findViewById(R.id.postalInput);

                //Get input data from xml file.
                String street= streetInput.getText().toString();
                String city= cityInput.getText().toString();
                String province= provinceInput.getText().toString();
                String postal= postalInput.getText().toString();
                String address = street + ", " + city + ", " + province + ", " + postal;

                //Verify the input address and add to the database.
                String result = UserVerification.addressVerification(street, city, province, postal, userEmail, address);

                //Display the result to user.
                ErrorPopUp.errorMsg(getActivity(), result);

                String display = String.format("First name: %s\n" +
                        "Last name: %s\n" +
                        "Email: %s\n" +
                        "Address: %s", UserServices.getFirstName(userEmail), UserServices.getLastName(userEmail), userEmail, UserServices.getAddress(userEmail));
                infoContainer.setText(display);
                infoContainer.setTextSize(30);
            }
        });
        builder.show();
    }
}