package com.example.pial.tourmate.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Pial on 22-Nov-16.
 */

public class UserManager {

    private Activity context;
    DBHelper dbHelper;

    public UserManager(Activity context) {
        this.context = context;
        dbHelper=new DBHelper(context);
    }

    public long addUser(User user){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DBHelper.COLUMN_NAME,user.getUserName());
        contentValues.put(DBHelper.COLUMN_PHONE,user.getUserPhoneNo());
        contentValues.put(DBHelper.COLUMN_PASSWORD,user.getUserPassword());
        contentValues.put(DBHelper.COLUMN_PHOTO_PATH,user.getUserPhotoPath());


        long result=sqLiteDatabase.insert(DBHelper.TABLE_USER,null,contentValues);
        sqLiteDatabase.close();
        return result;
    }
    public User getUserByPhone(String phone)
    {
        User user=null;

        String selectQuery="select * from "+DBHelper.TABLE_USER
                +" where "+DBHelper.COLUMN_PHONE+" = "+ "\"" +phone+"\"";

        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            int userID=cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID));
            String uname=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
            String upassword=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PASSWORD));
            String uphone=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PHONE));
            String upath=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PHOTO_PATH));

           user=new User(userID,uname,uphone,upassword,upath);
        }
        return user;
    }
}
