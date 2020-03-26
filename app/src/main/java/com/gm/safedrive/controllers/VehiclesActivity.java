package com.gm.safedrive.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.ModelBank;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.controllers.adapters.VehiclesRVAdapter;
import com.gm.safedrive.models.Vehicle;

import java.util.ArrayList;

public class VehiclesActivity extends AppCompatActivity {
    public static final String TAG = "VehiclesActivity";
    private ArrayList<Vehicle> mVehicles = new ArrayList<>();

    private Button mBtnNext;
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

        mBtnNext = findViewById(R.id.activity_vehicles_next_btn);
        mControlAddVehicle = findViewById(R.id.activity_vehicles_control_add);
        mControlRefreshList = findViewById(R.id.activity_vehicles_control_refresh);
        mBtnSetListView = findViewById(R.id.activity_vehicles_control_view_list);
        mBtnSetCarouselView = findViewById(R.id.activity_vehicles_control_view_carousel);

        mBtnNext.setEnabled(false); mBtnNext.setVisibility(View.INVISIBLE);

        mControlAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VehiclesActivity.this, BrandsActivity.class));
            }
        });

        initVehiclesList();
    }

    public void initVehiclesList(){
        ModelBank modelBank = new ModelBank();
        mVehicles.add(
                new Vehicle(
                        UserBank.SESSION,
                        "21/03/2020",
                        "LT950BX",
                        modelBank.getModel("M3 U28"),
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
                        modelBank.getModel("c-class"),
                        8.4,
                        100,
                        null
                )
        );

        initRecyclerView();
    }

    public void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started.");

        mUsersVehiclesRecyclerview = findViewById(R.id.activity_vehicles_recyclerview);
        VehiclesRVAdapter adapter = new VehiclesRVAdapter(this, mVehicles, mBtnNext);
        mUsersVehiclesRecyclerview.setAdapter(adapter);
        mUsersVehiclesRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }
}
