package com.gm.safedrive.banks;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.interfaces.IBank;
import com.gm.safedrive.models.VehicleModel;
import com.gm.safedrive.models.VehicleType;

import java.util.ArrayList;

public class ModelBank implements IBank<VehicleModel> {
    private ArrayList<VehicleModel> mModels;

    public ModelBank() {
        mModels = new ArrayList<>();
        BrandBank brandBank = new BrandBank();
        VehicleTypeBank vehicleTypeBank = new VehicleTypeBank();

        mModels.add(
                new VehicleModel(
                        "BMWM3U282003",
                        brandBank.getBrand("BMW"),
                        "M3 U28",
                        "This vehicle were saved for test.",
                        2003,
                        5.8,
                        vehicleTypeBank.getAll().get(0),
                        null
                )
        );

        mModels.add(
                new VehicleModel(
                        "MBCCLASS300",
                        brandBank.getBrand("Mercedes"),
                        "C-CLASS 300",
                        "This vehicle were saved for test.",
                        2007,
                        6.5,
                        vehicleTypeBank.getAll().get(0),
                        null
                )
        );
    }

    @Override
    public ArrayList<VehicleModel> getAll() {
        return mModels;
    }

    public VehicleModel getModel(String name){
        for (VehicleModel brand : mModels) {
            if(brand.getName().toLowerCase().contains(name.toLowerCase())){
                return brand;
            }
        }
        return null;
    }
}
