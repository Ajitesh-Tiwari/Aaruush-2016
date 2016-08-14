package com.aaruush16.webarch.aaruush16.RealmClasses;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Rishi on 10-08-2016.
 */
public class Event extends RealmObject{
    @PrimaryKey
    public int id;

    public String Type;
    public String SubType;
    public String Name;
    public String Description;
    public String Contact;
    public String Cost;
    public String Date;
    public String ImageURL;

    //Getters

    public int getId() {
        return id;
    }

    public String getType() {
        return Type;
    }

    public String getSubType() {
        return SubType;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getContact() {
        return Contact;
    }

    public String getCost() {
        return Cost;
    }

    public String getDate() {
        return Date;
    }

    public String getImageURL() {
        return ImageURL;
    }

    //Setters


    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setSubType(String subType) {
        SubType = subType;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

}
