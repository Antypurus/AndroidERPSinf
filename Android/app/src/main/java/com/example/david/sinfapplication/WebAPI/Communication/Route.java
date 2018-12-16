package com.example.david.sinfapplication.WebAPI.Communication;

public class Route
{
    //Server
    private static final String serverHostName = "dservers.ddns.net";
    private static final String primaveraServerPort = "2018";
    private static final String pythonServerPort = "5000";

    //Primavera Web API
    private static String PrimaveraWebAPIDefaultRoute = "http://" + serverHostName + ":" + primaveraServerPort + "/WebApi/";
    private static String PrimaveraWebAPIRawQuery = "Administrador/Consulta";

    //Python Web API
    private static String PythonWebAPIDefaultRoute = "http://" + serverHostName + ":" + pythonServerPort + "/";
    //private static String PythonAPIRawQuery = "Administrador/Consulta";

    //PrimaveraAuthentication
    public static String PrimaveraAuthentication = PrimaveraWebAPIDefaultRoute + "token";
    public static String PythonAuthentication = PythonWebAPIDefaultRoute + "login";

    //Products
    public static String ListProducts = PrimaveraWebAPIDefaultRoute + PrimaveraWebAPIRawQuery;
    public static String viewProduct = PrimaveraWebAPIDefaultRoute + "Base/Artigos/Edita/";

    //Customers
    public static String viewCustomer = PrimaveraWebAPIDefaultRoute + "Base/Clientes/Edita/";
    public static String listCustomersOfASalesman = PrimaveraWebAPIDefaultRoute + PrimaveraWebAPIRawQuery;
    public static String addCostumer = PrimaveraWebAPIDefaultRoute + "Base/Clientes/Actualiza";
    public static String editCostumer = PrimaveraWebAPIDefaultRoute + "Base/Clientes/Actualiza/";

    //Documents
    public static String createDocument = PrimaveraWebAPIDefaultRoute + "Vendas/Docs/CreateDocument/";
    public static String viewDocument = PrimaveraWebAPIDefaultRoute + PrimaveraWebAPIRawQuery;
    public static String viewCustomerDocuments = PrimaveraWebAPIDefaultRoute + PrimaveraWebAPIRawQuery;

    //Sales Opportunities
    public static String createSaleOpportunitie = PrimaveraWebAPIDefaultRoute + "CRM/OportunidadesVenda/Actualiza/\"\"";
    public static String createProposalForSaleOpportunitie = PrimaveraWebAPIDefaultRoute + "CRM/PropostasOPV/Actualiza/";
    public static String transformSaleOpportunitie = PrimaveraWebAPIDefaultRoute + "CRM/PropostasOPV/GenerateDocument/V/{documentType}/A/";

}
