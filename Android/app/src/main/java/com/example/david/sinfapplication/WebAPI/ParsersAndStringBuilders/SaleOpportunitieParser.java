package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

public class SaleOpportunitieParser
{
    public static boolean parseCreateSaleOpportunitieRequestResponse(String createSaleOpportunitieRequestResponse)
    {
        if(createSaleOpportunitieRequestResponse == null)
            return false;

        if(createSaleOpportunitieRequestResponse.equals(""))
            return true;

        return false;
    }

    public static boolean parseTransformSaleOpportunitieRequestResponse(String transformSaleOpportunitieRequestResponse)
    {
        if(transformSaleOpportunitieRequestResponse == null)
            return false;

        if(transformSaleOpportunitieRequestResponse.equals("")) //TODO maybe seja true
            return true;

        return false;
    }
}
