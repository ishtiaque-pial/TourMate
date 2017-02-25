package com.example.pial.tourmate.eventDetailsTablayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pial.tourmate.R;
import com.example.pial.tourmate.activityPackage.LoginSharedPreference;
import com.example.pial.tourmate.customListView.MomentListAdapter;
import com.example.pial.tourmate.database.Expense;
import com.example.pial.tourmate.database.Moment;
import com.example.pial.tourmate.database.MomentManager;

import java.util.ArrayList;

/**
 * Created by Pial on 26-Nov-16.
 */

public class EventMoments extends Fragment {
    ListView momentListView;
    ArrayList<Moment> momentList;
    MomentManager momentManager;
    MomentListAdapter momentListAdapter;
    LoginSharedPreference loginSharedPreference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_moments, container, false);
        momentListView= (ListView) rootView.findViewById(R.id.momentListView);

    try {


        loginSharedPreference = new LoginSharedPreference(getActivity());
        String evnID = loginSharedPreference.getEventKey();

        momentManager = new MomentManager(getActivity());
        momentList = momentManager.getAllMoment(evnID);
        if (momentList.isEmpty()) {

        } else {
            momentListAdapter = new MomentListAdapter(getActivity(), momentList);
            momentListView.setAdapter(momentListAdapter);
        }
    }catch (Exception ex)
    {
        Log.e("Error1",""+ex);
    }

        return rootView;
    }
}
