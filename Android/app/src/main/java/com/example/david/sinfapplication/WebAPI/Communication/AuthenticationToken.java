package com.example.david.sinfapplication.WebAPI.Communication;

import com.example.david.sinfapplication.Utils.UtilsClass;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

class AuthenticationToken
{
    private PrimaveraWebAPI primaveraWebAPI;
    private String authenticationToken = null;
    private long lastGeneratedTokenTimeMilis = 0;
    private byte[] authenticationRequestParamatersBytes;
    private static final long tokenExpirationTimeMilis = 15 * 60 * 1000; //15 minutes


    public AuthenticationToken(PrimaveraWebAPI primaveraWebAPI, String username, String password, String company, String instance,
                               String grant_type, String line) throws Exception
    {
        this.primaveraWebAPI = primaveraWebAPI;
        Map<String, Object> authenticationRequestParamaters = new LinkedHashMap<>();
        authenticationRequestParamaters.put("username", username);
        authenticationRequestParamaters.put("password", password);
        authenticationRequestParamaters.put("company", company);
        authenticationRequestParamaters.put("instance", instance);
        authenticationRequestParamaters.put("grant_type", grant_type);
        authenticationRequestParamaters.put("line", line);
        authenticationRequestParamatersBytes = UtilsClass.getBytesOfHTTPParametersToSend(authenticationRequestParamaters);

        generate();
    }

    //null is returned if an error occurred
    public String get() throws Exception
    {
        long currentTimeMilis = System.currentTimeMillis();
        boolean isTokenExpired = currentTimeMilis > (lastGeneratedTokenTimeMilis + tokenExpirationTimeMilis);
        if (isTokenExpired || authenticationToken == null)
            generate();

        return authenticationToken;
    }

    private void generate() throws Exception
    {
        String loginRequestResponse  = primaveraWebAPI.makeLoginRequest(Route.PrimaveraAuthentication, authenticationRequestParamatersBytes);
        JSONObject jsonObject = new JSONObject(loginRequestResponse);
        authenticationToken = jsonObject.getString("access_token");

        lastGeneratedTokenTimeMilis = System.currentTimeMillis();
    }

}
