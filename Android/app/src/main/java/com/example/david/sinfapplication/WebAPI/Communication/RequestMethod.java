package com.example.david.sinfapplication.WebAPI.Communication;

public class RequestMethod
{
    private static String GET = "GET";
    private static String POST = "POST";
    private static String PrimaveraWebAPIRawQueryMethod = POST;
    public static String Authentication = POST;
    public static String ListProducts = PrimaveraWebAPIRawQueryMethod;
    public static String ViewCustomer = GET;
    public static String AddCustomer = POST;
    public static String EditCustomer = POST;
    public static String ViewProduct = GET;
    public static String ViewDocument = PrimaveraWebAPIRawQueryMethod;
}
