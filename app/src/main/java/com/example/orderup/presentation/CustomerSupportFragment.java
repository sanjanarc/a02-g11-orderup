package com.example.orderup.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.orderup.R;
import com.example.orderup.logic.UserVerification;

public class CustomerSupportFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_customer_support, container, false);
        Button liveChat = (Button) view.findViewById(R.id.liveChat);
        Button emailUs = (Button) view.findViewById(R.id.emailUs);

        liveChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ErrorPopUp.errorMsg(getContext(), "Function currently unavailable. PLease email us.");
            }
        });

        emailUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("This message will never reach our server:");
                View v = getLayoutInflater().inflate(R.layout.popup_email_us, null);
                builder.setView(v);
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}