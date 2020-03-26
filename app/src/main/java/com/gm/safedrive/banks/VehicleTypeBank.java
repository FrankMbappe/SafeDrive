package com.gm.safedrive.banks;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.interfaces.IBank;
import com.gm.safedrive.models.VehicleType;

import java.util.ArrayList;

public class VehicleTypeBank implements IBank<VehicleType> {
    private ArrayList<VehicleType> mTypes;

    public VehicleTypeBank() {
       mTypes = new ArrayList<>();
       mTypes.add(new VehicleType(R.string.str_cars, R.drawable.ic_car_primary_24dp));
    }

    @Override
    public ArrayList<VehicleType> getAll() {
        return mTypes;
    }
}
