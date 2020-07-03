package com.gm.safedrive.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gm.safedrive.banks.BrandBank;
import com.gm.safedrive.banks.ModelBank;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.banks.VehicleTypeBank;
import com.gm.safedrive.models.Brand;
import com.gm.safedrive.models.User;
import com.gm.safedrive.models.Vehicle;
import com.gm.safedrive.models.VehicleModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DbManager extends SQLiteOpenHelper {

    public DbManager(Context context){
        super(context, Db.DATABASE_NAME, null, Db.DATABASE_VERSION);
    }


    // La date et l'heure de maintenant sous forme de chaîne

    //private String now = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    private String now = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            // #SQLITE
//            db.execSQL(UserFireDbHelper.QUERY_CREATE_TABLE_LOGGED_ACCOUNTS);
//            db.execSQL(UserFireDbHelper.QUERY_CREATE_TABLE_VEHICLES);
        }catch (SQLException sqlex){
            try {
                throw new IOException(sqlex);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        Log.i("DATABASE", "onCreate invoked");

    }

    @Override //Méthode appelée lorsque la version de la BD évolue
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists logged_accounts");
        db.execSQL("DROP TABLE if exists vehicles");
        this.onCreate(db);
        Log.i("DATABASE", "onUpgrade invoked");
    }



    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //------------------    /*          PROCEDURES STOCKEES             */      --------------------
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------



    // Insère un nouveau véhicule dans la BD

    /* #SQLITE public void insertVehicle(Vehicle vehicle){
        this.getWritableDatabase().execSQL(
            UserFireDbHelper.QUERY_INSERT_VEHICLE(
                    vehicle.getOwner().getId(),
                    vehicle.getModel().getCode(),
                    vehicle.getModel().getBrand().getName(),
                    vehicle.getModel().getName(),
                    now,
                    vehicle.getRegistrationNumber(),
                    vehicle.getModel().getDescription(),
                    vehicle.getTankCapacity(),
                    vehicle.getDistanceCovered()
            )
        );
        Log.i("DATABASE",now + ": new Vehicle added. (insertVehicle was successful)");
    }*/



    // Supprime un véhicule dans la BD

    /* #SQLITE public void deleteVehicle(Vehicle vehicle){
        this.getWritableDatabase().execSQL(
                UserFireDbHelper.QUERY_DELETE_VEHICLE(
                        vehicle.getId(),
                        vehicle.getOwner().getId()
                )
        );
        Log.i("DATABASE",now + ": A vehicle was deleted '" + vehicle.getRegistrationNumber() + "'. (deleteVehicle was successful)");
    }*/



    // Récupère la liste des véhicules d'un utilisateur

    /* #SQLITE public ArrayList<Vehicle> getSessionUserVehicles(){
        ModelBank modelBank = new ModelBank();

        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery(UserFireDbHelper.QUERY_GET_USER_VEHICLES(UserBank.SESSION.getId()), null);
        cursor.moveToFirst();

        String modelCode, modelName, brandName, registration, createdDate;
        double tankCapacity, distanceCovered;
        VehicleModel vehicleModel;
        while (!cursor.isAfterLast()){
            modelCode = cursor.getString(cursor.getColumnIndexOrThrow("model_code"));
            modelName = cursor.getString(cursor.getColumnIndexOrThrow("model_name"));
            brandName = cursor.getString(cursor.getColumnIndexOrThrow("brand_name"));
            registration = cursor.getString(cursor.getColumnIndexOrThrow("registration_number"));
            createdDate = cursor.getString(cursor.getColumnIndexOrThrow("created_date"));
            tankCapacity = cursor.getDouble(cursor.getColumnIndexOrThrow("tank_capacity"));
            distanceCovered = cursor.getDouble(cursor.getColumnIndexOrThrow("distance_covered"));

            vehicleModel = modelBank.getModelByCode(modelCode);

            // A modifier lorsque le traitement des statistiques va commencer
            // Concrètement, s'il n'y a pas ce modèle déjè pré-enregistré, j'en crée un nouveau que j'affecte
            if(vehicleModel == null){
                vehicleModel = new VehicleModel(
                        modelCode,
                        (new BrandBank().getBrand(brandName) != null) ? new BrandBank().getBrand(brandName) : new Brand(brandName, 0),
                        modelName,
                        "",
                        new Date().getYear(),
                        tankCapacity,
                        new VehicleTypeBank().getAll().get(0),
                        null
                );
            }

            vehicleList.add(
                    new Vehicle(
                            cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                            UserBank.SESSION,
                            createdDate,
                            registration,
                            vehicleModel,
                            tankCapacity,
                            distanceCovered,
                            null
                    )
            );
            cursor.moveToNext();
        }
        cursor.close();
        Log.i("DATABASE",now + ": " + vehicleList.size() + " item(s) sent. (getSessionUserVehicles was successful)");
        return vehicleList;
    }*/




    // Insère un nouvel utilisateur dans la BD

    public void insertUser(User user){
        this.getWritableDatabase().execSQL(
            Db.QUERY_INSERT_USER(
                    now,
                    user.getEmail(),
                    user.getPassword(),
                    user.getProfilePhotoId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPhoneNumber(),
                    now
            )
        );
        Log.i("DATABASE",now + ": new User added. (insertUser was successful)");
    }



    // Retourne un objet utilisateur à partir d'un id

    public User getUserById(int user_id){
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery(Db.QUERY_GET_USER(user_id), null);
        cursor.moveToFirst();
//        if(!cursor.isAfterLast()){
//            User user = new User(
//                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("created_date")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("password")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("first_name")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("last_name")),
//                    cursor.getInt(cursor.getColumnIndexOrThrow("phone_number")),
//                    new ArrayList<Vehicle>(),
//                    cursor.getInt(cursor.getColumnIndexOrThrow("profile_photo_id")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("last_time_connected")),
//                    null
//            );
//            Log.i("DATABASE",now + ": user " + user_id + "'s was been found [" + user.getFullName().toUpperCase() + "]. (getUserById was successful)");
//            cursor.close();
//            return user;
//        }
        return null;
    }


    // Met à jour la dernière date de connexion d'un utilisateur

    public void userUpdateLastLogin(int id){
        this.getWritableDatabase().execSQL(Db.QUERY_UPDATE_USER_LAST_LOGIN(id, now));
        Log.i("DATABASE",now + ": user " + id + " was updated. (userUpdateLastLogin was successful)");
    }
}
