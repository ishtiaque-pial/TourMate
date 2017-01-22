package com.example.pial.tourmate.dataEntryTablayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pial.tourmate.R;
import com.example.pial.tourmate.activityPackage.LoginSharedPreference;
import com.example.pial.tourmate.database.Moment;
import com.example.pial.tourmate.database.MomentManager;
import com.example.pial.tourmate.eventDetailsTablayout.EventDetailsActivity;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Pial on 26-Nov-16.
 */

public class AddMoment extends Fragment {
    ImageView imgbtn;
    Button takephoto;
    EditText detailsET;
    Button btn;
    CameraPhoto cameraPhotoo;
    GalleryPhoto galleryPhoto;
    final int CAMERA_REQUEST = 13334;
    final int GALLERY_REQUEST = 23333;
    public String photoPath="";
    private File imageFile;
    String Imgname="";
    private String pathchack="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_moment, container, false);

       imgbtn= (ImageView) rootView.findViewById(R.id.imageViewMoments);
        detailsET= (EditText) rootView.findViewById(R.id.moomentsET);
        btn= (Button) rootView.findViewById(R.id.momentsBtn);
        takephoto= (Button) rootView.findViewById(R.id.bt);
        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                Imgname=timeStamp+".jpg";
                imageFile=new File(
                        Environment
                                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                        Imgname
                );
                Uri tempuri=Uri.fromFile(imageFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,tempuri);
                pathchack=imageFile.getAbsolutePath().toString();
                startActivityForResult(intent,CAMERA_REQUEST);
                //loadimage();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pathchack.isEmpty()||detailsET.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(), "Fill the Field", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    LoginSharedPreference loginSharedPreference=new LoginSharedPreference(getActivity());
                    String evnID=loginSharedPreference.getEventKey();
                    String phn=loginSharedPreference.getUserPhone();
                    String details=detailsET.getText().toString();
                    String date=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                    String time=new SimpleDateFormat("hh:mma").format(new Date());
                    //String momentsEventId, String momentPhoneNo, String momentPhotoPath, String momentDetails, String momentDate, String momentTime
                    Moment moment=new Moment(evnID,phn,pathchack,details,date,time);
                    MomentManager momentManager=new MomentManager(getActivity());
                    long res=momentManager.addMoment(moment);
                    if (res>0)
                    {
                        Intent intent = new Intent(getActivity(), EventDetailsActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        Toast.makeText(getActivity(), "Something Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode==CAMERA_REQUEST)
            {
                if (resultCode==RESULT_OK)
                {
                    loadimage();
                }
                else if (resultCode==RESULT_CANCELED)
                {
                    pathchack="";
                }
            }
    }


    public void loadimage()
    {
        try{
        //File imgFile = new File(Imgname);

        if (imageFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

           // Toast.makeText(getActivity(), "ok"+imageFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            imgbtn.setImageBitmap(myBitmap);
        }
            else
        {
            Toast.makeText(getActivity(), "not ok", Toast.LENGTH_SHORT).show();
            pathchack="";
        }
    }catch (Exception e) {}
    }



}
