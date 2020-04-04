package com.gm.safedrive.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.ModelBank;
import com.gm.safedrive.banks.UserBank;
import com.gm.safedrive.banks.VehicleTypeBank;
import com.gm.safedrive.controllers.adapters.ModelsRVAdapter;
import com.gm.safedrive.controllers.interfaces.MainHeaderActivityUser;
import com.gm.safedrive.models.Brand;
import com.gm.safedrive.models.VehicleModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class BrandModelsActivity extends MainHeaderActivityUser {
    public static final String TAG = "BrandModelsActivity";
    public static final String EXTRA_CURRENT_BRAND = "EXTRA_CURRENT_BRAND";

    private ImageView mCurrentBrandLogo;
    private TextView mCurrentBrandName;
    private RecyclerView mModelsRecyclerView;
    private Button mCreateNewModelBtn;
    private Brand mCurrentBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_models);

        Gson gson = new GsonBuilder().create();
        String extra = getIntent().getStringExtra(EXTRA_CURRENT_BRAND);
        if(extra != null){
            mCurrentBrand = gson.fromJson(extra, Brand.class);
            Log.d(TAG, "Found " + new ModelBank().getModelsByBrand(mCurrentBrand).size() + " model(s).");
            initRecyclerView(new ModelBank().getModelsByBrand(mCurrentBrand));
        }
        mCurrentBrandLogo = findViewById(R.id.activity_brand_models_brand_logo);
        mCurrentBrandName = findViewById(R.id.activity_brand_models_brand_name);
        mCreateNewModelBtn = findViewById(R.id.activity_brand_models_new_model_btn);

        mCurrentBrandLogo.setImageResource(mCurrentBrand.getLogoId());
        mCurrentBrandName.setText(mCurrentBrand.getName());

        final Context thisContext = this;
        mCreateNewModelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisContext, FinalCVehicleActivity.class);

                VehicleModel defaultModel = new VehicleModel("", mCurrentBrand, "", "", 2020, 0, new VehicleTypeBank().getAll().get(0), null);

                // Je convertis l'objet marque choisi en String grâce à Gson et je l'envoie à l'autre activité avec un putExtra
                Gson gson = new GsonBuilder().create();
                intent.putExtra(FinalCVehicleActivity.EXTRA_CURRENT_MODEL, gson.toJson(defaultModel));
                thisContext.startActivity(intent);
            }
        });
    }

    @Override
    public String getActivityName() {
        return getString(R.string.str_model);
    }

    public void initRecyclerView(ArrayList<VehicleModel> models){
        Log.d(TAG, "initRecyclerView: started.");

        mModelsRecyclerView = findViewById(R.id.activity_brand_models_recyclerview);
        ModelsRVAdapter adapter = new ModelsRVAdapter(this, models);
        mModelsRecyclerView.setAdapter(adapter);
        mModelsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setSearchedValue(String searchedValue) {
        // J'update le recyclerview en lui passant la liste des marques qui dans leur nom contiennent la valeur de searchedValue
        ModelsRVAdapter adapter = new ModelsRVAdapter(this, new ModelBank().getModels(mCurrentBrand, searchedValue));
        mModelsRecyclerView.setAdapter(adapter);
        mModelsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
