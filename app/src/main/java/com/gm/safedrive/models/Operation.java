package com.gm.safedrive.models;

import java.util.Date;

public class Operation {
    private User Author;
    private OperationType Type;
    private Date Date;
    private String Title;
    private String Location;

    public Operation(){

    }
    public Operation(User author, OperationType type, java.util.Date date, String title, String location) {
        Author = author;
        Type = type;
        Date = date;
        Title = title;
        Location = location;
    }

    public User getAuthor() {
        return Author;
    }

    public void setAuthor(User author) {
        Author = author;
    }

    public OperationType getType() {
        return Type;
    }

    public void setType(OperationType type) {
        Type = type;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
