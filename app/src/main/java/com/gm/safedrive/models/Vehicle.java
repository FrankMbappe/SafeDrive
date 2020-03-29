package com.gm.safedrive.models;

public class Vehicle {
    private static int SEQUENCE_VAL = 1;

    private int id;
    private User owner;
    private String createdDate;
    private String registrationNumber;
    private VehicleModel model;
    private double tankCapacity;
    private double distanceCovered;
    private SafeDriveStatistics statistics;

    public Vehicle(User owner, String createdDate, String registrationNumber, VehicleModel model, double tankCapacity, double distanceCovered,  SafeDriveStatistics statistics) {
        this.id = SEQUENCE_VAL++;
        this.owner = owner;
        this.createdDate = createdDate;
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.tankCapacity = tankCapacity;
        this.distanceCovered = distanceCovered;
        this.statistics = statistics;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public VehicleModel getModel() {
        return model;
    }

    public void setModel(VehicleModel model) {
        this.model = model;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public double getDistanceCovered() {
        return distanceCovered;
    }

    public void setDistanceCovered(double distanceCovered) {
        this.distanceCovered = distanceCovered;
    }

    public SafeDriveStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(SafeDriveStatistics statistics) {
        this.statistics = statistics;
    }

    public SafeDriveStatistics getDefaultStatistics() {
        return model.getStatistics();
    }

}
