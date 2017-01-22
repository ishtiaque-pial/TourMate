package com.example.pial.tourmate.activityPackage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Pial on 20-Nov-16.
 */

public class LoginSharedPreference {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    static final String PHONE_KEY ="user_phoneNo";
    static final String PASS_KEY ="user_password";
    static final String EVENT_KEY ="event_id";
    static final String TOTAL_EXPENSE ="totalExpense";
    static final String TOTAL_BUDGET ="totalBudget";
    static final String LATITUDE ="latitude";
    static final String LONGITUDE ="longitude";
    static final String NEAR_BY_TYPE ="nearBy";
    static final String DESTINATION ="destination";

    static final String DEFAULT_VALUE ="";

    Context context;
    public LoginSharedPreference(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("my preference file",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveData(String phone, String password) {
        editor.putString(PHONE_KEY, phone);
        editor.putString(PASS_KEY, password);
        editor.commit();
    }
    public void saveEventId(String id)
    {
        editor.putString(EVENT_KEY,id);
        editor.commit();
    }
    public void saveTotalExpense(int i)
    {
        editor.putInt(TOTAL_EXPENSE,i);
        editor.commit();
    }
    public void saveBudget(int j)
    {
        editor.putInt(TOTAL_BUDGET,j);
        editor.commit();
    }
    public void saveLotiLongi(String lati,String longi)
    {
        editor.putString(LATITUDE,lati);
        editor.putString(LONGITUDE,longi);
        editor.commit();
    }
    public void saveNearBY(String near)
    {
        editor.putString(NEAR_BY_TYPE,near);
        editor.commit();
    }
    public void destroy()
    {
        editor.clear();
        editor.commit();
    }

    public void saveDestination(String destination)
    {
        editor.putString(DESTINATION,destination);

        editor.commit();
    }

    public String getUserPhone(){
        return sharedPreferences.getString(PHONE_KEY,DEFAULT_VALUE);
    }
    public String getPassword(){
        return sharedPreferences.getString(PASS_KEY,DEFAULT_VALUE);
    }
    public String getEventKey(){return sharedPreferences.getString(EVENT_KEY,DEFAULT_VALUE);}
    public int getTotalExpense(){return sharedPreferences.getInt(TOTAL_EXPENSE,0);}
    public int getBudget(){return sharedPreferences.getInt(TOTAL_BUDGET,0);}
    public String getLatitude(){return sharedPreferences.getString(LATITUDE,"0");}
    public String getLongitude(){return sharedPreferences.getString(LONGITUDE,"0");}
    public String getNearBy(){return sharedPreferences.getString(NEAR_BY_TYPE,DEFAULT_VALUE);}
    public String getDestination(){return sharedPreferences.getString(DESTINATION,DEFAULT_VALUE);}

}
