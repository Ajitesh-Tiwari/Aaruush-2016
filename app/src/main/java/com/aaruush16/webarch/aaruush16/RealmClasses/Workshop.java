package com.aaruush16.webarch.aaruush16.RealmClasses;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Rishi on 27-08-2016.
 */
public class Workshop extends RealmObject {
    @PrimaryKey
    int id;
    String name;
    String desc;
    String team;
    String date;
    String cost;
    String time;
    String company_name;
    String image;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getTeam() {
        return team;
    }

    public String getDate() {
        return date;
    }

    public String getCost() {
        return cost;
    }

    public String getTime() {
        return time;
    }

    public String getCompany_name() {
        return company_name;
    }
}
