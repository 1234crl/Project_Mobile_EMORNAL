package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.project.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.project.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use ViewBinding to inflate the layout
        Log.d("MainActivity", "Inflating layout...");
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "Layout inflated successfully.");

        // Find the BottomNavigationView in the layout
        navView = findViewById(R.id.nav_view);


        // Set up AppBarConfiguration with the fragment IDs in the bottom navigation
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_checkin, R.id.navigation_learn, R.id.navigation_statistic, R.id.navigation_profile)
                .build();

        // Set up NavController and link it to the nav_host_fragment in activity_main.xml
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        //CALENDAR
//        CalendarView calendarView = findViewById(R.id.calendar_view);



    }
}
