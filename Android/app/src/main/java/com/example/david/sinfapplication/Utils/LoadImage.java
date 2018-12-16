package com.example.david.sinfapplication.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImage extends AsyncTask {

    String url = null;

    public LoadImage(String url)
    {
        this.url = url;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        //load product image from server
        Bitmap bmp = null;
        try {
            URL url = new URL(this.url);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }
}
