package com.gm.safedrive.controllers;

import android.os.Bundle;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.controllers.interfaces.MainHeaderActivityUser;

public class BrandModelsActivity extends MainHeaderActivityUser {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_models);
    }

    @Override
    public String getActivityName() {
        return getString(R.string.str_model);
    }
}
