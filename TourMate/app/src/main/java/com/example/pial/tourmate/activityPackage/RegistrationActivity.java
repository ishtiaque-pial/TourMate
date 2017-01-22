package com.example.pial.tourmate.activityPackage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pial.tourmate.eventTablayout.EventActivity;
import com.example.pial.tourmate.R;
import com.example.pial.tourmate.database.User;
import com.example.pial.tourmate.database.UserManager;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RegistrationActivity extends AppCompatActivity {
    EditText name,phoneNo,password,confirmPassword;
    ImageView showImage;
    ImageButton mCamera,mGallary;
    LoginSharedPreference loginSharedPreference;
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    final int CAMERA_REQUEST = 13333;
    final int GALLERY_REQUEST = 23333;
    public String photoPath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name= (EditText) findViewById(R.id.etNmae);
        phoneNo= (EditText) findViewById(R.id.etPhone);
        password= (EditText) findViewById(R.id.etPassword);
        confirmPassword= (EditText) findViewById(R.id.confirmPasswordEt);
        mCamera= (ImageButton) findViewById(R.id.imageCameraBt);
        mGallary= (ImageButton) findViewById(R.id.imageGallaryBt);
        showImage= (ImageView) findViewById(R.id.showImage);

        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(getApplicationContext());


        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        mGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickGalleryPhoto();
            }
        });


    }

    private void takePhoto() {

        try {
            startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
            cameraPhoto.addToGallery();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Something Wrong while taking photos", Toast.LENGTH_SHORT).show();
        }
    }
    private void pickGalleryPhoto() {

        startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
    }
    public void btnClick(View view) {

        if (name.getText().toString().isEmpty()||phoneNo.getText().toString().isEmpty()||password.getText().toString().isEmpty()||confirmPassword.getText().toString().isEmpty()||photoPath.isEmpty())
        {
            Toast.makeText(this, "Fill all the field", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (password.getText().toString().equals(confirmPassword.getText().toString()))
            {
                String dname=name.getText().toString();
                String dphone=phoneNo.getText().toString();
                String dpassword=password.getText().toString();
                String dpath=photoPath.toString();

                UserManager userManager=new UserManager(RegistrationActivity.this);

                User u=userManager.getUserByPhone(dphone);
                if (u!=null)
                {
                    Toast.makeText(RegistrationActivity.this, "Your Phone Number Already Register", Toast.LENGTH_SHORT).show();
                }
                else {
                    User user = new User(dname, dphone, dpassword, dpath);

                    long result = userManager.addUser(user);
                    if (result > 0) {
                        Toast.makeText(RegistrationActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        loginSharedPreference = new LoginSharedPreference(this);
                        loginSharedPreference.saveData(phoneNo.getText().toString(), password.getText().toString());
                        Intent intent = new Intent(RegistrationActivity.this, EventActivity.class);
                        startActivity(intent);
                        this.finish();
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                    }
                }

            }
            else
            {
                Toast.makeText(this, "Password Not match", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                photoPath = cameraPhoto.getPhotoPath();

                loadImage(showImage,photoPath,512,512);

            } else if (requestCode == GALLERY_REQUEST) {
                Uri uri = data.getData();

                galleryPhoto.setPhotoUri(uri);
                photoPath = galleryPhoto.getPath();

                loadImage(showImage,photoPath,512,512);
            }
        }
    }

    private void loadImage(ImageView showProductImage, String photoPath, int width, int height) {
        try {
            Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(width,height).getBitmap();
            showProductImage.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            Toast.makeText(this,
                    "Something Wrong while choosing photos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(RegistrationActivity.this,FirstActivity.class);
        startActivity(intent);
        this.finish();
    }
}
