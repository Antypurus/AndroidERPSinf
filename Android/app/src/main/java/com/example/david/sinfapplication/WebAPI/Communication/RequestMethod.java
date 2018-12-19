package com.example.david.sinfapplication.WebAPI.Communication;

public class RequestMethod
{
    //Methods
    private static String GET = "GET";
    private static String POST = "POST";

    //Primavera API
    private static String PrimaveraWebAPIRawQueryMethod = POST;

    public static String Authentication = POST;

    //Customers
    public static String ViewCustomer = GET;
    public static String listCustomersOfASalesman = POST;
    public static String AddCustomer = POST;
    public static String EditCustomer = POST;

    //Products
    public static String ListProducts = PrimaveraWebAPIRawQueryMethod;
    public static String ViewProduct = GET;

    //Documents
    public static String createDocument = POST;
    public static String ViewDocument = PrimaveraWebAPIRawQueryMethod;

    //Sales Opportunities
    public static String createSaleOpportunitie = POST;
    public static String createProposalForSaleOpportunitie = POST;
    public static String transformSaleOpportunitie = POST;
    public static String getSalesOportunity = POST;
    public static String getMaxIdOfProposalThatBelongToSaleOpportunity = POST;
    public static String getSalesOpportunitiesOfCustomer = POST;
    public static String getAllProposalsOfASalesOpportunity = POST;
    public static String getProposalDetails = POST;
}
