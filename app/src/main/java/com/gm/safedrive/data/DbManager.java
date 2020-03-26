package com.gm.safedrive.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.models.User;
import com.gm.safedrive.models.Vehicle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DbManager extends SQLiteOpenHelper {

    public DbManager(Context context){
        super(context, Db.DATABASE_NAME, null, Db.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Db.QUERY_CREATE_TABLE_LOGGED_ACCOUNTS);
        db.execSQL(Db.QUERY_CREATE_TABLE_VEHICLES);
        Log.i("DATABASE", "onCreate invoked");
        insertUser(UserBank.SESSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists logged_accounts");
        db.execSQL("DROP TABLE if exists vehicles");
        this.onCreate(db);
        Log.i("DATABASE", "onUpgrade invoked");
    }

    public void insertVehicle(Vehicle vehicle){
        String now = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        this.getWritableDatabase().execSQL(
            Db.QUERY_INSERT_VEHICLE(
                    vehicle.getOwner().getId(),
                    vehicle.getModel().getCode(),
                    now,
                    vehicle.getRegistrationNumber(),
                    vehicle.getModel().getDescription(),
                    vehicle.getTankCapacity(),
                    vehicle.getDistanceCovered()
            )
        );
        Log.i("DATABASE",now + ": new Vehicle added. (insertVehicle was successful)");
    }

    public void insertUser(User user){
        String now = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        this.getWritableDatabase().execSQL(
            Db.QUERY_INSERT_USER(
                    now,
                    user.getEmail(),
                    user.getPassword(),
                    user.getProfilePhotoId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPhoneNumber(),
                    "-"
            )
        );
        Log.i("DATABASE",now + ": new User added. (insertUser was successful)");
    }

    public void userUpdateLastLogin(int id){
        String now = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        this.getWritableDatabase().execSQL(Db.QUERY_UPDATE_USER_LAST_LOGIN(id, now));
        Log.i("DATABASE",now + ": user " + id + " was updated. (userUpdateLastLogin was successful)");
    }
}
