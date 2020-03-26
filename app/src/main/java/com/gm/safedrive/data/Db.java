package com.gm.safedrive.data;

public class Db {
    public static final String DATABASE_NAME = "SafeDriveDB.db";
    public static final int DATABASE_VERSION = 1;

    public static String stringConvert(String text){
        return "'"+ text.replace("'","''") + "'";
    }




    public static final String QUERY_CREATE_TABLE_LOGGED_ACCOUNTS =
        "CREATE TABLE logged_accounts (" +
        "       id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "       created_date TEXT," +
        "       email TEXT NOT NULL," +
        "       password TEXT NOT NULL," +
        "       profile_photo_id INTEGER,"+
        "       first_name TEXT," +
        "       last_name TEXT," +
        "       phone_number INTEGER," +
        "       last_time_connected TEXT" +
        ")";

    public static String QUERY_INSERT_USER(String created_date, String email, String password, int profile_photo_id,
                   String first_name, String last_name, int phone_number, String last_time_connected ){

        return "INSERT INTO vehicles(" +
                    "created_date," +
                    "email," +
                    "password," +
                    "profile_photo_id," +
                    "first_name," +
                    "last_name," +
                    "phone_number," +
                    "last_time_connected" +
                ")" +
                "VALUES (" +
                    stringConvert(created_date) + "," +
                    stringConvert(email) + "," +
                    stringConvert(password) + "," +
                    profile_photo_id + "," +
                    stringConvert(first_name) + "," +
                    stringConvert(last_name) + "," +
                    phone_number + "," +
                    stringConvert(last_time_connected) +
                ")";
    }

    public static String QUERY_UPDATE_USER_LAST_LOGIN(int user_id, String date){
        return "UPDATE TABLE logged_accounts " +
                "SET " +
                "last_time_connected = " + stringConvert(date) +
                "WHERE user_id = " + user_id;
    }




    public static final String QUERY_CREATE_TABLE_VEHICLES =
        "CREATE TABLE vehicles (" +
        "       id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "       user_id INTEGER," +
        "       model_code TEXT," +
        "       created_date TEXT," +
        "       registration_number TEXT," +
        "       description TEXT," +
        "       tank_capacity REAL," +
        "       distance_covered REAL" +
        "       FOREIGN KEY(user_id) REFERENCES logged_accounts(id)" +
        ")";

    public static String QUERY_INSERT_VEHICLE(int user_id, String model_code, String created_date,
                  String registration_number, String description, double tank_capacity, double distance_covered){

        return "INSERT INTO vehicles(" +
                    "user_id," +
                    "brand_name," +
                    "model_name," +
                    "created_date," +
                    "registration_number," +
                    "description," +
                    "tank_capacity," +
                    "distance_covered" +
                ")" +
                "VALUES (" +
                    user_id + "," +
                    stringConvert(model_code) + "," +
                    stringConvert(created_date) + "," +
                    stringConvert(registration_number) + "," +
                    stringConvert(description) + "," +
                    tank_capacity + "," +
                    distance_covered +
                ")";
    }

}
