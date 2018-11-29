package com.example.david.sinfapplication;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final Map<String,Object> parametersMap = new LinkedHashMap<>();
        parametersMap.put("username", "FEUP");
        parametersMap.put("password", "qualquer1");
        parametersMap.put("company", "BELAFLOR");
        parametersMap.put("instance", "DEFAULT");
        parametersMap.put("grant_type", "password");
        parametersMap.put("line", "professional");
        try
        {
            byte[] parametersByteArray = Utils.getBytesOfHTTPParametersToSend(parametersMap);
            PrimaveraWebAPI.login("http://dservers.ddns.net:2018/WebApi/token", parametersByteArray);
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (TimeoutException e)
        {
            e.printStackTrace();
        }



        setContentView(R.layout.create_customer);
        ProgressBar bar = (ProgressBar) findViewById(R.id.creation_progress);
        bar.getProgressDrawable().mutate().setColorFilter(Color.RED,PorterDuff.Mode.SRC_IN);
    }
}