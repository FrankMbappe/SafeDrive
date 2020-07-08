package com.gm.safedrive.models;

import java.util.ArrayList;

public class User {
    private static int SEQUENCE_VAL = 1;
    public static final String KEY = "SESSION_USER";

    private String id;
    private String createdDate;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    private int profilePhotoId;
    private int phoneNumber;
    private String lastTimeConnectedDate;
    private String geographicPosition;
    private ArrayList<Vehicle> vehicles;


    public User(){
        
    }
    public User(String id, String createdDate, String email, String password, String firstName, String lastName, int phoneNumber, ArrayList<Vehicle> vehicles){
        this.id = id;
        this.createdDate = createdDate;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.vehicles = vehicles;
    }

    public User(String id, String createdDate, String email, String password, String firstName, String lastName, int phoneNumber, ArrayList<Vehicle> vehicles, int profilePhotoId, String lastTimeConnectedDate, String geographicPosition) {
        this.id = id;
        this.createdDate = createdDate;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.vehicles = vehicles;
        this.profilePhotoId = profilePhotoId;
        this.lastTimeConnectedDate = lastTimeConnectedDate;
        this.geographicPosition = geographicPosition;
    }
    public User(String createdDate, String email, String password, String firstName, String lastName, int phoneNumber, ArrayList<Vehicle> vehicles, int profilePhotoId, String lastTimeConnectedDate, String geographicPosition) {
        this.createdDate = createdDate;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.vehicles = vehicles;
        this.profilePhotoId = profilePhotoId;
        this.lastTimeConnectedDate = lastTimeConnectedDate;
        this.geographicPosition = geographicPosition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return (firstName + " " + lastName);
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGeographicPosition() {
        return geographicPosition;
    }

    public void setGeographicPosition(String geographicPosition) {
        this.geographicPosition = geographicPosition;
    }

    public String getLastTimeConnectedDate() {
        return lastTimeConnectedDate;
    }

    public void setLastTimeConnectedDate(String lastTimeConnectedDate) {
        this.lastTimeConnectedDate = lastTimeConnectedDate;
    }

    public int getProfilePhotoId() {
        return profilePhotoId;
    }

    public void setProfilePhotoId(int id){
        this.profilePhotoId = id;
    }

    public ArrayList<Vehicle> getVehicles(){
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle){
        if(vehicles != null && vehicle != null){
            vehicles.add(vehicle);
        }
    }
    public void updateVehicle(int id, Vehicle vehicle){
        for(Vehicle v : vehicles){
            if(v.getId() == id){
                v = vehicle;
            }
        }
    }
    public void deleteVehicle(int id){
        for(Vehicle v : vehicles){
            if(v.getId() == id){
                vehicles.remove(v);
            }
        }
    }
    public void setVehicles(ArrayList<Vehicle> vehicles){
        this.vehicles = vehicles;
    }

}
