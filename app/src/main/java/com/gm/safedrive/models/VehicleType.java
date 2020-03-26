package com.gm.safedrive.models;

public class VehicleType {
    private static int SEQUENCE_VAL = 1;

    private int id;
    private int nameId;
    private int logoId;

    public VehicleType(int nameId, int logoId) {
        this.id = SEQUENCE_VAL++;
        this.nameId = nameId;
        this.logoId = logoId;
    }

    public int getId(){
        return id;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public int getLogoId() {
        return logoId;
    }

    public void setLogoId(int logoId) {
        this.logoId = logoId;
    }
}
