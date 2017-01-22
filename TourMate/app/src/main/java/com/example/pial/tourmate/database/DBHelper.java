package com.example.pial.tourmate.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pial on 22-Nov-16.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME="tour_mate_database";
    public static final int DATABASE_VERSION=1;


    public static final String TABLE_USER="login";

    public static final String COLUMN_ID="id";
    public static final String COLUMN_NAME="userName";
    public static final String COLUMN_PHONE="userPhone";
    public static final String COLUMN_PASSWORD="userPassword";
    public static final String COLUMN_PHOTO_PATH="userPhotoPath";

    private String CREATE_TABLE_USER="create table "+TABLE_USER+"( "+COLUMN_ID+
            " integer primary key autoincrement, "+COLUMN_NAME+" text,"+COLUMN_PHONE+" text, "+COLUMN_PASSWORD+" text, "+COLUMN_PHOTO_PATH+" text);";

    public static final String TABLE_EVENT="event";

    public static final String COLUMN_EVENT_ID="eventID";
    public static final String COLUMN_EVENT_PHONE="userPhoneNoForEvent";
    public static final String COLUMN_EVENT_DESTINATION="eventDestination";
    public static final String COLUMN_EVENT_BUDGET="eventBudget";
    public static final String COLUMN_EVENT_FROM_DATE="fromDate";
    public static final String COLUMN_EVENT_TO_DATE="toDate";
    public static final String COLUMN_EVENT_TOTAL_EXPENSE="totalExpense";


    private String CREATE_TABLE_EVENT="create table "+TABLE_EVENT+"( "+COLUMN_EVENT_ID+
            " integer primary key autoincrement, "+COLUMN_EVENT_PHONE+" text,"+COLUMN_EVENT_DESTINATION+" text, "+COLUMN_EVENT_BUDGET+" text, "+COLUMN_EVENT_FROM_DATE+" text,"+COLUMN_EVENT_TO_DATE+" text,"+COLUMN_EVENT_TOTAL_EXPENSE+" text);";

    public static final String TABLE_EXPENSE="expense";

    public static final String COLUMN_EXPENSE_ID="expenseID";
    public static final String COLUMN_EXPENSE_EVENT_ID="eventId";
    public static final String COLUMN_EXPENSE_USER_PHONE_NO="userPhoneNo";
    public static final String COLUMN_EXPENSE_DEATAILS_="expenseDetails";
    public static final String COLUMN_EXPENSE_AMOUNT="expenseAmount";
    public static final String COLUMN_EXPENSE_DATE="expenseDate";
    public static final String COLUMN_EXPENSE_TIME_="expenseTime";


    private String CREATE_TABLE_EXPENSE="create table "+TABLE_EXPENSE+"( "+COLUMN_EXPENSE_ID+
            " integer primary key autoincrement, "+COLUMN_EXPENSE_EVENT_ID+" text,"+COLUMN_EXPENSE_USER_PHONE_NO+" text, "+COLUMN_EXPENSE_DEATAILS_+" text, "+COLUMN_EXPENSE_AMOUNT+" text, "+COLUMN_EXPENSE_DATE+" text, "+COLUMN_EXPENSE_TIME_+" text);";

    public static final String TABLE_MOMENT="Moments";

    public static final String COLUMN_MOMENT_ID="momentsID";
    public static final String COLUMN_MOMENT_EVENT_ID="momentsEventId";
    public static final String COLUMN_MOMENT_USER_PHONE_NO="momentPhoneNo";
    public static final String COLUMN_MOMENT_PHOTOPATH="momentPhotoPath";
    public static final String COLUMN_EXPENSE_DEATAILS="momentDetails";
    public static final String COLUMN_MOMENT_DATE="momentDate";
    public static final String COLUMN_MOMENT_TIME_="momentTime";


    private String CREATE_TABLE_MOMENT="create table "+TABLE_MOMENT+"( "+COLUMN_MOMENT_ID+
            " integer primary key autoincrement, "+COLUMN_MOMENT_EVENT_ID+" text,"+COLUMN_MOMENT_USER_PHONE_NO+" text, "+COLUMN_MOMENT_PHOTOPATH+" text, "+COLUMN_EXPENSE_DEATAILS+" text, "+COLUMN_MOMENT_DATE+" text, "+COLUMN_MOMENT_TIME_+" text);";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_EVENT);
        db.execSQL(CREATE_TABLE_EXPENSE);
        db.execSQL(CREATE_TABLE_MOMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist "+TABLE_USER);
        db.execSQL("drop table if exist "+TABLE_EVENT);
        db.execSQL("drop table if exist "+TABLE_EXPENSE);
        db.execSQL("drop table if exist "+TABLE_MOMENT);
        onCreate(db);

    }
}
