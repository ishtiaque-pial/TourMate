package com.example.pial.tourmate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by Pial on 21-Feb-17.
 */

public class LoadImage extends AsyncTask<Object, Void, Bitmap> {

    private Context context;
    private String path;
    private ImageView imageView;
    public LoadImage(Context context,ImageView imageView,String path)
    {
        this.context=context;
        this.imageView=imageView;
        this.path=path;

    }

    @Override
    protected Bitmap doInBackground(Object... params) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(path);

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
