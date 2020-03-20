package com.gm.safedrive.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.gm.safedrive.R;

public class VehiclesActivity extends AppCompatActivity {
    Button mBtnNext;
    ImageButton mControlAddVehicle;
    ImageButton mControlRefreshList;
    ImageButton mBtnSetListView;
    ImageButton mBtnSetCarouselView;
    RecyclerView mUsersVehiclesRecyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);

        mBtnNext = findViewById(R.id.activity_vehicles_next_btn);
        mControlAddVehicle = findViewById(R.id.activity_vehicles_control_add);
        mControlRefreshList = findViewById(R.id.activity_vehicles_control_refresh);
        mBtnSetListView = findViewById(R.id.activity_vehicles_control_view_list);
        mBtnSetCarouselView = findViewById(R.id.activity_vehicles_control_view_carousel);
        mUsersVehiclesRecyclerview = findViewById(R.id.activity_vehicles_recyclerview);


    }

}
