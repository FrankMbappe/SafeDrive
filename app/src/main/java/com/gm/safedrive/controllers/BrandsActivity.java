package com.gm.safedrive.controllers;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.BrandBank;
import com.gm.safedrive.controllers.adapters.BrandsRVAdapter;
import com.gm.safedrive.controllers.interfaces.MainHeaderActivityUser;
import com.gm.safedrive.models.Brand;

import java.util.ArrayList;

public class BrandsActivity extends MainHeaderActivityUser {
    public static final String TAG = "BrandsActivity";

    private RecyclerView mBrandsRecyclerview;
    private LinearLayout mLinearLayout;
    private ImageButton mSkipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);

        mLinearLayout = findViewById(R.id.activity_brands_global_name_block);
        mSkipButton = findViewById(R.id.activity_brands_skip_btn);

        final Context thisContext = this;
        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(thisContext, FinalCVehicleActivity.class));
            }
        });

        BrandBank brandBank = new BrandBank();
        Log.d(TAG, "Brand bank has " + brandBank.getAll().size() + " item(s).");
        initRecyclerView(brandBank.getAll());
    }

    @Override
    public String getActivityName() {
        return getString(R.string.str_brand);
    }

    public void initRecyclerView(ArrayList<Brand> brands){
        Log.d(TAG, "initRecyclerView: started.");

        mBrandsRecyclerview = findViewById(R.id.activity_brands_recyclerview);
        BrandsRVAdapter adapter = new BrandsRVAdapter(this, brands);
        mBrandsRecyclerview.setAdapter(adapter);
        mBrandsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setSearchedValue(String searchedValue) {
        // J'update le recyclerview en lui passant la liste des marques qui dans leur nom contiennent la valeur de searchedValue
        BrandsRVAdapter adapter = new BrandsRVAdapter(this, new BrandBank().getBrands(searchedValue));
        mBrandsRecyclerview.setAdapter(adapter);
        mBrandsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    /* Ex: Je fais disparaître un élément dès que le focus d'une vue passe à true */
    @Override
    public void hideViewsOnBaseFocus(boolean isFocused) {
        if(isFocused){
            mLinearLayout.setVisibility(View.GONE);
        }
        else {
            mLinearLayout.setVisibility(View.VISIBLE);
        }
    }
}
