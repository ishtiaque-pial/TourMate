package com.example.pial.tourmate.eventDetailsTablayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pial.tourmate.FindPlaceResponse.AreaApi;
import com.example.pial.tourmate.FindPlaceResponse.AreaLogLatResponse;
import com.example.pial.tourmate.FindPlaceResponse.Result;
import com.example.pial.tourmate.R;
import com.example.pial.tourmate.Weather.Constants;
import com.example.pial.tourmate.Weather.Daily;
import com.example.pial.tourmate.Weather.Datum__;
import com.example.pial.tourmate.Weather.WeatherApi;
import com.example.pial.tourmate.Weather.WeatherResponse;
import com.example.pial.tourmate.activityPackage.LoginSharedPreference;
import com.example.pial.tourmate.dataEntryTablayout.ForecastAdapter;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pial on 26-Nov-16.
 */

public class EventWeather extends Fragment {
    LoginSharedPreference loginSharedPreference2;
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/textsearch/";
    private AreaApi areaApi;
    String destination;
    Double lati;
    Double longi;
    WeatherApi weatherApi;
    public static ArrayList<Daily> forecastArrayList;
    Boolean isInternetConnection = false;
    List<Datum__> forecastList1;
    ListView forecastListView;
    ForecastAdapter forecastAdapter;
    TextView section_label;
    ProgressDialog progress;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_weather, container, false);
        section_label= (TextView) rootView.findViewById(R.id.section_label);
        forecastListView = (ListView) rootView.findViewById(R.id.weatherListView);
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        networkLibraryIntialize();
        forecastArrayList = new ArrayList<Daily>();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(getActivity()).addApi(AppIndex.API).build();

        loginSharedPreference2=new LoginSharedPreference(getActivity());



            destination=loginSharedPreference2.getDestination();
        section_label.setText("Weather of "+destination);
            String urlString = String.format("json?query=%s&key=AIzaSyD7tOmCYtXq9OzBHulugWq3PA65Chqd7iU", destination);
            Call<AreaLogLatResponse> areaLogLatResponseCall = areaApi.getAllResponse(urlString);
            areaLogLatResponseCall.enqueue(new Callback<AreaLogLatResponse>() {
                @Override
                public void onResponse(Call<AreaLogLatResponse> call, Response<AreaLogLatResponse> response) {
                    AreaLogLatResponse areaLogLatResponse = response.body();
                    final List<Result> results = areaLogLatResponse.getResults();
                    if (results.isEmpty()) {
                        Toast.makeText(getActivity(), "wrong", Toast.LENGTH_SHORT).show();
                    } else {
                        lati = results.get(0).getGeometry().getLocation().getLat();
                        longi = results.get(0).getGeometry().getLocation().getLng();

                        getData();
                    }


                }

                @Override
                public void onFailure(Call<AreaLogLatResponse> call, Throwable t) {
                    progress.dismiss();
                    Log.e("webservice", "onFailure: " + t.getMessage());
                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();

                }
            });


        return rootView;
    }
    private void networkLibraryIntialize() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(Constants.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        areaApi = retrofit.create(AreaApi.class);
        weatherApi = retrofit1.create(WeatherApi.class);
    }
    private void getData()
    {

        String url = "https://api.darksky.net/forecast/2e59fb6e0ec91a11878ef6f66a92ee8e/" + lati + "," + longi;
        Call<WeatherResponse> weatherResponseCall = weatherApi.getAllWeatherData(url);
        weatherResponseCall.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse weatherResponse = response.body();

                Daily daily = new Daily();
                daily = weatherResponse.getDaily();

                List<Datum__> info;
                info = daily.getData();

                /*int f = info.get(1).getTemperatureMax().intValue();
                //Double f = info.get(1).getTemperatureMax();
                int cTemp =  ((f - 32) * 5) / 9;*/


                forecastList1 = info;

                forecastAdapter = new ForecastAdapter(getActivity(), info);
                forecastListView.setAdapter(forecastAdapter);


                progress.dismiss();

                Log.e("webservice", "onResponse: " + weatherResponse.getLatitude());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("webservice", "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
