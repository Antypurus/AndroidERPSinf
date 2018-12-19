package com.example.david.sinfapplication.WebAPI.Communication;

public class Route
{
    //Server
    private static final String primaveraServerHostName = "dservers.ddns.net";
    private static final String primaveraRedundantServerHostName = "dcameras.ddns.net";
    private static final String pythonServerHostName = "dservers.ddns.net";
    private static final String primaveraServerPort = "2018";
    private static final String pythonServerPort = "5000";

    //Primavera Web API
    private static String PrimaveraWebAPIDefaultRoute = "http://" + primaveraServerHostName + ":" + primaveraServerPort + "/WebApi/";
    private static String PrimaveraWebAPIRawQuery = "Administrador/Consulta";

    //Python Web API
    private static String PythonWebAPIDefaultRoute = "http://" + pythonServerHostName + ":" + pythonServerPort + "/";
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
    public static String getSalesOportunity = PrimaveraWebAPIDefaultRoute + "CRM/OportunidadesVenda/DaValorAtributos/";
    public static String getMaxIdOfProposalThatBelongToSaleOpportunity = PrimaveraWebAPIDefaultRoute + PrimaveraWebAPIRawQuery;
    public static String getSalesOpportunitiesOfCustomer = PrimaveraWebAPIDefaultRoute + PrimaveraWebAPIRawQuery;
    public static String getAllProposalsOfASalesOpportunity = PrimaveraWebAPIDefaultRoute + PrimaveraWebAPIRawQuery;
    public static String getProposalDetailsPart1 = PrimaveraWebAPIDefaultRoute + "CRM/PropostasOPV/Edita/d58cac54-03af-11e9-8dd0-080027266259/";
    public static String getProposalDetailsPart2 = PrimaveraWebAPIDefaultRoute + "/5/true";

    //AgendaEntry
    public static String listAgendaEntries = PrimaveraWebAPIDefaultRoute + PrimaveraWebAPIRawQuery;
    public static String setAgendaEntryNotes = PrimaveraWebAPIDefaultRoute + PrimaveraWebAPIRawQuery;

}
