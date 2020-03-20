package com.gm.safedrive.models;

public class VehicleType {
    private static int SEQUENCE_VAL = 1;

    private int id;
    private String name;
    private int logoId;

    public VehicleType(int id, String name, int logoId) {
        this.id = SEQUENCE_VAL++;
        this.name = name;
        this.logoId = logoId;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLogoId() {
        return logoId;
    }

    public void setLogoId(int logoId) {
        this.logoId = logoId;
    }
}
