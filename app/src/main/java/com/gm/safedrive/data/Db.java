package com.gm.safedrive.data;

/* Cette classe n'en est pas une vraie classe en principe mais juste un repositoire de valeurs non seulement constantes, mais
 * aussi statique. Ce qui fait que l'instancier ne sert à rien, on récupère juste la valeur des constantes.
 * Il est préférable de mettre toutes les valeurs constantes concernant la base de donnée ici. */

public class Db {

    public static final String DATABASE_NAME = "SafeDriveDB.db";
    public static final int DATABASE_VERSION = 3;

    public static final String TABLE_NAME_USERS = "logged_accounts";
    public static final String TABLE_NAME_VEHICLES = "vehicles";


    /*  Cette méthode prend en paramètre une valeur texte et la conforme à  la base de données (Current: SQLite)   */

    public static String stringConvert(String text){
        return "'"+ text.replace("'","''") + "'";
    }


    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------    /*          REQUETES             */      ---------------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    public static final String QUERY_CREATE_TABLE_LOGGED_ACCOUNTS =
        "CREATE TABLE " + TABLE_NAME_USERS + " (" +
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

        return "INSERT INTO " + TABLE_NAME_USERS + "(" +
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
        return "UPDATE TABLE " + TABLE_NAME_USERS + " " +
                "SET " +
                "last_time_connected = " + stringConvert(date) +
                "WHERE user_id = " + user_id;
    }

    public static String QUERY_GET_USER(int user_id){
        return "SELECT * FROM " + TABLE_NAME_USERS +
                " WHERE id = " + user_id;
    }




    public static final String QUERY_CREATE_TABLE_VEHICLES =
        "CREATE TABLE " + TABLE_NAME_VEHICLES + "(" +
        "       id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "       user_id INTEGER," +
        "       model_code TEXT," +
        "       created_date TEXT," +
        "       registration_number TEXT," +
        "       description TEXT," +
        "       tank_capacity REAL," +
        "       distance_covered REAL," +
        "       FOREIGN KEY(user_id) REFERENCES " + TABLE_NAME_USERS + "(id)" +
        ")";

    public static String QUERY_INSERT_VEHICLE(int user_id, String model_code, String created_date,
                  String registration_number, String description, double tank_capacity, double distance_covered){

        return "INSERT INTO " + TABLE_NAME_VEHICLES + "(" +
                    "user_id," +
                    "model_code," +
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

    public static String QUERY_GET_USER_VEHICLES(int user_id){
        return "SELECT * FROM " + TABLE_NAME_VEHICLES +
                " WHERE user_id = " + user_id;
    }

}
