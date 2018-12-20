package com.example.david.sinfapplication.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.david.sinfapplication.WebAPI.Communication.ContentType;
import com.example.david.sinfapplication.WebAPI.Communication.RequestMethod;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LoadImage
{
    public static Object doInBackground(final String urlToRetrieveFrom) throws InterruptedException, ExecutionException, TimeoutException
    {
        AsyncTask asyncTask = new AsyncTask()
        {
            @Override
            protected Object doInBackground(Object[] objects)
            {
                //load product image from server
                Bitmap bmp = null;
                try
                {
                    URL url = new URL(urlToRetrieveFrom);
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (MalformedURLException e)
                {
                    e.printStackTrace();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                System.out.println("image loaded");
                return bmp;
            }
        };
        asyncTask.execute(new String[1]);
        Object requestResponse = asyncTask.get(5000, TimeUnit.MILLISECONDS);
        System.out.println("finished waiting for image load");

        return requestResponse;
    }
}
