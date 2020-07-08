package com.gm.safedrive.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import com.gm.safedrive.R;
import com.gm.safedrive.application.Current;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.controllers.VehiclesActivity;
import com.gm.safedrive.models.User;
import com.google.gson.Gson;

public class SplashScreenActivity extends AppCompatActivity {
    public static final String TAG = "SplashScreenActivity";
    public static final String SAFEDRIVE_FIRST_TIME_LAUNCH = "SAFEDRIVE_FIRST_TIME_LAUNCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean firstLaunch = preferences.getBoolean(SAFEDRIVE_FIRST_TIME_LAUNCH, true);
                String sessionUserExists = (String) Current.getObjectFromSharedPreferences(SplashScreenActivity.this, User.KEY, String.class);

                if(firstLaunch){
                    Log.d(TAG, "SafeDrive app's first launch [FLKeyExists=" + preferences.contains(SAFEDRIVE_FIRST_TIME_LAUNCH) + ", FLKeyValue=" + firstLaunch + "]" );
                    startActivity(new Intent(SplashScreenActivity.this, FirstPubActivity.class));
                }
                else{
                    if(sessionUserExists != null){
                        //UserBank.SESSION = new Gson().fromJson(sessionUserExists, User.class);
                        User loggedInUser = new UserBank().getSessionUser(SplashScreenActivity.this);
                        Log.d(TAG, "A session user already exists [Fullname=" + loggedInUser.getFullName() + ", FirebaseID=" + loggedInUser.getId() + "]" );
                        startActivity(new Intent(SplashScreenActivity.this, VehiclesActivity.class));
                    }
                    else{
                        Log.d(TAG, "There's no session already opened. Starting LoginActivity..." );
                        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    }
                }
            }
        }, 2500);
    }
}
