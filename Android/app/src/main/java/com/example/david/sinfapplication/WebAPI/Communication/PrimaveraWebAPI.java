package com.example.david.sinfapplication.WebAPI.Communication;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PrimaveraWebAPI
{
    private static final int requestTimeoutMilis = 20000;
    private static AuthenticationToken authenticationToken;

    public static void login(String username, String password, String company, String instance, String grant_type, String line)
            throws UnsupportedEncodingException
    {
        authenticationToken = new AuthenticationToken(new PrimaveraWebAPI(), username, password, company, instance, grant_type, line);
    }

    public String makeLoginRequest(final String urlString, final byte[] bodyContent) throws
            InterruptedException, ExecutionException, TimeoutException
    {
        AsyncTask asyncTask = new AsyncTask()
        {
            @Override
            protected Object doInBackground(Object[] objects)
            {
                return sendAndReceiveResponseGeneric(urlString, RequestMethod.Authentication, bodyContent, ContentType.UrlEncoded, false);
            }
        };
        asyncTask.execute(new String[1]);
        Object requestResponse = asyncTask.get(requestTimeoutMilis, TimeUnit.MILLISECONDS);

        return (String) requestResponse;
    }

    public static String sendRequest(final String urlString, final String method, final String contentType, final byte[] bodyContent) throws
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
                return sendAndReceiveResponseGeneric(urlString, method, bodyContent, contentType,true);
            }
        };
        asyncTask.execute(new Object());
        Object serverResponse = asyncTask.get(requestTimeoutMilis, TimeUnit.MILLISECONDS);

        return (String) serverResponse;
    }

    private static String sendAndReceiveResponseGeneric(String urlString, String method, byte[] bodyContent,
                                                        String contentType, boolean authenticationTokenRequired)
    {
        HttpURLConnection urlConnection = null;
        try
        {
            URL url = new URL(urlString);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(method);

            urlConnection.setRequestProperty("Content-Type", contentType);
            if (authenticationTokenRequired)
                urlConnection.setRequestProperty("Authorization", "Bearer " + authenticationToken.get());
            urlConnection.setRequestProperty("Content-Length", String.valueOf(bodyContent.length));

            if(method == "POST")
            {
                urlConnection.setDoOutput(true);
                urlConnection.getOutputStream().write(bodyContent);
            }

            InputStream errorStream = urlConnection.getErrorStream();
            if(errorStream != null) //error detected, server had responded with error code
            {
                byte[] bytes = new byte[5000];
                int readBytes = errorStream.read(bytes);
                String errorString = new String(bytes, 0, readBytes, Charset.defaultCharset());
                Log.e("Http return error code", errorString);
                return null;
            }

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