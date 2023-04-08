package com.example.orderup.presentation;

import static com.example.orderup.logic.Services.getUserPersistence;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.Objects.User;
import com.example.orderup.R;
import com.example.orderup.logic.Services;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * This class holds the navigation bar function and allow three fragment to switch.
 */
public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    // Fragments that show up on the navigation bar.
    HomeFragment homeFragment = new HomeFragment();
    UserAccountFragment userAccountFragment = new UserAccountFragment();
    CustomerSupportFragment customerSupportFragment = new CustomerSupportFragment();

    // Used to retrieve image.
    public static String PACKAGE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        PACKAGE_NAME = getApplicationContext().getPackageName();

        //Event listener of the Navigation bar.
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                //Get data from parameter to see which tab/fragment user want to switch to.
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.user_account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, userAccountFragment).commit();
                        return true;
                    case R.id.customer_support:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, customerSupportFragment).commit();
                        return true;
                }

                return false;
            }
        });
    }
}