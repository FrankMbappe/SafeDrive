package com.gm.safedrive.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import com.gm.safedrive.R;
import com.gm.safedrive.controllers.VehiclesActivity;

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

                if(firstLaunch){
                    Log.d(TAG, "SafeDrive app's first launch [FLKeyExists=" + preferences.contains(SAFEDRIVE_FIRST_TIME_LAUNCH) + ", FLKeyValue=" + firstLaunch + "]" );
                    startActivity(new Intent(SplashScreenActivity.this, FirstPubActivity.class));
                }
                else{
                    startActivity(new Intent(SplashScreenActivity.this, VehiclesActivity.class));
                }
            }
        }, 2500);
    }
}
