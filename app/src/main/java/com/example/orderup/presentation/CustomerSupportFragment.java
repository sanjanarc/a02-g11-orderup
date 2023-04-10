package com.example.orderup.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.orderup.R;

/**
 * The customer support UI page.
 */
public class CustomerSupportFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_support, container, false);

//        // Live Chat button listener.
//        Button liveChat = (Button) view.findViewById(R.id.liveChat);
//        liveChat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ErrorPopUp.errorMsg(getContext(), "Function currently unavailable. PLease email us.");
//            }
//        });

//        // Email us button listener.
//        Button emailUs = (Button) view.findViewById(R.id.emailUs);
//        emailUs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("This message will never reach our server:");
//                View v = getLayoutInflater().inflate(R.layout.popup_email_us, null);
//                builder.setView(v);
//                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//
//                builder.show();
//            }
//        });

        // Inflate the layout for this fragment
        return view;
    }
}