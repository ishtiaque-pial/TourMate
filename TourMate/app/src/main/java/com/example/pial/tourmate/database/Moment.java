package com.example.pial.tourmate.database;

/**
 * Created by Pial on 27-Nov-16.
 */

public class Moment {
    private int momentsID;
    private String momentsEventId;
    private String momentPhoneNo;

    public int getMomentsID() {
        return momentsID;
    }

    public String getMomentsEventId() {
        return momentsEventId;
    }

    public String getMomentPhoneNo() {
        return momentPhoneNo;
    }

    public String getMomentPhotoPath() {
        return momentPhotoPath;
    }

    public String getMomentDetails() {
        return momentDetails;
    }

    public String getMomentDate() {
        return momentDate;
    }

    public String getMomentTime() {
        return momentTime;
    }

    public Moment(String momentsEventId, String momentPhoneNo, String momentPhotoPath, String momentDetails, String momentDate, String momentTime) {

        this.momentsEventId = momentsEventId;
        this.momentPhoneNo = momentPhoneNo;
        this.momentPhotoPath = momentPhotoPath;
        this.momentDetails = momentDetails;
        this.momentDate = momentDate;
        this.momentTime = momentTime;
    }

    private String momentPhotoPath;
    private String momentDetails;
    private String momentDate;
    private String momentTime;

    public Moment(int momentsID, String momentsEventId, String momentPhoneNo, String momentPhotoPath, String momentDetails, String momentDate, String momentTime) {
        this.momentsID = momentsID;
        this.momentsEventId = momentsEventId;
        this.momentPhoneNo = momentPhoneNo;
        this.momentPhotoPath = momentPhotoPath;
        this.momentDetails = momentDetails;
        this.momentDate = momentDate;
        this.momentTime = momentTime;
    }
}
