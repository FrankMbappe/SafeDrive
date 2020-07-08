package com.gm.safedrive.models;

public class Vehicle {
    private int id;
    private String ownerId;
    private String createdDate;
    private String registrationNumber;
    private VehicleModel model;
    private double tankCapacity;
    private double distanceCovered;
    private SafeDriveStatistics statistics;

    public Vehicle(){

    }
    public Vehicle(int id, String ownerId, String createdDate, String registrationNumber, VehicleModel model, double tankCapacity, double distanceCovered,  SafeDriveStatistics statistics) {
        this.id = id;
        this.ownerId = ownerId;
        this.createdDate = createdDate;
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.tankCapacity = tankCapacity;
        this.distanceCovered = distanceCovered;
        this.statistics = statistics;
    }

    public Vehicle(String ownerId, String createdDate, String registrationNumber, VehicleModel model, double tankCapacity, double distanceCovered,  SafeDriveStatistics statistics) {
        this.ownerId = ownerId;
        this.createdDate = createdDate;
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.tankCapacity = tankCapacity;
        this.distanceCovered = distanceCovered;
        this.statistics = statistics;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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
