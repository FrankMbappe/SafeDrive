package com.gm.safedrive.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.gm.safedrive.R;
import com.gm.safedrive.controllers.fragments.HomeFragment;
import com.gm.safedrive.controllers.fragments.RemindersFragment;
import com.gm.safedrive.controllers.fragments.StatisticsFragment;
import com.gm.safedrive.controllers.interfaces.MainHeaderActivityUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomNavBar();
    }

    public void initBottomNavBar() {
        mBottomNav = findViewById(R.id.activity_main_bottom_navigation);
        /* J'ajoute un OnItemSelectedListener qui au moment d'une sélection, récupère l'Id du MenuItem correspondant et
         * l'insère (ou le remplace) dans mon fragment_container */
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d(TAG, "onNavigationItemSelected method invoked.");
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.menu_main_bnav_home:
                        selectedFragment = new HomeFragment();
                        Log.d(TAG, "SelectedFragment is a HomeFragment.");
                        break;
                    case R.id.menu_main_bnav_stats:
                        selectedFragment = new StatisticsFragment();
                        Log.d(TAG, "SelectedFragment is a StatisticsFragment.");
                        break;
                    case R.id.menu_main_bnav_reminder:
                        selectedFragment = new RemindersFragment();
                        Log.d(TAG, "SelectedFragment is a RemindersFragment.");
                        break;
                }

                // Le remplacement s'effectue ici
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragment_container, selectedFragment).commit();
                Log.d(TAG, "New fragment loaded successfully.");
                return true;
            }
        });

        //Je définis l'élément de la Bottom Navigation Bar sélectionné par défaut
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragment_container, new HomeFragment()).commit();
    }
}