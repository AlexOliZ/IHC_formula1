package com.example.formula1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    TO DO:
        adicionar maneira de filtrar na toolbar
        adicionar on click listener no MyAdapter
        adicionar on click listener na checkBox nas notificações
*/

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView  = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,new Schedule()).commit();

        //setSupportActionBar(findViewById(R.id.toolbar));

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch(item.getItemId()){
                    case R.id.Forum:
                        selectedFragment = new Forum();
                        break;
                    case R.id.Teams:
                        selectedFragment = new Teams();
                        break;
                    case R.id.Statistics:
                        selectedFragment = new Statistics();
                        break;
                    case R.id.Settings:
                        selectedFragment = new Settings();
                        break;
                    case R.id.Schedule:
                        selectedFragment = new Schedule();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,selectedFragment).commit();
                return true;
            }
        };

        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.toolbar_search,menu);
            return true;
        }

}