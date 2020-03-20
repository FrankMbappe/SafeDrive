package com.gm.safedrive.models;

public class VehicleModel {
    private static int SEQUENCE_VAL = 1;

    private int id;
    private Brand brand;
    private String name;
    private int releaseYear;
    private double tankCapacity;
    private VehicleType vehicleType;
    private SafeDriveStatistics statistics;

    public VehicleModel(Brand brand, String name, int releaseYear, double tankCapacity, VehicleType vehicleType, SafeDriveStatistics statistics) {
        this.id = SEQUENCE_VAL++;
        this.brand = brand;
        this.name = name;
        this.releaseYear = releaseYear;
        this.tankCapacity = tankCapacity;
        this.vehicleType = vehicleType;
        this.statistics = statistics;
    }

    public int getId() {
        return id;
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
