package com.example.pial.tourmate.database;

/**
 * Created by Pial on 26-Nov-16.
 */

public class Expense {

    private int expenseID;
    private String eventId;
    private String userPhoneNo;
    private String expenseDetails;
    private String expenseAmount;
    private String expenseDate;
    private String expenseTime;


    public int getExpenseID() {
        return expenseID;
    }

    public String getEventId() {
        return eventId;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public String getExpenseDetails() {
        return expenseDetails;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public String getExpenseTime() {
        return expenseTime;
    }

    public Expense(String eventId, String userPhoneNo, String expenseDetails, String expenseAmount, String expenseDate, String expenseTime) {

        this.eventId = eventId;
        this.userPhoneNo = userPhoneNo;
        this.expenseDetails = expenseDetails;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
        this.expenseTime = expenseTime;
    }

    public Expense(int expenseID, String eventId, String userPhoneNo, String expenseDetails, String expenseAmount, String expenseDate, String expenseTime) {

        this.expenseID = expenseID;
        this.eventId = eventId;
        this.userPhoneNo = userPhoneNo;
        this.expenseDetails = expenseDetails;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
        this.expenseTime = expenseTime;
    }
}
