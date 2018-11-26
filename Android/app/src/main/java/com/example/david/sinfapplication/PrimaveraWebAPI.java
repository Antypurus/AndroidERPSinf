package com.example.david.sinfapplication;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PrimaveraWebAPI
{
    private static final int requestTimeoutMilis = 20000;
    private static AuthenticationToken authenticationToken;


    public static void login(final String urlString, final Map<String, Object> requestParamaters) throws
            InterruptedException, ExecutionException, TimeoutException
    {
        AsyncTask asyncTask = new AsyncTask()
        {
            @Override
            protected Object doInBackground(Object[] objects)
            {
                return sendAndReceiveResponseGeneric(urlString, "POST", requestParamaters, false);
            }
        };
        asyncTask.execute(new String[1]);
       Object requestResponse = asyncTask.get(requestTimeoutMilis, TimeUnit.MILLISECONDS);

       String stringRequestResponse = (String) requestResponse;
        //TODO: PARSE JSON
    }

    public static String sendRequest(final String urlString, final String method, final Map<String, Object> requestParamaters) throws
            InterruptedException, ExecutionException, TimeoutException
    {
        AsyncTask asyncTask = new AsyncTask()
        {
            @Override
            protected Object doInBackground(Object[] objects)
            {
                return sendAndReceiveResponseGeneric(urlString, method, requestParamaters, true);
            }
        };
        Object serverResponse = asyncTask.get(20000, TimeUnit.MILLISECONDS);

        return (String) serverResponse;
    }

    private static String sendAndReceiveResponseGeneric(String urlString, String method, Map<String, Object> requestParamaters,
                                                 boolean authenticationTokenRequired)
    {
        HttpURLConnection urlConnection = null;
        try
        {
            URL url = new URL(urlString);
            byte[] postDataBytes = getBytesOfHTTPParametersToSend(requestParamaters);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(method);

            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if(authenticationTokenRequired)
                urlConnection.setRequestProperty("Authorization", "Bearer " + authenticationToken.get());
            urlConnection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));

            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(postDataBytes);

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

    private static byte[] getBytesOfHTTPParametersToSend(Map<String, Object> params) throws UnsupportedEncodingException
    {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet())
        {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        return postData.toString().getBytes("UTF-8");
    }

}