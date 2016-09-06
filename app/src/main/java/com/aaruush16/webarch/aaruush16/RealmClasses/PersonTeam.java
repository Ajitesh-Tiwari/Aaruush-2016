package com.aaruush16.webarch.aaruush16.RealmClasses;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Rishi on 04-09-2016.
 */
public class PersonTeam extends RealmObject {
    @PrimaryKey
    String img_id;

    String name,contact,email,position,fb_link;

    public String getImg_id() {
        return img_id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getFb_link() {
        return fb_link;
    }

    public String getPosition() {
        return position;
    }

    public void setFb_link(String fb_link) {
        this.fb_link = fb_link;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
