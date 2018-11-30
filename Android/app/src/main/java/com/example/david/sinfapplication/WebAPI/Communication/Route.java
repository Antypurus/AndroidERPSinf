package com.example.david.sinfapplication.WebAPI.Communication;

public class Route
{
    public static final String serverHostName = "dservers.ddns.net";
    public static final String serverPort = "2018";
    private static String PrimaveraWebAPIDefaultRoute = "http://" + serverHostName + ":" + serverPort + "/WebApi/";
    private static String PrimaveraWebAPIDirectQuery = "Administrador/Consulta";
    public static String Authentication = PrimaveraWebAPIDefaultRoute + "token";
    public static String ListProducts = PrimaveraWebAPIDefaultRoute + PrimaveraWebAPIDirectQuery;
    public static String viewCustomer = PrimaveraWebAPIDefaultRoute + "Base/Clientes/Edita/C0001";

}
