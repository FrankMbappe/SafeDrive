package com.gm.safedrive.application;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.location.Location;

import com.gm.safedrive.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;

public class Current {
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

    public static void saveToSharedPreferences ( Object object, String filePreference, String preferenceFileKey, ContextWrapper contextWrapper ) {
        String contentPreference = new Gson().toJson(object);
        SharedPreferences.Editor editor = contextWrapper.getSharedPreferences(preferenceFileKey , Context.MODE_PRIVATE).edit();
        editor.putString(filePreference , contentPreference);
        editor.apply();
    }


    public static void clearFromSharedPreferences ( Object object, String filePreference, String preferenceFileKey, ContextWrapper contextWrapper ) {
        String contentPreference = new Gson().toJson(object);
        SharedPreferences.Editor editor = contextWrapper.getSharedPreferences(preferenceFileKey , Context.MODE_PRIVATE).edit();
        editor.putString(filePreference , contentPreference);
        editor.apply();
        editor.clear();
    }

    public static Object getFromSharedPreferences ( Object object, String filePreference, String preferenceFileKey, ContextWrapper contextWrapper ) {
        SharedPreferences sharedPref = contextWrapper.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        String member_string = sharedPref.getString(filePreference, null);
        if(member_string != null) {
            return new Gson().fromJson(member_string, object.getClass());
        }
        return null;
    }

}
