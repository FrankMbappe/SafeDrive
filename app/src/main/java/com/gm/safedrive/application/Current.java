package com.gm.safedrive.application;

import android.content.Context;
import android.location.Location;

import com.gm.safedrive.R;

import java.text.SimpleDateFormat;
import java.util.Date;

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


}
