package com.gm.safedrive.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gm.safedrive.R;
import com.gm.safedrive.controllers.interfaces.MainHeaderActivityUser;

public class BrandsActivity extends MainHeaderActivityUser {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);
    }

    @Override
    public String getActivityName() {
        return getString(R.string.str_brands);
    }

}
