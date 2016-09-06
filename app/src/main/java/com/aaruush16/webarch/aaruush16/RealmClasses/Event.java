package com.aaruush16.webarch.aaruush16.RealmClasses;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Rishi on 10-08-2016.
 */
public class Event extends RealmObject{
    @PrimaryKey
    public int id;
    public String Type;
    public String Name;
    public String Description;
    public String Contact;
    public String Rules;
    public String Arena;
    public String Rounds;
    public String ImageURL;
    public Boolean Fav;

    public String getRounds() {
        return Rounds;
    }

    public void setRounds(String rounds) {
        Rounds = rounds;
    }

    public void setRules(String rules) {
        Rules = rules;
    }

    public String getRules() {
        return Rules;
    }

    public void setArena(String arena) {
        Arena = arena;
    }

    public String getArena() {
        return Arena;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return Type;
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

    public String getImageURL() {
        return ImageURL;
    }

    public Boolean getFav() {
        return Fav;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public void setFav(Boolean fav) {
        Fav = fav;
    }
}
