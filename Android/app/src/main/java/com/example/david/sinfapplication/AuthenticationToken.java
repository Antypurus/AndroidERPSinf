package com.example.david.sinfapplication;

import java.util.LinkedHashMap;
import java.util.Map;

public class AuthenticationToken
{
    private String authenticationToken;
    private long lastGeneratedTokenTimeMilis;
    private Map<String, Object> authenticationRequestParamaters = new LinkedHashMap<>();
    private static final long tokenExpirationTimeMilis = 15 * 60 * 1000; //15 minutes


    public AuthenticationToken(String username, String password, String company, String instance, String grant_type, String line)
    {
        authenticationRequestParamaters.put("username", username);
        authenticationRequestParamaters.put("password", password);
        authenticationRequestParamaters.put("company", company);
        authenticationRequestParamaters.put("instance", instance);
        authenticationRequestParamaters.put("grant_type", grant_type);
        authenticationRequestParamaters.put("line", line);

        generate();
    }

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
        //TODO
     /* byte[] httpParametersToSendBytes = getBytesOfHTTPParametersToSend(authenticationRequestParamaters);
        sendHTTPRequest(url, httpParametersToSendBytes);*/
        //TODO setar o authenticationToken
        lastGeneratedTokenTimeMilis = System.currentTimeMillis();
    }

}
