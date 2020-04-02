package com.gm.safedrive.banks;

import com.gm.safedrive.banks.dictionnaries.ModelDictionnary;
import com.gm.safedrive.banks.interfaces.IBank;
import com.gm.safedrive.models.Brand;
import com.gm.safedrive.models.Vehicle;
import com.gm.safedrive.models.VehicleModel;

import java.util.ArrayList;

public class ModelBank implements IBank<VehicleModel> {
    private ArrayList<VehicleModel> mModels;

    public ModelBank() {
        mModels = new ArrayList<>();
        BrandBank brandBank = new BrandBank();
        VehicleTypeBank vehicleTypeBank = new VehicleTypeBank();

        mModels.add(
                new VehicleModel(
                        ModelDictionnary.CODE_BMWM3U282003,
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
                        ModelDictionnary.CODE_MBCCLASS300,
                        brandBank.getBrand("Mercedes"),
                        "C-CLASS 300",
                        "This vehicle were saved for test.",
                        2007,
                        6.5,
                        vehicleTypeBank.getAll().get(0),
                        null
                )
        );

        mModels.add(
                new VehicleModel(
                        ModelDictionnary.CODE_TOYOTACARINAE,
                        brandBank.getBrand("Toyota"),
                        "CARINA E",
                        "This vehicle were saved for test.",
                        2004,
                        7.5,
                        vehicleTypeBank.getAll().get(0),
                        null
                )
        );
    }

    @Override
    public ArrayList<VehicleModel> getAll() {
        return mModels;
    }

    public VehicleModel getModelByName(String name){
        for (VehicleModel model : mModels) {
            if(model.getName().toLowerCase().contains(name.toLowerCase())){
                return model;
            }
        }
        return null;
    }

    public VehicleModel getModelByCode(String code){
        for (VehicleModel model : mModels) {
            if(model.getCode().equals(code)){
                return model;
            }
        }
        return null;
    }

    public ArrayList<VehicleModel> getModelsByBrand(Brand brand){
        ArrayList<VehicleModel> models = new ArrayList<>();
        for (VehicleModel currentModel : mModels) {
            if(currentModel.getBrand().getName().toLowerCase().equals(brand.getName().toLowerCase())){
                models.add(currentModel);
            }
        }
        return models;
    }

    public ArrayList<VehicleModel> getModels(Brand brand, String search){
        ArrayList<VehicleModel> models = new ArrayList<>();
        for (VehicleModel currentModel : mModels) {
            if(currentModel.getBrand().getName().toLowerCase().equals(brand.getName().toLowerCase())
                && currentModel.getName().toLowerCase().contains(search.toLowerCase())){

                models.add(currentModel);
            }
        }
        return models;
    }

}
