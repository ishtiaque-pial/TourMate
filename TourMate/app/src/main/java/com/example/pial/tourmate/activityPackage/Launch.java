package com.example.pial.tourmate.activityPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pial.tourmate.eventTablayout.EventActivity;
import com.example.pial.tourmate.R;

import java.util.Timer;
import java.util.TimerTask;

public class Launch extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        final LoginSharedPreference loginSharedPreference=new LoginSharedPreference(this);
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (loginSharedPreference.getPassword().isEmpty()&&loginSharedPreference.getPassword().isEmpty())
                {
                    Intent intent = new Intent(Launch.this, FirstActivity.class);
                    startActivity(intent);
                    finishscreen();
                }
                else
                {
                    Intent intent = new Intent(Launch.this,EventActivity.class);
                    startActivity(intent);
                    finishscreen();
                }

            }
        };
        Timer t = new Timer();
        t.schedule(task, 5000);
    }
    private void finishscreen() {
        this.finish();
    }
}


