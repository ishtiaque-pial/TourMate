package com.example.pial.tourmate.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Pial on 27-Nov-16.
 */

public class MomentManager {
    private Activity context;
    DBHelper dbHelper;

    public MomentManager(Activity context) {
        this.context = context;
        dbHelper=new DBHelper(context);
    }

    public long addMoment(Moment moment){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DBHelper.COLUMN_MOMENT_EVENT_ID,moment.getMomentsEventId());
        contentValues.put(DBHelper.COLUMN_MOMENT_USER_PHONE_NO,moment.getMomentPhoneNo());
        contentValues.put(DBHelper.COLUMN_MOMENT_PHOTOPATH,moment.getMomentPhotoPath());
        contentValues.put(DBHelper.COLUMN_EXPENSE_DEATAILS,moment.getMomentDetails());
        contentValues.put(DBHelper.COLUMN_MOMENT_DATE,moment.getMomentDate());
        contentValues.put(DBHelper.COLUMN_MOMENT_TIME_,moment.getMomentTime());


        long result=sqLiteDatabase.insert(DBHelper.TABLE_MOMENT,null,contentValues);
        sqLiteDatabase.close();
        return result;

    }

    public ArrayList<Moment> getAllMoment(String e_id){
        ArrayList<Moment>moments=new ArrayList<>();
        String selectQuery="select * from "+DBHelper.TABLE_MOMENT
                +" where "+DBHelper.COLUMN_MOMENT_EVENT_ID+" = "+ "\"" +e_id+"\"";
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                int momentsID=cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_MOMENT_ID));
                String momentPhoneNo=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_MOMENT_USER_PHONE_NO));
                String momentPhotoPath=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_MOMENT_PHOTOPATH));
                String momentDetails=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EXPENSE_DEATAILS));
                String momentDate=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_MOMENT_DATE));
                String momentTime=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_MOMENT_TIME_));

                //int momentsID, String momentsEventId, String momentPhoneNo, String momentPhotoPath, String momentDetails, String momentDate, String momentTime
                Moment moment=new Moment(momentsID,e_id,momentPhoneNo,momentPhotoPath,momentDetails,momentDate,momentTime);
                moments.add(moment);
            }while(cursor.moveToNext());
        }

        sqLiteDatabase.close();
        return moments;
    }
}
