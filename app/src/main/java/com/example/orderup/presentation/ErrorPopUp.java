package com.example.orderup.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

//This class will pop up a window to display given message to user.
public class ErrorPopUp
{
    //Display the given message to user.
    public static void errorMsg(Context context, String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(msg);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
}