package com.example.orderup.presentation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity
{
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment=new HomeFragment();
    UserAccountFragment userAccountFragment=new UserAccountFragment();
    CustomerSupportFragment customerSupportFragment=new CustomerSupportFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                switch (item.getItemId()){
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