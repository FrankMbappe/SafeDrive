package com.gm.safedrive.models;

public class VehicleModel {
    //private static int SEQUENCE_VAL = 1;

    private String code;
    private Brand brand;
    private String name;
    private String reducedName;
    private String fullName;
    private  String description;
    private int releaseYear;
    private double tankCapacity;
    private VehicleType vehicleType;
    private SafeDriveStatistics statistics;

    public VehicleModel(){

    }
    public VehicleModel(String code, Brand brand, String name, String description, int releaseYear, double tankCapacity, VehicleType vehicleType, SafeDriveStatistics statistics) {
        this.code = code;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
        this.tankCapacity = tankCapacity;
        this.vehicleType = vehicleType;
        this.statistics = statistics;
    }

    public String getCode() {
        return code;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReducedName(){
        return (name.length() >= 10) ? name.substring(0,9) + "..." : name;
    }

    public String getFullName(){
        return brand.getName() + " " + name + " " + (releaseYear + "").substring(2) + "'";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String text) {
        this.description = text;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public SafeDriveStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(SafeDriveStatistics statistics) {
        this.statistics = statistics;
    }
}
