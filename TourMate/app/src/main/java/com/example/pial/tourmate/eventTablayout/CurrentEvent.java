package com.example.pial.tourmate.eventTablayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pial.tourmate.FindPlaceResponse.AreaApi;
import com.example.pial.tourmate.FindPlaceResponse.AreaLogLatResponse;
import com.example.pial.tourmate.FindPlaceResponse.Result;
import com.example.pial.tourmate.NearByLocationInfo.PlacesServiceApi;
import com.example.pial.tourmate.R;
import com.example.pial.tourmate.activityPackage.AddEventInfoActivity;
import com.example.pial.tourmate.activityPackage.LoginSharedPreference;
import com.example.pial.tourmate.customListView.EventListAdapter;
import com.example.pial.tourmate.database.Event;
import com.example.pial.tourmate.database.EventManager;
import com.example.pial.tourmate.eventDetailsTablayout.EventDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CurrentEvent extends Fragment {

    Button btn;
    EventListAdapter eventListAdapter;
    EventManager eventManager;
    ArrayList<Event> eventList;
    FloatingActionButton flt;
    TextView tx;
    ImageView imageView;
    ListView listView;
    LoginSharedPreference loginSharedPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_current, container, false);

        btn= (Button) rootView.findViewById(R.id.btnAddEvent);
        flt= (FloatingActionButton) rootView.findViewById(R.id.fabAddEvent);
        tx= (TextView) rootView.findViewById(R.id.textView);
        imageView= (ImageView) rootView.findViewById(R.id.imageView2);
        listView= (ListView) rootView.findViewById(R.id.eventListView);

        //networkLibraryIntialize();
       // Toast.makeText(getActivity(), "lati"+loginSharedPreference1.getLatitude()+" longi"+loginSharedPreference1.getLongitude(), Toast.LENGTH_LONG).show();





        loginSharedPreference=new LoginSharedPreference(getActivity());
        String phone=loginSharedPreference.getUserPhone();
        eventManager=new EventManager(getActivity());
        eventList=eventManager.getAllEvents(phone);
        if (eventList.isEmpty())
        {

        }
        else
        {
            btn.setVisibility(View.GONE);
            tx.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            eventListAdapter=new EventListAdapter(getActivity(),eventList);
            listView.setAdapter(eventListAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Event e=eventList.get(position);
                    String e_id=String.valueOf(e.getEventId());
                    int budget=Integer.parseInt(e.getEventBudget());
                    String deatination=e.getEventDestination();
                    //getData(deatination);

                    loginSharedPreference.saveDestination(deatination);
                    Toast.makeText(getActivity(), "ok"+loginSharedPreference.getDestination(), Toast.LENGTH_SHORT).show();
                    loginSharedPreference.saveEventId(e_id);
                    loginSharedPreference.saveBudget(budget);
                    Intent in=new Intent(getActivity(), EventDetailsActivity.class);

                    startActivity(in);
                    getActivity().finish();


                }
            });
        }



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentPerpase();
            }
        });

        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentPerpase();
            }
        });

        return rootView;
    }

    private void intentPerpase() {

        Intent intent=new Intent(getActivity(), AddEventInfoActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
    /**/
    /*private void getData(String destination)
    {
        //AreaLogLatResponse
        String urlString = String.format("json?query=%s&key=%s",destination,getString(R.string.place_api_key));
        Call<AreaLogLatResponse> areaLogLatResponseCall = areaApi.getAllResponse(urlString);
        areaLogLatResponseCall.enqueue(new Callback<AreaLogLatResponse>() {
            @Override
            public void onResponse(Call<AreaLogLatResponse> call, Response<AreaLogLatResponse> response) {
                AreaLogLatResponse areaLogLatResponse = response.body();
                final List<Result> results = areaLogLatResponse.getResults();
                Double lati=results.get(0).getGeometry().getLocation().getLat();
                Double longi=results.get(0).getGeometry().getLocation().getLng();
                loginSharedPreference.saveDestinationLotiLongi(String.valueOf(lati),String.valueOf(longi));
                Toast.makeText(getActivity(), ""+lati, Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onFailure(Call<AreaLogLatResponse> call, Throwable t) {

            }
        });
    }*/

}
