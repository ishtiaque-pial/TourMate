package com.example.pial.tourmate.activityPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pial.tourmate.R;

public class FirstActivity extends AppCompatActivity {
    Button loginBT,RegistrationBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        loginBT= (Button) findViewById(R.id.loginButton);
        RegistrationBT= (Button) findViewById(R.id.reqButton);

        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginIntent();
            }
        });

        RegistrationBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegIntent();
            }
        });
    }

    private void loginIntent()
    {
        Intent intent1=new Intent(FirstActivity.this,LoginActivity.class);
        startActivity(intent1);
        this.finish();
    }
    private void RegIntent()
    {
        Intent intent=new Intent(FirstActivity.this,RegistrationActivity.class);
        startActivity(intent);
        this.finish();
    }
}
