package com.example.pial.tourmate.dataEntryTablayout;

/**
 * Created by fuadr on 11/29/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.pial.tourmate.R;
import com.example.pial.tourmate.Weather.Datum__;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Callback;

public class ForecastAdapter extends BaseAdapter {

    private Context context;
    private List<Datum__> forecastArrayList;
    private LayoutInflater layoutInflater;


    public ForecastAdapter(Context context, List<Datum__> forecastArrayList) {
        this.context = context;
        this.forecastArrayList = forecastArrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return forecastArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return forecastArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View viewRow = view;
        if (view == null) {
            viewRow = layoutInflater.inflate(R.layout.weather_row_list, null);
            viewHolder = new ViewHolder();

            viewHolder.weatherDateTV = (TextView) viewRow.findViewById(R.id.weatherDateTV);
            //viewHolder.conditionImageView = (ImageView) viewRow.findViewById(R.id.conditionImageView);
            viewHolder.weatherDayTV = (TextView) viewRow.findViewById(R.id.weatherDayTV);
            viewHolder.weatherDegTV = (TextView) viewRow.findViewById(R.id.weatherDegTV);
            viewHolder.weatherDetailsTV = (TextView) viewRow.findViewById(R.id.weatherDetailsTV);

            viewRow.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        /*DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(forecastArrayList.get(i).getTime());
        TimeZone tz = TimeZone.getDefault();
        sdf.setTimeZone(tz);*/

        int f = forecastArrayList.get(i).getTemperatureMax().intValue();
        int g = forecastArrayList.get(i).getTemperatureMin().intValue();
                //Double f = info.get(1).getTemperatureMax();
                int maxTemp =  ((f - 32) * 5) / 9;
                int minTemp =  ((g - 32) * 5) / 9;

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM d, yyyy ");
        String dateString = formatter.format(new Date(forecastArrayList.get(i).getTime() * 1000L));

        viewHolder.weatherDateTV.setText(""+dateString);
        viewHolder.weatherDetailsTV.setText(forecastArrayList.get(i).getSummary().toString());

        viewHolder.weatherDayTV.setText("Humidity: "+forecastArrayList.get(i).getHumidity().toString());
        viewHolder.weatherDegTV.setText("Max Temperature: " + maxTemp + " \n " +
                "Min Temperature: "+minTemp);



        return viewRow;
    }

    public class ViewHolder {
        private TextView weatherDateTV;
       // private ImageView conditionImageView;
        private TextView weatherDayTV;
        private TextView weatherDegTV;
        private TextView weatherDetailsTV;

    }
}
