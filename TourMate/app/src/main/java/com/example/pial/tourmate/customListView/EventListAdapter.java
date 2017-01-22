package com.example.pial.tourmate.customListView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pial.tourmate.R;
import com.example.pial.tourmate.database.Event;

import java.util.ArrayList;

/**
 * Created by Pial on 25-Nov-16.
 */

public class EventListAdapter extends ArrayAdapter {
    ArrayList<Event> eventList;
    private Context context;


    public EventListAdapter(Context context, ArrayList<Event> eventList) {
        super(context, R.layout.row_event_list, eventList);
        this.context = context;
        this.eventList = eventList;
    }

    private static class ViewHolder {
        TextView destinationTV,fromView,toView,fromDateTV,toDateTV,estView,estAmountTV,estTakaView,expenseView,expenseTv,expTakaView;
        ProgressBar progressBar;



    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_event_list, parent, false);

            viewHolder.destinationTV= (TextView) view.findViewById(R.id.destinationTV);
            viewHolder.fromView= (TextView) view.findViewById(R.id.fromView);
            viewHolder.toView= (TextView) view.findViewById(R.id.toView);
            viewHolder.fromDateTV= (TextView) view.findViewById(R.id.fromDateTV);
            viewHolder.toDateTV= (TextView) view.findViewById(R.id.toDateTV);
            viewHolder.estView= (TextView) view.findViewById(R.id.estView);
            viewHolder.estAmountTV= (TextView) view.findViewById(R.id.estAmountTV);
            viewHolder.estTakaView= (TextView) view.findViewById(R.id.estTakaView);
            viewHolder.expenseView= (TextView) view.findViewById(R.id.expenseView);
            viewHolder.expenseTv= (TextView) view.findViewById(R.id.expenseTv);
            viewHolder.expTakaView= (TextView) view.findViewById(R.id.expTakaView);
            viewHolder.progressBar= (ProgressBar) view.findViewById(R.id.progressBar1);


            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (EventListAdapter.ViewHolder) view.getTag();
        }

        viewHolder.destinationTV.setText(eventList.get(position).getEventDestination());
        viewHolder.fromDateTV.setText(eventList.get(position).getFromDate());
        viewHolder.toDateTV.setText(eventList.get(position).getToDate());
        viewHolder.estAmountTV.setText(eventList.get(position).getEventBudget());
        viewHolder.progressBar.setMax(Integer.parseInt(eventList.get(position).getEventBudget()));
        viewHolder.progressBar.setProgress(Integer.parseInt(eventList.get(position).getExpense()));
        viewHolder.expenseTv.setText(eventList.get(position).getExpense());


        int expense=Integer.parseInt(eventList.get(position).getExpense());
        int budet=Integer.parseInt(eventList.get(position).getEventBudget());

        double percentage=(expense*100)/budet;
        if (percentage>=80)
        {
            viewHolder.progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }




        return view;

    }
}
