package com.example.david.sinfapplication.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class Utils
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
        else
        if (nif == 11 - remainder_11)
            return true;
        else
            return false;
    }

}
