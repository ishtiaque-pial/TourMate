package com.example.pial.tourmate.database;

/**
 * Created by Pial on 22-Nov-16.
 */

public class User {

    private int id;
    private String userName;
    private String userPassword;
    private String userPhoneNo;
    private String userPhotoPath;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public String getUserPhotoPath() {
        return userPhotoPath;
    }

    public User(String userName, String userPhoneNo, String userPassword, String userPhotoPath) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNo = userPhoneNo;
        this.userPhotoPath = userPhotoPath;
    }

    public User(int id, String userName, String userPhoneNo,String userPassword, String userPhotoPath) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhoneNo = userPhoneNo;
        this.userPhotoPath = userPhotoPath;
    }
}
