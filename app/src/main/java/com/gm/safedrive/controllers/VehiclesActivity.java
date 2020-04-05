package com.gm.safedrive.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.ModelBank;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.controllers.adapters.VehiclesRVAdapter;
import com.gm.safedrive.data.DbManager;
import com.gm.safedrive.models.Vehicle;

import java.util.ArrayList;
import java.util.Date;

public class VehiclesActivity extends AppCompatActivity {
    public static final String TAG = "VehiclesActivity";
    private ArrayList<Vehicle> mVehicles = new ArrayList<>();
    private DbManager db;

    private ImageButton mControlAddVehicle;
    private ImageButton mControlRefreshList;
    private ImageButton mBtnSetListView;
    private ImageButton mBtnSetCarouselView;
    private RecyclerView mUsersVehiclesRecyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);
        Log.d(TAG, "onCreate: started.");

        mControlAddVehicle = findViewById(R.id.activity_vehicles_control_add);
        mControlRefreshList = findViewById(R.id.activity_vehicles_control_refresh);
        mBtnSetListView = findViewById(R.id.activity_vehicles_control_view_list);
        mBtnSetCarouselView = findViewById(R.id.activity_vehicles_control_view_carousel);

        mControlAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VehiclesActivity.this, BrandsActivity.class));
            }
        });

        //initVehiclesList();
        db = new DbManager(this);
        // J'initialise la BD, après les tables créees ci-dessus, j'inscris les données de base
        //db.insertUser(UserBank.SESSION);
        UserBank.SESSION = db.getUserById(1);
//        db.insertVehicle(
//                new Vehicle(
//                        UserBank.SESSION,
//                        new Date().toString(),
//                        "LT950BX",
//                        new ModelBank().getModelByName("M3 U28"),
//                        8.4,
//                        100,
//                        null
//                )
//        );
//        db.insertVehicle(
//                new Vehicle(
//                        UserBank.SESSION,
//                        new Date().toString(),
//                        "LT600AF",
//                        new ModelBank().getModelByName("c-class"),
//                        8.4,
//                        100,
//                        null
//                )
//        );

        initRecyclerView(db.getSessionUserVehicles());
        db.close();
    }

    public void initVehiclesList(){
        ModelBank modelBank = new ModelBank();
        mVehicles.add(
                new Vehicle(
                        UserBank.SESSION,
                        "21/03/2020",
                        "LT950BX",
                        modelBank.getModelByName("M3 U28"),
                        8.4,
                        100,
                        null
                )
        );
        mVehicles.add(
                new Vehicle(
                        UserBank.SESSION,
                        "21/03/2020",
                        "LT600AF",
                        modelBank.getModelByName("c-class"),
                        8.4,
                        100,
                        null
                )
        );

        //initRecyclerView();
    }

    public void initRecyclerView(ArrayList<Vehicle> mVehicles){
        Log.d(TAG, "initRecyclerView: started.");

        mUsersVehiclesRecyclerview = findViewById(R.id.activity_vehicles_recyclerview);
        VehiclesRVAdapter adapter = new VehiclesRVAdapter(this, mVehicles);
        mUsersVehiclesRecyclerview.setAdapter(adapter);
        mUsersVehiclesRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            this.finishAffinity();
            this.finishAndRemoveTask();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click once again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
