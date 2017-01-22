package com.example.pial.tourmate.eventTablayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pial.tourmate.R;

/**
 * Created by Pial on 25-Nov-16.
 */

public class PastEvent extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_past, container, false);

        return rootView;
    }
}
