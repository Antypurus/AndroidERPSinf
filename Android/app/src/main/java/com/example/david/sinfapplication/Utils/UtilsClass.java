package com.example.david.sinfapplication.Utils;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class UtilsClass
{
    public static byte[] getBytesOfHTTPParametersToSend(Map<String, Object> params) throws
            UnsupportedEncodingException
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

    public static boolean IsNifValid(String nifString)
    {
        //check length
        if (nifString.length() != 9)
            return false;

        //check first digit
        String first_digit = "1 2 5 6 8 9";
        if (first_digit.contains(String.valueOf(nifString.charAt(0))))
            return false;

        //check mod11
        long nif = Long.parseLong(nifString);
        int curr_number;
        int soma = 0;
        for (int i = 9; i >= 2; i--)
        {
            curr_number = (int) Math.pow(10, i - 1);
            soma += (nif / curr_number) * i;
            nif -= (nif / curr_number) * curr_number;  // subtracts the current number * 10 raised to the power of its position
        }

        int remainder_11 = soma % 11;
        if (remainder_11 == 0)
            if (nif == 0 || nif == 1)
                return true;
            else
                return false;
        else if (nif == 11 - remainder_11)
            return true;
        else
            return false;
    }

    public static void addToJsonObjectIfNotNull(JSONObject jsonObject, String key, String value) throws
            JSONException
    {
        if (value == null)
            value = "";

        jsonObject.put(key, value);
    }

    public static String getSHA256OfStringInHexadecimalEncoding(String str)
    {
        MessageDigest digest = null;
        try
        {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            Log.e("CustomerBasic", "Error computing SHA-256 of CustomerBasic");
        }
        byte hash[] = digest.digest(str.getBytes());
        StringBuilder hashSB = new StringBuilder();
        for (byte b : hash)
            hashSB.append(String.format("%02X", b));

        return hashSB.toString();
    }

    public static String getDateAsString()
    {
        Date date = new Date();
        String strDateFormat = "MM/dd/yyyy";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);

        return formattedDate;
    }

    public static Bitmap loadImageFromServer(String imagePath) throws InterruptedException, ExecutionException,
            TimeoutException
    {
        if(imagePath == null || imagePath.isEmpty() || imagePath.equals("null"))
            return null;

        Bitmap imageObject = (Bitmap) LoadImage.doInBackground(imagePath);
        return imageObject;
    }
}
