package com.example.pial.tourmate.eventDetailsTablayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pial.tourmate.NearByDetailsActivity;
import com.example.pial.tourmate.R;
import com.example.pial.tourmate.activityPackage.LoginSharedPreference;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Pial on 26-Nov-16.
 */

public class EventNearBy extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;
    double latitude=0, longitude=0;
    Button cafe,resturant,atm,bank;
    LoginSharedPreference loginSharedPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_nearby, container, false);
        cafe= (Button) rootView.findViewById(R.id.btnCafe);
        resturant= (Button) rootView.findViewById(R.id.brnRestaurant);
        atm= (Button) rootView.findViewById(R.id.btnAtm);
        bank= (Button) rootView.findViewById(R.id.btnBank);

        loginSharedPreference=new LoginSharedPreference(getActivity());
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSharedPreference.saveNearBY("cafe");
                gotoToNearByDetailsActivity();
            }
        });
        resturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSharedPreference.saveNearBY("restaurant");
                gotoToNearByDetailsActivity();
            }
        });
        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSharedPreference.saveNearBY("atm");
                gotoToNearByDetailsActivity();
            }
        });
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSharedPreference.saveNearBY("bank");
                gotoToNearByDetailsActivity();
            }
        });

        return rootView;
    }

    private void gotoToNearByDetailsActivity() {

        if (latitude==0||longitude==0)
        {
            Toast.makeText(getActivity(), "Can not find your location", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loginSharedPreference.saveLotiLongi(String.valueOf(latitude),String.valueOf(longitude));
            Intent intent=new Intent(getActivity(), NearByDetailsActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        googleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getActivity(), "no connection", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLocationChanged(Location location) {
        //latTV.setText(String.valueOf(location.getLatitude()));
        //lonTV.setText(String.valueOf(location.getLongitude()));
        latitude=location.getLatitude();
        longitude=location.getLongitude();

    }
}
