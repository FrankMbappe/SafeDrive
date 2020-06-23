package com.gm.safedrive.banks;

import com.gm.safedrive.R;
import com.gm.safedrive.banks.interfaces.IBank;
import com.gm.safedrive.models.OperationType;
import com.gm.safedrive.models.VehicleType;

import java.util.ArrayList;

public class OperationTypeBank implements IBank<OperationType> {
    private ArrayList<OperationType> mTypes;

    public static final String PLEIN = "PLEIN";
    public static final String CARBURATION_PARTIELLE = "CARBURATION_PARTIELLE";

    public OperationTypeBank() {
       mTypes = new ArrayList<>();
       mTypes.add(new OperationType(PLEIN, R.drawable.ic_icons8_gas_station, R.color.colorOptimalBlue, R.string.str_full_refueling));
       mTypes.add(new OperationType(CARBURATION_PARTIELLE, R.drawable.ic_icons8_gas_station, R.color.colorPrimary, R.string.str_partial_refueling));
    }

    @Override
    public ArrayList<OperationType> getAll() {
        return mTypes;
    }
}
