package com.example.pial.tourmate.activityPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pial.tourmate.eventTablayout.EventActivity;
import com.example.pial.tourmate.R;
import com.example.pial.tourmate.database.User;
import com.example.pial.tourmate.database.UserManager;

public class LoginActivity extends AppCompatActivity {
    EditText phone,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone= (EditText) findViewById(R.id.phoneEt);
        pass= (EditText) findViewById(R.id.passET);
    }

    public void onclick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(LoginActivity.this,FirstActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void onClickButton(View view) {
        if (phone.getText().toString().isEmpty()||pass.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Fill All Field", Toast.LENGTH_SHORT).show();
        }
        else
        {
            UserManager userManager=new UserManager(LoginActivity.this);

            User u=userManager.getUserByPhone(phone.getText().toString());
            LoginSharedPreference loginSharedPref=new LoginSharedPreference(this);


            if (u!=null)
            {
                if (u.getUserPassword().equals(pass.getText().toString()))
                {
                    loginSharedPref.saveData(phone.getText().toString(), pass.getText().toString());
                    Intent intent=new Intent(LoginActivity.this,EventActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(this, "Phone or Password Not Match", Toast.LENGTH_SHORT).show();
                }

            }
            else
            {
                Toast.makeText(this, "Phone or Password Not Match", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
