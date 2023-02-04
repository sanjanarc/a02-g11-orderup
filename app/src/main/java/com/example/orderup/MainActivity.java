package com.example.orderup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /** Called when the user touches the button */
    public void sendMessage(View view) {
        // Do something in response to button click
        Log.d("this","Jackson, I Love You!");
    }

}