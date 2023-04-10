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

import com.example.orderup.Objects.User;
import com.example.orderup.R;
import com.example.orderup.logic.MyException;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserServices;
import com.example.orderup.logic.UserVerification;

/**
 * This is the User page class.
 */
public class UserAccountFragment extends Fragment {

    TextView infoContainer;
    Button addCardButton, logoutButton, addAddressButton, redeemCardButton, membershipButton, cartButton;

    String userEmail = Services.getCurrentUser();

    // Create user verification object and passing the database.
    UserVerification userVerification = new UserVerification(Services.getUserPersistence());

    // Create user services object and passing the database and the user email.
    UserServices userServices = new UserServices(Services.getUserPersistence());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_account, container, false);

        infoContainer = (TextView) view.findViewById(R.id.infoContainer);

        // Event listener of the cart button.
        cartButton = (Button) view.findViewById(R.id.cartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Start the cart activity class.
                Intent intent = new Intent(getContext(), MyCartsActivity.class);
                startActivity(intent);

            }
        });

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

        //Event listener of the membership button.
        membershipButton = (Button) view.findViewById(R.id.membershipButton);
        membershipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memberPopUp();
            }
        });

        updateInfo(); // Display the user info.

        //Event listener of the logout button.
        logoutButton = (Button) view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Go back to the login page.
                startActivity(new Intent(getActivity(), LoginActivity.class));

                // Tell the system that the current user is logged out.
                Services.setCurrentUser(null);

                // Remove current activity.
                getActivity().finish();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    /**
     * This method pop up a window and prompt user to enter the gift card code.
     */
    private void redeemPopUp() {

        View view = getLayoutInflater().inflate(R.layout.popup_redeem_gift_card, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // Create the builder box object.
        builder.setTitle("Enter your Gift card code:"); // Set the box title.
        builder.setView(view);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Get input data from xml file.
                String cardNum = ((EditText) view.findViewById(R.id.redeemInput)).getText().toString();

                try {

                    // Verify and add gift card amount to user account.
                    userVerification.giftCardVerification(userEmail, cardNum);

                    // Refresh and display the user info.
                    updateInfo();

                } catch (Exception e) {

                    String msg;

                    if (e instanceof MyException.EXCEPTION_ILLEGAL_FORMAT) {

                        msg = "Error: Invalid gift card format, must be 16 digits.";

                    } else if (e instanceof MyException.EXCEPTION_ITEM_DOES_NOT_EXIST) {

                        msg = "Error: Gift card not found in our system.";

                    } else {

                        msg = e.getMessage();

                    }

                    // Display error message to user if the user entered an incorrect input.
                    ErrorPopUp.errorMsg(getActivity(), msg);
                }
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

                } catch (Exception e) {

                    String msg;

                    if (e instanceof MyException.EXCEPTION_ILLEGAL_FORMAT) {

                        msg = "Error: Incorrect Card Number Format.";

                    } else if (e instanceof MyException.EXCEPTION_TYPE_MISMATCH) {

                        msg = "Error: Card is not Visa, American Express or Mastercard.";

                    } else if (e instanceof MyException.EXCEPTION_CVC_LENGTH_DOES_NOT_MATCH) {

                        msg = "Error: Incorrect CVC length.";

                    } else if (e instanceof MyException.EXCEPTION_ILLEGAL_DATE_FORMAT) {

                        msg = "Error: Incorrect Expiry date length.";

                    } else if (e instanceof MyException.EXCEPTION_ILLEGAL_DATE_FORMAT2) {

                        msg = "Error: Incorrect Expiry date.";

                    } else if (e instanceof MyException.EXCEPTION_EMPTY_INPUT) {

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

                    // Update the info and display it.
                    updateInfo();

                } catch (Exception e) {

                    String msg;

                    if (e instanceof MyException.EXCEPTION_ILLEGAL_FORMAT) {

                        msg = "Error: Address format incorrect.";

                    } else if (e instanceof MyException.EXCEPTION_LOCATION_OUT_OF_BOUND) {

                        msg = "Error: The city you entered must be located within Manitoba.";

                    } else if (e instanceof MyException.EXCEPTION_LOCATION_OUT_OF_BOUND2) {

                        msg = "Error: Currently does not support other province other than Manitoba.";

                    } else if (e instanceof MyException.EXCEPTION_INVALID_POSTAL_CODE_LENGTH) {

                        msg = "Error: Invalid postal code length.";

                    } else if (e instanceof MyException.EXCEPTION_LOCATION_OUT_OF_BOUND3) {

                        msg = "Error: Postal Code not located in Manitoba.";

                    } else if (e instanceof MyException.EXCEPTION_INVALID_POSTAL_CODE_FORMAT) {

                        msg = "Error: Invalid postal code format.";

                    } else {

                        msg = e.getMessage();

                    }

                    // Display the result to user.
                    ErrorPopUp.errorMsg(getActivity(), msg);
                }
            }
        });

        builder.show();
    }

    /**
     * This method will pop up and prompt user for become a membership.
     */
    private void memberPopUp() {

        View v = getLayoutInflater().inflate(R.layout.popup_buy_membership, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are you sure you want to purchase membership for 25$?: ");
        builder.setView(v);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Connect to xml file.
                EditText confirmationInput = (EditText) v.findViewById(R.id.confirmationInput);

                //Get input data from xml file.
                String confirm = confirmationInput.getText().toString();

                if (confirm.equals("Yes")) {

                    try {

                        //Verify and add credit card to database.
                        userVerification.verifyMembershipPurchase(userEmail);

                        // Refresh and display the user info.
                        updateInfo();

                        // Change the user membership status.
                        membershipButton.setEnabled(false);

                    } catch (Exception e) {

                        String msg;

                        if (e instanceof MyException.EXCEPTION_ITEM_ALREADY_EXIST) {

                            msg = "Error: You are already a member!.";

                        } else if (e instanceof MyException.EXCEPTION_NO_CARD) {

                            msg = "Error: You do not have a credit card / Insufficient Funds.";

                        } else {

                            msg = e.getMessage();

                        }

                        //Display the result to user.
                        ErrorPopUp.errorMsg(getActivity(), msg);
                    }
                }
            }
        });

        builder.show();
    }

    /**
     * Display the account info to user.
     */
    private void updateInfo() {

        try {

            User user = userServices.getUser(Services.getCurrentUser());
            String membershipStatus;

            if (user.getMembership()==true) {

                membershipStatus = "Enabled";
                membershipButton.setEnabled(false);

            } else {

                membershipStatus = "Disabled";
                membershipButton.setEnabled(true);


            }

            // Formatting the message.
            String display = String.format("First name: %s\n" +
                    "Last name: %s\n" +
                    "Email: %s\n" +
                    "Address: %s\n" +
                    "Account balance: $ %s\n" +
                    "Membership status: %s", user.getFirstName(), user.getLastName(), userEmail, user.getAddress(), user.getBalance(), membershipStatus);

            // Display the message.
            infoContainer.setText(display);

        } catch (Exception e) {

            throw new NullPointerException();

        }
    }
}