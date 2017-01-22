package com.example.pial.tourmate.customListView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pial.tourmate.NearByLocationInfo.Result;
import com.example.pial.tourmate.R;

import java.util.List;

/**
 * Created by BITM TRAINER-403 on 19/11/2016.
 */

public class PlacesCustomAdapter extends ArrayAdapter {
    private Context context;
    private  List<Result> results;
    public PlacesCustomAdapter(Context context, List<Result> results) {
        super(context, R.layout.places_single_row, results);
        this.context = context;
        this.results = results;
    }

    private static class ViewHolder {


    TextView placeName,placeAddress,placeOpenNow,placeRatingView;
        RatingBar ratingBar;

    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.places_single_row, parent, false);

            viewHolder.placeName= (TextView) view.findViewById(R.id.placeName);
            viewHolder.placeAddress= (TextView) view.findViewById(R.id.placeAddress);
            viewHolder.placeOpenNow= (TextView) view.findViewById(R.id.placeOpenNow);
            viewHolder.placeRatingView= (TextView) view.findViewById(R.id.placeRatingView);
            viewHolder.ratingBar= (RatingBar) view.findViewById(R.id.ratingBar);

            viewHolder.ratingBar.setIsIndicator(true);


            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (PlacesCustomAdapter.ViewHolder) view.getTag();
        }

    try {


        viewHolder.placeName.setText(results.get(position).getName());
        viewHolder.placeAddress.setText(results.get(position).getVicinity());




            double a = results.get(position).getRating();

            viewHolder.ratingBar.setRating((float) a);
        }catch (Exception exx)
        {
            Log.e("Error",""+exx);
            viewHolder.ratingBar.setRating((float) 0);
        }

        try {
        if (results.get(position).getOpeningHours().getOpenNow()==true)
        {
            viewHolder.placeOpenNow.setText("Open Now");
            viewHolder.placeOpenNow.setTextColor(Color.GREEN);
        }
        else if (results.get(position).getOpeningHours().getOpenNow()==false)
        {
            viewHolder.placeOpenNow.setText("Closed");
            viewHolder.placeOpenNow.setTextColor(Color.RED);

        }



    }
    catch (Exception ex)
    {
        Log.e("Error1",""+ex);
        viewHolder.placeOpenNow.setText("Open/close");
    }



        return view;
    }
}
