package com.example.david.sinfapplication;

import android.util.Log;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

public class AuthenticationToken
{
    private String authenticationToken = null;
    private long lastGeneratedTokenTimeMilis = 0;
    private byte[] authenticationRequestParamatersBytes;
    private static final long tokenExpirationTimeMilis = 15 * 60 * 1000; //15 minutes


    public AuthenticationToken(String username, String password, String company, String instance, String grant_type, String line) throws
            UnsupportedEncodingException
    {
        Map<String, Object> authenticationRequestParamaters = new LinkedHashMap<>();
        authenticationRequestParamaters.put("username", username);
        authenticationRequestParamaters.put("password", password);
        authenticationRequestParamaters.put("company", company);
        authenticationRequestParamaters.put("instance", instance);
        authenticationRequestParamaters.put("grant_type", grant_type);
        authenticationRequestParamaters.put("line", line);
        authenticationRequestParamatersBytes = Utils.getBytesOfHTTPParametersToSend(authenticationRequestParamaters);

        generate();
    }

    //null is returned if an error occurred
    public String get()
    {
        long currentTimeMilis = System.currentTimeMillis();
        boolean isTokenExpired = (lastGeneratedTokenTimeMilis + tokenExpirationTimeMilis) > currentTimeMilis;
        if (isTokenExpired || authenticationToken == null)
            generate();

        return authenticationToken;
    }

    private void generate()
    {
        try
        {
            String loginRequestResponse  = PrimaveraWebAPI.makeLoginRequest(Route.Authentication, authenticationRequestParamatersBytes);
            JSONObject jsonObject = new JSONObject(loginRequestResponse);
            authenticationToken = jsonObject.getString("access_token");
            Log.d("generate token", authenticationToken);
        } catch (Exception e)
        {
            e.printStackTrace();
            authenticationToken = null;
            return;
        }

        lastGeneratedTokenTimeMilis = System.currentTimeMillis();
    }

}
