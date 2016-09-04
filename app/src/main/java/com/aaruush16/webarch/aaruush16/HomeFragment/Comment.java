package com.aaruush16.webarch.aaruush16.HomeFragment;

/**
 * Created by ajitesh on 4/9/16.
 */
public class Comment {
    private String name, email, photo, comment;

    public String getComment() {
        return comment;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}