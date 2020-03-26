package com.gm.safedrive.banks;

import com.gm.safedrive.banks.interfaces.IBank;
import com.gm.safedrive.models.Vehicle;

import java.util.ArrayList;

public class VehicleBank implements IBank<Vehicle> {
    @Override
    public ArrayList<Vehicle> getAll() {
        return null;
    }
}
