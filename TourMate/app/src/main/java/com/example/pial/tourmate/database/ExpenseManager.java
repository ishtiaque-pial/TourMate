package com.example.pial.tourmate.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Pial on 26-Nov-16.
 */

public class ExpenseManager {
    private Activity context;
    DBHelper dbHelper;

    public ExpenseManager(Activity context) {
        this.context = context;
        dbHelper=new DBHelper(context);
    }

    public long addExpense(Expense expense){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DBHelper.COLUMN_EXPENSE_EVENT_ID,expense.getEventId());
        contentValues.put(DBHelper.COLUMN_EXPENSE_USER_PHONE_NO,expense.getUserPhoneNo());
        contentValues.put(DBHelper.COLUMN_EXPENSE_DEATAILS_,expense.getExpenseDetails());
        contentValues.put(DBHelper.COLUMN_EXPENSE_AMOUNT,expense.getExpenseAmount());
        contentValues.put(DBHelper.COLUMN_EXPENSE_DATE,expense.getExpenseDate());
        contentValues.put(DBHelper.COLUMN_EXPENSE_TIME_,expense.getExpenseTime());


        long result=sqLiteDatabase.insert(DBHelper.TABLE_EXPENSE,null,contentValues);
        sqLiteDatabase.close();
        return result;

    }

    public ArrayList<Expense> getAllExpense(String e_id){
        ArrayList<Expense>expenses=new ArrayList<>();
        String selectQuery="select * from "+DBHelper.TABLE_EXPENSE
                +" where "+DBHelper.COLUMN_EXPENSE_EVENT_ID+" = "+ "\"" +e_id+"\"";
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                int expenseID=cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_EXPENSE_ID));
                String userPhoneNo=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EXPENSE_USER_PHONE_NO));
                String expenseDetails=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EXPENSE_DEATAILS_));
                String expenseAmount=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EXPENSE_AMOUNT));
                String expenseDate=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EXPENSE_DATE));
                String expenseTime=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EXPENSE_TIME_));

                //public Expense(int expenseID, String eventId, String userPhoneNo, String expenseDetails, String expenseAmount, String expenseDate, String expenseTime)
                Expense expense=new Expense(expenseID,e_id,userPhoneNo,expenseDetails,expenseAmount,expenseDate,expenseTime);

                expenses.add(expense);
            }while(cursor.moveToNext());
        }

        sqLiteDatabase.close();
        return expenses;
    }
    public int totalExpense(String idd)
    {
        String selectQuery="select * from "+DBHelper.TABLE_EXPENSE
                +" where "+DBHelper.COLUMN_EXPENSE_EVENT_ID+" = "+ "\"" +idd+"\"";
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        int total=0;
        if(cursor.moveToFirst()){
            do{

                String expenseAmount=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EXPENSE_AMOUNT));
               total=total+Integer.parseInt(expenseAmount);
                //public Expense(int expenseID, String eventId, String userPhoneNo, String expenseDetails, String expenseAmount, String expenseDate, String expenseTime)

            }while(cursor.moveToNext());
        }

        sqLiteDatabase.close();
        return total;
    }
}
