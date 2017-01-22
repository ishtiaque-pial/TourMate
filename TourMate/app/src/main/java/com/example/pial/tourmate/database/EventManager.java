package com.example.pial.tourmate.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Pial on 25-Nov-16.
 */

public class EventManager {

    private Activity context;
    DBHelper dbHelper;

    public EventManager(Activity context) {
        this.context = context;
        dbHelper=new DBHelper(context);
    }

    public long addEvent(Event event){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DBHelper.COLUMN_EVENT_PHONE,event.getUserPhoneNoForEvent());
        contentValues.put(DBHelper.COLUMN_EVENT_DESTINATION,event.getEventDestination());
        contentValues.put(DBHelper.COLUMN_EVENT_BUDGET,event.getEventBudget());
        contentValues.put(DBHelper.COLUMN_EVENT_FROM_DATE,event.getFromDate());
        contentValues.put(DBHelper.COLUMN_EVENT_TO_DATE,event.getToDate());
        contentValues.put(DBHelper.COLUMN_EVENT_TOTAL_EXPENSE,"0");


        long result=sqLiteDatabase.insert(DBHelper.TABLE_EVENT,null,contentValues);
        sqLiteDatabase.close();
        return result;

    }

    public ArrayList<Event> getAllEvents(String phone){
        ArrayList<Event>events=new ArrayList<>();
        String selectQuery="select * from "+DBHelper.TABLE_EVENT
                +" where "+DBHelper.COLUMN_EVENT_PHONE+" = "+ "\"" +phone+"\"";
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        String selectQuery1;
        SQLiteDatabase sqLiteDatabase1;
        Cursor cursor1;


        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_ID));
                String destination=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_DESTINATION));
                String budget=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_BUDGET));
                String fromDate=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_FROM_DATE));
                String toDate=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_TO_DATE));
                String total=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EVENT_TOTAL_EXPENSE));


                int totalexpense=Integer.parseInt(total);


                //public Event(String userPhoneNoForEvent, String eventDestination, String eventBudget, String toDate, String fromDate, String expense)
                Event event = new Event(id,phone,destination,budget,fromDate,toDate,String.valueOf(totalexpense));

                events.add(event);

            }while(cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return events;
    }
    public long updateEventTable(String id,int total){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        long result=-5;
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBHelper.COLUMN_EVENT_TOTAL_EXPENSE,String.valueOf(total));


        result=sqLiteDatabase.update(DBHelper.TABLE_EVENT,contentValues,
                DBHelper.COLUMN_EVENT_ID+" =? ",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return result;
    }

    public long updateEventBudget(String id,int total){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        long result=-5;
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBHelper.COLUMN_EVENT_BUDGET,String.valueOf(total));


        result=sqLiteDatabase.update(DBHelper.TABLE_EVENT,contentValues,
                DBHelper.COLUMN_EVENT_ID+" =? ",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return result;
    }
}
