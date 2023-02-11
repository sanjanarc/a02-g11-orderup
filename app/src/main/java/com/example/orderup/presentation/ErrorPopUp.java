package com.example.orderup.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ErrorPopUp
{
    //Display a dialog on the screen and show the message to user.
    public static void errorMsg(Context context, String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(msg);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Do nothing.
            }
        });

        builder.show();
    }
}
