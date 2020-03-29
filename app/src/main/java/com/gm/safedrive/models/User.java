package com.gm.safedrive.models;

public class User {
    private static int SEQUENCE_VAL = 1;

    private int id;
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

    public User(int id, String createdDate, String email, String password, String firstName, String lastName, int phoneNumber, int profilePhotoId, String lastTimeConnectedDate, String geographicPosition) {
        this.id = id;
        this.createdDate = createdDate;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.profilePhotoId = profilePhotoId;
        this.lastTimeConnectedDate = lastTimeConnectedDate;
        this.geographicPosition = geographicPosition;
    }

    public int getId() {
        return id;
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
}
