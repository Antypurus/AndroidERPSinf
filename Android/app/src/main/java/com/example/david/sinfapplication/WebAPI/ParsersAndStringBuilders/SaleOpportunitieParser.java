package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.CommonDataClasses.CommonStorage;
import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitie;

import org.json.JSONException;
import org.json.JSONObject;

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

    public static SaleOpportunitie createSaleOpportunitieRequestBody(String description, String creationDate, String expirationDate, String entity)
    {
        return new SaleOpportunitie(description, creationDate, expirationDate, description, entity, "C",
                "0", "EUR", CommonStorage.vender_id, "CV1");
    }

    public static boolean parseCreateProposalForSaleOpportunitieRequestResponse(String createSaleOpportunitieRequestResponse)
    {
        if(createSaleOpportunitieRequestResponse == null)
            return false;

        if(createSaleOpportunitieRequestResponse.equals("")) //TODO maybe seja true
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

    public static String transformSaleOpportunitieProposalRequestBody(String saleOpportunitieCode, int numberProposalToAccept) throws
            JSONException
    {
        JSONObject requestBody = new JSONObject();
        requestBody.put("CodigoOPV", saleOpportunitieCode);
        requestBody.put("NumeroPropostaOPV", numberProposalToAccept);

        return requestBody.toString();
    }
}
