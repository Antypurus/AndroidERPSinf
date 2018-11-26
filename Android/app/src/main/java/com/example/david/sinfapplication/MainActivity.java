package com.example.david.sinfapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
            PrimaveraWebAPI.login("http://dservers.ddns.net:2018/WebApi/token", parametersMap);
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
    }
}