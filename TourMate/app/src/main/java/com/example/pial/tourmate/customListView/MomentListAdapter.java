package com.example.pial.tourmate.customListView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pial.tourmate.R;
import com.example.pial.tourmate.database.Expense;
import com.example.pial.tourmate.database.Moment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Pial on 25-Nov-16.
 */

public class MomentListAdapter extends ArrayAdapter {
    ArrayList<Moment> momentList;
    private Context context;
    String path;
    File imgFile;
    Bitmap myBitmap;


    public MomentListAdapter(Context context, ArrayList<Moment> momentList) {
        super(context, R.layout.row_moment_info, momentList);
        this.context = context;
        this.momentList=momentList;

    }

    private static class ViewHolder {
        TextView momentDateTV,momentTimeTV,momentDetailsTV;
        ImageView momentImageViewTV;



    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_moment_info, parent, false);

            viewHolder.momentDateTV= (TextView) view.findViewById(R.id.momentDateTV);
            viewHolder.momentTimeTV= (TextView) view.findViewById(R.id.momentTimeTV);
            viewHolder.momentDetailsTV= (TextView) view.findViewById(R.id.momentDetailsTV);
            viewHolder.momentImageViewTV= (ImageView) view.findViewById(R.id.momentImageViewTV);


            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (MomentListAdapter.ViewHolder) view.getTag();
        }

        viewHolder.momentDateTV.setText(momentList.get(position).getMomentDate());
        viewHolder.momentTimeTV.setText(momentList.get(position).getMomentTime());
        viewHolder.momentDetailsTV.setText(momentList.get(position).getMomentDetails());


            path = momentList.get(position).getMomentPhotoPath();

        try {
            myBitmap = BitmapFactory.decodeFile(path);
            if (myBitmap == null) {

            } else {
                //viewHolder.momentImageViewTV.setImageBitmap(myBitmap);
            }
        }catch (Exception ex)
        {
            Log.e("Error1",""+ex);
        }












        return view;

    }
}
