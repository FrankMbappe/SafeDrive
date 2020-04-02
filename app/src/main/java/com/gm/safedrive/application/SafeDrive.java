package com.gm.safedrive.application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class SafeDrive extends Application {
    public static final String MAIN_CHANNEL_ID = "mainChannel";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel mainChannel = new NotificationChannel(
                    MAIN_CHANNEL_ID,
                    "Main channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            mainChannel.setDescription("This is the SafeDrive main channel.");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(mainChannel);
        }
    }
}
