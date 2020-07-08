package com.gm.safedrive.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.controllers.adapters.VehiclesRVAdapter;
import com.gm.safedrive.models.Vehicle;

import java.util.ArrayList;
import java.util.Objects;

public class VehiclesActivity extends AppCompatActivity {
    public static final String TAG = "VehiclesActivity";

    private ImageButton mControlAddVehicle;
    private ImageButton mControlRefreshList;
    private RecyclerView mUsersVehiclesRecyclerview;
    private TextView mLoggedInAs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);
        Log.d(TAG, "onCreate: started.");

        mControlAddVehicle = findViewById(R.id.activity_vehicles_control_add);
        mControlRefreshList = findViewById(R.id.activity_vehicles_control_refresh);
        mLoggedInAs = findViewById(R.id.activity_vehicles_logged_in_user_output);


        mLoggedInAs.setText(String.format(getString(R.string.str_logged_in_as), new UserBank().getSessionUser(this).getFullName()));

        mControlAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VehiclesActivity.this, BrandsActivity.class));
            }
        });

        mControlRefreshList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(mUsersVehiclesRecyclerview.getAdapter()).notifyDataSetChanged();
            }
        });

        // Si c'est la première fois que l'utilisateur démarre l'appli, lancement de la méthode prévue à cet effet
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean firstLaunch = preferences.getBoolean(SplashScreenActivity.SAFEDRIVE_FIRST_TIME_LAUNCH, true);
        Log.d(TAG, "SafeDrive app's first launch [FLKeyExists=" + preferences.contains(SplashScreenActivity.SAFEDRIVE_FIRST_TIME_LAUNCH) + ", FLKeyValue=" + firstLaunch + "]" );

        if(firstLaunch){
            onFirstLaunch();
        }

        new UserBank().updateSessionUserFromOnlineDb(this);

        // TODO: initRecyclerView()
        initRecyclerView(new UserBank().getSessionUser(this).getVehicles());
    }


    public void initRecyclerView(ArrayList<Vehicle> mVehicles){
        Log.d(TAG, "initRecyclerView: started.");

        mUsersVehiclesRecyclerview = findViewById(R.id.activity_vehicles_recyclerview);
        VehiclesRVAdapter adapter = new VehiclesRVAdapter(this, mVehicles);
        mUsersVehiclesRecyclerview.setAdapter(adapter);
        mUsersVehiclesRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }


    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            this.finishAffinity();
            this.finishAndRemoveTask();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click once again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private void onFirstLaunch(){
        // Je sauvegarde la valeur du booléen SAFEDRIVE_ON_FIRST_LAUNCH dans les SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(SplashScreenActivity.SAFEDRIVE_FIRST_TIME_LAUNCH, false);
        editor.apply();
    }
}
