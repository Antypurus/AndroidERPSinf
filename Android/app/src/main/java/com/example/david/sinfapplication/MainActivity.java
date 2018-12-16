package com.example.david.sinfapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.david.sinfapplication.Activities.Login.login_activity;
import java.util.LinkedHashMap;
import java.util.Map;

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

        Intent intent = new Intent(this, login_activity.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}