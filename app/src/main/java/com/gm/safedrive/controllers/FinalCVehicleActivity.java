package com.gm.safedrive.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.gm.safedrive.models.Brand;
import com.gm.safedrive.models.Vehicle;
import com.gm.safedrive.models.VehicleModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FinalCVehicleActivity extends AppCompatActivity {
    public static final String TAG = "FinalCVehicleActivity";
    public static final String EXTRA_CURRENT_MODEL = "EXTRA_CURRENT_MODEL";
    private VehicleModel mCurrentModel;

    ImageView mBrandLogo;
    ImageView mBrandModelLogo;
    EditText mRegistrationInput;
    EditText mBrandNameInput;
    EditText mModelNameInput;
    EditText mTankCapacityInput;
    EditText mDistanceCoveredInput;
    Button mValidateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_c_vehicle);

        Gson gson = new GsonBuilder().create();
        String extra = getIntent().getStringExtra(EXTRA_CURRENT_MODEL);
        if(extra != null){
            mCurrentModel = gson.fromJson(extra, VehicleModel.class);
        }

        mBrandLogo = findViewById(R.id.activity_final_c_vehicle_brand_logo);
        mBrandModelLogo = findViewById(R.id.activity_final_c_vehicle_brand_model_logo);
        mRegistrationInput = findViewById(R.id.activity_final_c_vehicle_registration_input);
        mBrandNameInput = findViewById(R.id.activity_final_c_vehicle_brand_input);
        mModelNameInput = findViewById(R.id.activity_final_c_vehicle_model_input);
        mTankCapacityInput = findViewById(R.id.activity_final_c_vehicle_tank_input);
        mDistanceCoveredInput = findViewById(R.id.activity_final_c_vehicle_distance_covered_input);
        mValidateBtn = findViewById(R.id.activity_final_c_vehicle_save_btn);

        mBrandLogo.setImageResource(mCurrentModel.getBrand().getLogoId());
        mBrandNameInput.setText(mCurrentModel.getBrand().getName());
        mModelNameInput.setText(mCurrentModel.getName());
        mTankCapacityInput.setText(mCurrentModel.getTankCapacity() + "");
        mDistanceCoveredInput.setText("0.00");

        mBrandNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBrandLogo.setImageResource(getCurrentLogoId(s.toString()));
                mBrandModelLogo.setImageResource(getCurrentLogoId(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mValidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Vehicle generated : " + getCurrentVehicle().getModel().getName());
            }
        });

    }

    private int getCurrentLogoId(String brandName){
        return new BrandBank().getBrands(brandName).get(0)
                    .getLogoId();
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

    public VehicleModel getCurrentModel(){
        boolean allSetToDefault = mBrandNameInput.getText().toString().toLowerCase().equals(mCurrentModel.getBrand().getName().toLowerCase()) &&
                mModelNameInput.getText().toString().toLowerCase().equals(mCurrentModel.getName().toLowerCase());

        if(allSetToDefault){
            return mCurrentModel;
        }
        else{
            return new VehicleModel(
                    mModelNameInput.getText().toString().replaceAll(" ", ""),
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
}
