package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.WebAPI.Communication.PrimaveraAuthenticationCredentials;

import org.json.JSONException;
import org.json.JSONObject;

public class PythonLoginResponseParser
{
    public static PrimaveraAuthenticationCredentials parsePythonLoginResponse(String pythonLoginResponse) throws JSONException
    {
        JSONObject dataSetObject = null;
        try
        {
            dataSetObject = new JSONObject(pythonLoginResponse);
        } catch (JSONException e)
        {
            //Pyhton server returned non-Json content, which means error
            //The error comes in a string format
            //Return null to inform the caller it was an error
            return null;
        }
        String username = dataSetObject.getString("username");
        String password = dataSetObject.getString("password");
        String company = dataSetObject.getString("company");
        String instance = dataSetObject.getString("instance");
        String grant_type = dataSetObject.getString("password");
        String line = dataSetObject.getString("line");

        return new PrimaveraAuthenticationCredentials(username, password, company,
                instance, grant_type, line);
    }
}
