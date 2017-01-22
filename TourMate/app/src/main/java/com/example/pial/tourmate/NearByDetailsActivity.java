package com.example.pial.tourmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pial.tourmate.NearByLocationInfo.NearByLocationResponse;
import com.example.pial.tourmate.NearByLocationInfo.PlacesServiceApi;
import com.example.pial.tourmate.NearByLocationInfo.Result;
import com.example.pial.tourmate.activityPackage.LoginSharedPreference;
import com.example.pial.tourmate.activityPackage.RegistrationActivity;
import com.example.pial.tourmate.customListView.PlacesCustomAdapter;
import com.example.pial.tourmate.dataEntryTablayout.DataEntryActivity;
import com.example.pial.tourmate.eventDetailsTablayout.EventDetailsActivity;
import com.example.pial.tourmate.eventDetailsTablayout.EventNearBy;
import com.example.pial.tourmate.eventTablayout.CurrentEvent;
import com.example.pial.tourmate.eventTablayout.EventActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearByDetailsActivity extends AppCompatActivity {
   TextView type;
    ListView locatinInfoListView;
    String nearByType,latitude,longitude;
    private PlacesCustomAdapter placesCustomAdapter;
    LoginSharedPreference loginSharedPreference;

    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/";
    private PlacesServiceApi placesServiceApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_details);
        type= (TextView) findViewById(R.id.NearbyTypeTV);
        locatinInfoListView= (ListView) findViewById(R.id.nearByList);
        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        loginSharedPreference=new LoginSharedPreference(this);
        nearByType=loginSharedPreference.getNearBy();
        latitude=loginSharedPreference.getLatitude();
        longitude=loginSharedPreference.getLongitude();
        type.setText(nearByType);


        networkLibraryIntialize();
        getData();


    }

    private void networkLibraryIntialize() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        placesServiceApi = retrofit.create(PlacesServiceApi.class);
    }
    private void getData()
    {

        String urlString = String.format("json?location=%s,%s&radius=1000&type=%s&key=AIzaSyD7tOmCYtXq9OzBHulugWq3PA65Chqd7iU",latitude,longitude,nearByType);
        Call<NearByLocationResponse> nearbyLocationResponseCall = placesServiceApi.getAllResponse(urlString);
        nearbyLocationResponseCall.enqueue(new Callback<NearByLocationResponse>() {
            @Override
            public void onResponse(Call<NearByLocationResponse> call, Response<NearByLocationResponse> response) {
                NearByLocationResponse nearByLocationResponse=response.body();
                final List<Result> results = nearByLocationResponse.getResults();
                if (results==null)
                {
                    Toast.makeText(NearByDetailsActivity.this, "Found Nothing From your location", Toast.LENGTH_SHORT).show();
                }
                else {
                    placesCustomAdapter = new PlacesCustomAdapter(NearByDetailsActivity.this, results);
                    locatinInfoListView.setAdapter(placesCustomAdapter);
                }

                //Toast.makeText(NearByDetailsActivity.this, "ok "+loginSharedPreference.getLongitude(), Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onFailure(Call<NearByLocationResponse> call, Throwable t) {
                Toast.makeText(NearByDetailsActivity.this, "Wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        if (item.getItemId()==android.R.id.home)
        {
            Intent intent=new Intent(NearByDetailsActivity.this,NearByDetailsActivity.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(NearByDetailsActivity.this, EventDetailsActivity.class);
        startActivity(intent);
        finish();
    }
}

