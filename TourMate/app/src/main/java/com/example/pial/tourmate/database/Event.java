package com.example.pial.tourmate.database;

/**
 * Created by Pial on 25-Nov-16.
 */

public class Event {

    private int eventId;
    private String userPhoneNoForEvent;
    private String eventDestination;
    private String eventBudget;

    public Event(String userPhoneNoForEvent, String eventDestination, String eventBudget, String fromDate, String toDate) {
        this.userPhoneNoForEvent = userPhoneNoForEvent;
        this.eventDestination = eventDestination;
        this.eventBudget = eventBudget;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    private String fromDate;
    private String toDate;
    private String expense;



    public String getExpense() {
        return expense;
    }


    public Event(String userPhoneNoForEvent, String eventDestination, String eventBudget, String toDate, String fromDate, String expense) {
        this.userPhoneNoForEvent = userPhoneNoForEvent;
        this.eventDestination = eventDestination;
        this.eventBudget = eventBudget;
        this.toDate = toDate;
        this.fromDate = fromDate;
        this.expense = expense;
    }

    public Event(int eventId, String userPhoneNoForEvent, String eventDestination, String eventBudget, String fromDate, String toDate, String expense) {

        this.eventId = eventId;
        this.userPhoneNoForEvent = userPhoneNoForEvent;
        this.eventDestination = eventDestination;
        this.eventBudget = eventBudget;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.expense = expense;
    }

    public String getUserPhoneNoForEvent() {
        return userPhoneNoForEvent;
    }

    public String getEventDestination() {
        return eventDestination;
    }

    public String getEventBudget() {
        return eventBudget;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }
    public int getEventId() {
        return eventId;
    }
}
