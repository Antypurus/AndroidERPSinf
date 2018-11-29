package com.example.david.sinfapplication;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PrimaveraWebAPI
{
    private static final int requestTimeoutMilis = 20000;
    private static AuthenticationToken authenticationToken;

    public static void login(String username, String password, String company, String instance, String grant_type, String line) throws UnsupportedEncodingException
    {
        authenticationToken = new AuthenticationToken(username, password, company, instance, grant_type, line);
    }

    protected static String makeLoginRequest(final String urlString, final byte[] bodyContent) throws
            InterruptedException, ExecutionException, TimeoutException
    {
        AsyncTask asyncTask = new AsyncTask()
        {
            @Override
            protected Object doInBackground(Object[] objects)
            {
                return sendAndReceiveResponseGeneric(urlString, "POST", bodyContent, false);
            }
        };
        asyncTask.execute(new String[1]);
        Object requestResponse = asyncTask.get(requestTimeoutMilis, TimeUnit.MILLISECONDS);

        return (String) requestResponse;
    }

    public static String sendRequest(final String urlString, final String method, final byte[] bodyContent) throws
            InterruptedException, ExecutionException, TimeoutException
    {
        //check have logged in first
        if(authenticationToken == null)
            return null;

        AsyncTask asyncTask = new AsyncTask()
        {
            @Override
            protected Object doInBackground(Object[] objects)
            {
                return sendAndReceiveResponseGeneric(urlString, method, bodyContent, true);
            }
        };
        Object serverResponse = asyncTask.get(20000, TimeUnit.MILLISECONDS);

        return (String) serverResponse;
    }

    private static String sendAndReceiveResponseGeneric(String urlString, String method, byte[] bodyContent,
                                                        boolean authenticationTokenRequired)
    {
        HttpURLConnection urlConnection = null;
        try
        {
            URL url = new URL(urlString);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(method);

            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (authenticationTokenRequired)
                urlConnection.setRequestProperty("Authorization", "Bearer " + authenticationToken.get());
            urlConnection.setRequestProperty("Content-Length", String.valueOf(bodyContent.length));

            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(bodyContent);

            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null)
            {
                result.append(line);
            }
            rd.close();

            return result.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            urlConnection.disconnect();
        }
    }
}