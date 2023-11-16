package com.example.gr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navegacion();
    }

    private void navegacion(){
        BottomNavigationView bnv = findViewById(R.id.bottomNavigationView);
        NavHostFragment nhf = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(bnv, nhf.getNavController());
    }
}