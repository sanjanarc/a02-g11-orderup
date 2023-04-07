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
public class UserAccountFragment extends Fragment {
    TextView infoContainer;
    Button addCardButton, logoutButton, addAddressButton, redeemCardButton;
    String display;
    String userEmail = Services.getCurrentUser();

    // Create user verification object and passing the database.
    UserVerification userVerification = new UserVerification(Services.getUserPersistence());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_account, container, false);

        infoContainer = (TextView) view.findViewById(R.id.infoContainer);
        updateInfo();

        //Event listener of the add credit card button.
        addCardButton = (Button) view.findViewById(R.id.addCardButton);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCardPopUp();
            }
        });

        //Event listener of the add address button.
        addAddressButton = (Button) view.findViewById(R.id.addAddressButton);
        addAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAddressPopUp();
            }
        });

        //Event listener of the redeem button.
        redeemCardButton = (Button) view.findViewById(R.id.redeemCardButton);
        redeemCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redeemPopUp();
            }
        });

        //Event listener of the logout button.
        logoutButton = (Button) view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Change the activity to login page.
                startActivity(new Intent(getActivity(), LoginActivity.class));

                //Tell the system that the current user is logged out.
                Services.setCurrentUser(null);

                //Remove current activity.
                getActivity().finish();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    //This method pop up a window and prompt user to enter the gift card code.
    private void redeemPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter your Gift card code:");
        View v = getLayoutInflater().inflate(R.layout.popup_redeem_gift_card, null);
        builder.setView(v);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Connect to xml file.
                EditText giftCard = (EditText) v.findViewById(R.id.redeemInput);

                //Get input data from xml file.
                String cardNum = giftCard.getText().toString();

                //Verify and add gift card amount to user account.
                String result = UserVerification.giftCardVerification(userEmail, cardNum);

                //Display the result to user.
                if ("" != result) {
                    ErrorPopUp.errorMsg(getActivity(), result);
                }
                updateInfo();
            }
        });
        builder.show();
    }

    /**
     * This method will pop up a window to prompt user to enter credit card info.
     */
    private void addCardPopUp() {

        View view = getLayoutInflater().inflate(R.layout.popup_add_credit_card, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // Create the builder box object.
        builder.setTitle("Enter your Credit Card Info:"); // Set the box title.
        builder.setView(view);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Get input data from user.
                String cardNum = ((EditText) view.findViewById(R.id.cardNumberInput)).getText().toString();
                String cardCvc = ((EditText) view.findViewById(R.id.cardCvcInput)).getText().toString();
                String cardExpiry = ((EditText) view.findViewById(R.id.cardExpiryInput)).getText().toString();

                try {

                    // Verify the credit card info and store the data if no error.
                    userVerification.creditCardVerification(userEmail, cardNum, cardCvc, cardExpiry);

                } catch (Throwable e) {

                    String msg = "";

                    if (e instanceof) {

                        msg = "Error: Incorrect Card Number Format.";

                    } else if (e instanceof) {

                        msg = "Error: Card is not Visa, American Express or Mastercard.";

                    } else if (e instanceof) {

                        msg = "Error: Incorrect CVC length.";

                    } else if (e instanceof) {

                        msg = "Error: Incorrect Expiry date length.";

                    } else if (e instanceof) {

                        msg = "Error: Incorrect Expiry date.";

                    } else if (e instanceof) {

                        msg = "Missing Field: Please check you have entered all fields.";

                    } else {

                        msg = e.getMessage();

                    }

                    // Display the error message.
                    ErrorPopUp.errorMsg(getActivity(), msg);
                }
            }
        });

        builder.show();
    }

    /**
     * This method will pop up a window to prompt user to enter their address.
     */
    private void addAddressPopUp() {

        View view = getLayoutInflater().inflate(R.layout.popup_add_address, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // Create the builder box object.
        builder.setTitle("Enter your Address:"); // Set the box title.
        builder.setView(view);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Get input data from xml file.
                String street = ((EditText) view.findViewById(R.id.streetInput)).getText().toString();
                String city = ((EditText) view.findViewById(R.id.cityInput)).getText().toString();
                String province = ((EditText) view.findViewById(R.id.provinceInput)).getText().toString();
                String postal = ((EditText) view.findViewById(R.id.postalInput)).getText().toString();
                String address = street + ", " + city + ", " + province + ", " + postal;

                try {

                    // Verify the input date and add to database.
                    userVerification.addressVerification(street, city, province, postal, userEmail, address);
                    updateInfo(); // Update the info and display it.

                } catch (Throwable e) {

                    String msg = "";

                    // Display the result to user.
                    ErrorPopUp.errorMsg(getActivity(), msg);
                }
            }
        });

        builder.show();
    }

    /**
     * Display the account info to user.
     */
    private void updateInfo() {

        // Formatting the message.
        display = String.format("First name: %s\n" +
                "Last name: %s\n" +
                "Email: %s\n" +
                "Address: %s\n" +
                "Account balance: $ %s", UserServices.getFirstName(userEmail), UserServices.getLastName(userEmail), userEmail, UserServices.getAddress(userEmail), UserServices.getBalance(userEmail));

        infoContainer.setText(display);
    }
}