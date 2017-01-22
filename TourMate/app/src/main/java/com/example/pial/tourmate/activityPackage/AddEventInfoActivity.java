package com.example.pial.tourmate.activityPackage;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pial.tourmate.R;
import com.example.pial.tourmate.customListView.EventListAdapter;
import com.example.pial.tourmate.database.Event;
import com.example.pial.tourmate.database.EventManager;
import com.example.pial.tourmate.eventTablayout.EventActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class AddEventInfoActivity extends AppCompatActivity {
    EditText destination,budget,fromDate,toDate;
    EventListAdapter eventListAdapter;
    EventManager eventManager;
    ArrayList<Event> eventList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_info);

        destination= (EditText) findViewById(R.id.ETdestination);
        budget= (EditText) findViewById(R.id.ETbudget);
        fromDate= (EditText) findViewById(R.id.ETfromdate);
        toDate= (EditText) findViewById(R.id.ETtodate);


        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentDate = Calendar.getInstance();
                final int mYear = mcurrentDate.get(Calendar.YEAR);
                final int mMonth = mcurrentDate.get(Calendar.MONTH);
                final int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(AddEventInfoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {


                        mcurrentDate.set(Calendar.YEAR, selectedyear);
                        mcurrentDate.set(Calendar.MONTH, selectedmonth);
                        mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

                        fromDate.setText("" + simpleDateFormat.format(mcurrentDate.getTime()));
                        fromDate.setSelection(fromDate.getText().length());
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentDate = Calendar.getInstance();
                final int mYear = mcurrentDate.get(Calendar.YEAR);
                final int mMonth = mcurrentDate.get(Calendar.MONTH);
                final int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(AddEventInfoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {


                        mcurrentDate.set(Calendar.YEAR, selectedyear);
                        mcurrentDate.set(Calendar.MONTH, selectedmonth);
                        mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

                        toDate.setText("" + simpleDateFormat.format(mcurrentDate.getTime()));
                        toDate.setSelection(fromDate.getText().length());
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });







        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_event,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            Intent intent=new Intent(AddEventInfoActivity.this, EventActivity.class);
            startActivity(intent);
            finish();

        }


        return super.onOptionsItemSelected(item);
    }

    public void save(MenuItem item)
    {

        LoginSharedPreference loginSharedPreference=new LoginSharedPreference(this);
        String phoneNo=loginSharedPreference.getUserPhone().toString();
        String desti=destination.getText().toString();
        String bud=budget.getText().toString();
        String fromm=fromDate.getText().toString();
        String too=toDate.getText().toString();

        if (desti.isEmpty()||bud.isEmpty()||fromm.isEmpty()||too.isEmpty())
        {
            Toast.makeText(this, "Fill all the field", Toast.LENGTH_SHORT).show();
        }
        else {
            EventManager eventManager = new EventManager(AddEventInfoActivity.this);
            Event event = new Event(phoneNo, desti, bud, fromm, too);
            long result = eventManager.addEvent(event);

            if (result > 0) {
                Intent intent = new Intent(AddEventInfoActivity.this, EventActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }


}
