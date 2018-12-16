package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.WebAPI.Communication.PirmaveraAuthenticationCredentials;

import org.json.JSONException;
import org.json.JSONObject;

public class PythonLoginResponseParser
{
    public static PirmaveraAuthenticationCredentials parsePythonLoginResponse(String pythonLoginResponse)
    {
        JSONObject dataSetObject = null;
        try
        {
            dataSetObject = new JSONObject(pythonLoginResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        try
        {
            String username = dataSetObject.getString("username");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
