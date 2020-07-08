package com.gm.safedrive.application;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

import com.gm.safedrive.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Current {
    public static final String TAG = "Current";
    public static final String SAFEDRIVE_PREFERENCES = "SAFEDRIVE_PREFERENCES";
    private String FullDateFormat = "yyyy/MM/dd HH:mm:ss";
    private String FullTimeFormat = "HH:mm:ss";
    private String DateFormat = "yyyy/MM/dd";
    private String TimeFormat = "HH:mm";

    private Location Location;
    private Date Date;


    public Current(){
        Location = null; //TODO: Current location
        Date = new Date();
    }

    public Current(Context context){
        FullDateFormat = context.getString(R.string.full_date_format);
        FullTimeFormat = context.getString(R.string.full_time_format);
        DateFormat = context.getString(R.string.date_format);
        TimeFormat = context.getString(R.string.time_format);

        Location = null; //TODO: Current location
        Date = new Date();
    }

    public static boolean stringIsNullOrWhitespace(String... values){
        boolean err = false;
        for (String val : values){
            err = err || (val == null || val.trim().length() <= 0);
        }
        return err;
    }

    public Date getDate() {
        return Date;
    }


    // Full Date
    public String getFullDateToString(){
        return new SimpleDateFormat(FullDateFormat).format(Date);
    }
    public String getFullDateToString(Date date){
        return new SimpleDateFormat(FullDateFormat).format(date);
    }


    // Today date
    public String getDateToString(){
        return new SimpleDateFormat(DateFormat).format(Date);
    }
    public String getDateToString(Date date){
        return new SimpleDateFormat(DateFormat).format(date);
    }


    // Full time
    public String getFullTimeToString(){
        return new SimpleDateFormat(FullTimeFormat).format(Date);
    }
    public String getFullTimeToString(Date date){
        return new SimpleDateFormat(FullTimeFormat).format(date);
    }


    // Time
    public String getTimeToString(){
        return new SimpleDateFormat(TimeFormat).format(Date);
    }
    public String getTimeToString(Date date){
        return new SimpleDateFormat(TimeFormat).format(date);
    }

    public static void saveObjectToSharedPreferences (ContextWrapper context, String key, Object object) {
        Log.d(TAG, "saveObjectToSharedPreferences() method called.");
        SharedPreferences.Editor editor = context.getSharedPreferences(SAFEDRIVE_PREFERENCES, Context.MODE_PRIVATE).edit();

        Class<?> objectClass = object.getClass();
        if (Integer.class.equals(objectClass)) {
            Log.d(TAG, "saveObjectToSharedPreferences() : The object is an Integer");
            editor.putInt(key, Integer.parseInt(object.toString()));
        }
        else if (Boolean.class.equals(objectClass)) {
            Log.d(TAG, "saveObjectToSharedPreferences() : The object is a Boolean");
            editor.putBoolean(key, Boolean.parseBoolean(object.toString()));
        }
        else if(Float.class.equals(objectClass) || Double.class.equals(objectClass)){
            Log.d(TAG, "saveObjectToSharedPreferences() : The object is a Float");
            editor.putFloat(key, Float.parseFloat(object.toString()));
        }
        else if(String.class.equals(objectClass)){
            Log.d(TAG, "saveObjectToSharedPreferences() : The object is a String");
            editor.putString(key, object.toString());
        }
        else {
            Log.d(TAG, "saveObjectToSharedPreferences() : The object is a " + objectClass.toString());
            String objectToJson = new Gson().toJson(object);
            editor.putString(key, objectToJson);
        }
        editor.apply();
    }
    public static void deleteKeyValueFromSharedPreferences (ContextWrapper context, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SAFEDRIVE_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.apply();
    }
    public static Object getObjectFromSharedPreferences (ContextWrapper context, String key, Class objectClass) {
        SharedPreferences prefs = context.getSharedPreferences(SAFEDRIVE_PREFERENCES, Context.MODE_PRIVATE);

        if (Integer.class.equals(objectClass)) {
            return prefs.getInt(key, Integer.MIN_VALUE);
        }
        else if (Boolean.class.equals(objectClass)) {
            return prefs.getBoolean(key, Boolean.FALSE);
        }
        else if(Float.class.equals(objectClass) || Double.class.equals(objectClass)){
            return prefs.getFloat(key, Float.MIN_VALUE);
        }
        else if(String.class.equals(objectClass)){
            return prefs.getString(key, null);
        }
        else {
            String targetObject = prefs.getString(key, null);
            if(targetObject != null) {
                return new Gson().fromJson(targetObject, objectClass);
            }
        }

        return null;
    }

}
