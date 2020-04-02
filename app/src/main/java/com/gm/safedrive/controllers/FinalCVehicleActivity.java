package com.gm.safedrive.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.BrandBank;
import com.gm.safedrive.banks.ModelBank;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.banks.VehicleTypeBank;
import com.gm.safedrive.controllers.fragments.dialogs.VehicleValidationDialog;
import com.gm.safedrive.data.DbManager;
import com.gm.safedrive.models.Brand;
import com.gm.safedrive.models.Vehicle;
import com.gm.safedrive.models.VehicleModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.gm.safedrive.application.SafeDrive.MAIN_CHANNEL_ID;

public class FinalCVehicleActivity extends AppCompatActivity {
    public static final String TAG = "FinalCVehicleActivity";
    public static final String EXTRA_CURRENT_MODEL = "EXTRA_CURRENT_MODEL";
    private NotificationManagerCompat mNotificationManager;
    private DbManager mDbManager;
    private VehicleModel mCurrentModel;

    private ImageView mBrandLogo;
    private ImageView mBrandModelLogo;
    private ImageView mLgCheck;
    private EditText mRegistrationInput;
    private EditText mBrandNameInput;
    private EditText mModelNameInput;
    private EditText mTankCapacityInput;
    private EditText mDistanceCoveredInput;
    private Button mValidateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_c_vehicle);

        mNotificationManager = NotificationManagerCompat.from(this);
        mDbManager = new DbManager(this);
        mCurrentModel = new ModelBank().getAll().get(0);

        Gson gson = new GsonBuilder().create();
        String extra = getIntent().getStringExtra(EXTRA_CURRENT_MODEL);
        if(extra != null){
            mCurrentModel = gson.fromJson(extra, VehicleModel.class);
        }

        mBrandLogo = findViewById(R.id.activity_final_c_vehicle_brand_logo);
        mBrandModelLogo = findViewById(R.id.activity_final_c_vehicle_brand_model_logo);
        mLgCheck = findViewById(R.id.lgcheck);
        mRegistrationInput = findViewById(R.id.activity_final_c_vehicle_registration_input);
        mBrandNameInput = findViewById(R.id.activity_final_c_vehicle_brand_input);
        mModelNameInput = findViewById(R.id.activity_final_c_vehicle_model_input);
        mTankCapacityInput = findViewById(R.id.activity_final_c_vehicle_tank_input);
        mDistanceCoveredInput = findViewById(R.id.activity_final_c_vehicle_distance_covered_input);
        mValidateBtn = findViewById(R.id.activity_final_c_vehicle_save_btn);

        mBrandLogo.setImageResource(mCurrentModel.getBrand().getLogoId());
        mBrandModelLogo.setImageResource(mCurrentModel.getBrand().getLogoId());
        mBrandNameInput.setText(mCurrentModel.getBrand().getName());
        mModelNameInput.setText(mCurrentModel.getName());
        mTankCapacityInput.setText(mCurrentModel.getTankCapacity() + "");
        mDistanceCoveredInput.setText("0.00");
        mLgCheck.setVisibility(View.INVISIBLE);

        mBrandNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /* En fonction du texte entré, la marque du modèle final se met à jour et affiche le logo correspondant */
                mCurrentModel.setBrand(getCurrentBrand(s.toString()));

                if(mCurrentModel.getBrand() != null){
                    mBrandLogo.setImageResource(mCurrentModel.getBrand().getLogoId());
                    mBrandModelLogo.setImageResource(mCurrentModel.getBrand().getLogoId());

                    // Validation visuelle
                    if(!mCurrentModel.getBrand().getName().equals("")){
                        mLgCheck.setVisibility(View.VISIBLE);
                        mLgCheck.setImageResource(R.drawable.ic_check_circle_primary_36dp);
                    }
                    else{
                        mLgCheck.setVisibility(View.VISIBLE);
                        mLgCheck.setImageResource(R.drawable.ic_error_orange_36dp);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        final Context thisContext = this;
        mValidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCurrentVehicle().getRegistrationNumber().isEmpty() || getCurrentVehicle().getTankCapacity() <= 0){
                    Toast.makeText(thisContext, R.string.str_error_values_finalc, Toast.LENGTH_LONG).show();
                }
                else {
                    mDbManager.insertVehicle(getCurrentVehicle());
                    openVehicleValidationDialog();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(FinalCVehicleActivity.this, VehiclesActivity.class));
                            Log.i(TAG, "Vehicle generated : " + getCurrentVehicle().getModel().getBrand().getName() + " " + getCurrentVehicle().getModel().getName());
                        }
                    }, 4000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sendValidationOnMainChannel(
                                    getResources().getString(R.string.str_notification_well_done),
                                    getResources().getString(R.string.str_notification_save_vehicle_description) +
                                            "| MSV : " + getCurrentVehicle().getModel().getFullName()
                            );
                        }
                    }, 5000);
                }
            }
        });

    }







    private Brand getCurrentBrand(String brandName){
        ArrayList<Brand> brands = new BrandBank().getBrands(brandName);
        if(brands.size() > 0){
            return brands.get(0);
        }
        return new Brand("", 0);
    }

    // Un véhicule à partir des paramètres actuels
    public Vehicle getCurrentVehicle(){
        return new Vehicle(
                UserBank.SESSION,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()),
                mRegistrationInput.getText().toString(),
                getCurrentModel(),
                Double.parseDouble(mTankCapacityInput.getText().toString()),
                Double.parseDouble(mDistanceCoveredInput.getText().toString()),
                null
        );
    }


    /* Méthode qui retourne un objet VehicleModel à l'aide des paramètres actuels et qui retourne l'objet originel(currentModel) si les
    * paramètres n'ont pas été modifiés (Nom de la condition : nameIsSetToDefault)*/

    public VehicleModel getCurrentModel(){
        // Cette ligne n'a aucun effet si l'utilisateur n'a pas modifié le nom de la marque TODO: Entrer le nom de la marque par voie vocale
        mCurrentModel.setBrand(getCurrentBrand(mBrandNameInput.getText().toString()));

        if(mCurrentModel.getBrand() != null){
            boolean nameIsSetToDefault = mModelNameInput.getText().toString().toLowerCase().equals(mCurrentModel.getName().toLowerCase());

            if(nameIsSetToDefault){
                return mCurrentModel;
            }
            else{
                return new VehicleModel(
                        getModelCode(),
                        mCurrentModel.getBrand(),
                        mModelNameInput.getText().toString(),
                        "",
                        new Date().getYear(),
                        Double.parseDouble(mTankCapacityInput.getText().toString()),
                        new VehicleTypeBank().getAll().get(0),
                        null
                );
            }
        }
        else {
            return new VehicleModel(
                    getModelCode(),
                    new Brand(mBrandNameInput.getText().toString(), 0),
                    mModelNameInput.getText().toString(),
                    "",
                    new Date().getYear(),
                    Double.parseDouble(mTankCapacityInput.getText().toString()),
                    new VehicleTypeBank().getAll().get(0),
                    null
            );
        }

    }

    /* Méthode classique qui retourne un code par défaut pour un modèle de véhicule à partir du nom du modèle
    * et celui de la marque */
    private String getModelCode(){
        return (mBrandNameInput.getText().toString() + mModelNameInput.getText().toString()).replaceAll(" ", "");
    }


    /* Pour émettre la notification de validation sur la chaîne principale */
    private void sendValidationOnMainChannel(String title, String description){
        Notification notification = new NotificationCompat.Builder(this, MAIN_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_check_circle_primary_36dp)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        mNotificationManager.notify(1, notification);
    }

    /* Pour afficher une boîte de dialogue */
    private void openVehicleValidationDialog(){
        new VehicleValidationDialog().show(getSupportFragmentManager(), "Vehicle validation dialog");
    }
}
