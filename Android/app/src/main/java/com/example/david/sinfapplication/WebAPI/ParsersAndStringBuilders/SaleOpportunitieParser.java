package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.CommonDataClasses.CommonStorage;
import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitie;
import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitieProposal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    public static String parseGetSaleOportunityResponse(String response, String attribNameToRetrieve) throws JSONException
    {
        if(response == null)
            return null;

        JSONArray array = new JSONArray(response);
        if(array.length() == 0)
            return null;

        String attribValue = array.getJSONObject(0).getString(attribNameToRetrieve);
        return attribValue;
    }

    public static Integer parseMaxIdOfProposalThatBelongToSaleOpportunity(String maxIdOfProposalThatBelongToSaleOpportunityRequestResponse) throws JSONException
    {
        if(maxIdOfProposalThatBelongToSaleOpportunityRequestResponse == null)
            return null;

        JSONObject dataSetObject = new JSONObject(maxIdOfProposalThatBelongToSaleOpportunityRequestResponse).getJSONObject("DataSet");
        JSONArray proposalsArray = dataSetObject.getJSONArray("Table");
        Integer maxIdOfProposal = null;
        for(int i = 0; i < proposalsArray.length(); i++)
        {
            JSONObject productObject = proposalsArray.getJSONObject(i);
            maxIdOfProposal = productObject.getInt("NumProposta");
        }

        return maxIdOfProposal;
    }

    public static ArrayList<SaleOpportunitie>  parseGetSalesOpportunitiesOfCustomer(String getAllProposalsOfASalesOpportunityResponse) throws JSONException
    {
        if(getAllProposalsOfASalesOpportunityResponse == null)
            return null;

        JSONObject dataSetObject = new JSONObject(getAllProposalsOfASalesOpportunityResponse).getJSONObject("DataSet");
        JSONArray proposalsArray = dataSetObject.getJSONArray("Table");
        ArrayList<SaleOpportunitie> salesOpportunities = new ArrayList<>();
        for(int i = 0; i < proposalsArray.length(); i++)
        {
            JSONObject productObject = proposalsArray.getJSONObject(i);
            String opportunitieId = productObject.getString("ID");
            String opportunitieNumber = productObject.getString("Oportunidade");
            String description = productObject.getString("Descricao");
            String creationDate = productObject.getString("DataCriacao");
            String expirationDate = productObject.getString("DataExpiracao");
            String resume = productObject.getString("Resumo");
            String entity = productObject.getString("Entidade");
            String entityType = productObject.getString("TipoEntidade");
            String saleState = productObject.getString("EstadoVenda");
            String salesman = productObject.getString("Vendedor");
            String saleCycle = productObject.getString("CicloVenda");

            SaleOpportunitie saleOpportunitie = new SaleOpportunitie(opportunitieId, opportunitieNumber,
                    description, creationDate, expirationDate, resume, entity, entity, entityType,
                    saleState, salesman, saleCycle);

            salesOpportunities.add(saleOpportunitie);
        }

        return salesOpportunities;
    }

    public static ArrayList<SaleOpportunitieProposal> parseGetAllProposalsOfASalesOpportunity(String salesOpportunityResponse, SaleOpportunitie saleOpportunitie) throws JSONException
    {
        if(salesOpportunityResponse == null)
            return null;

        JSONObject dataSetObject = new JSONObject(salesOpportunityResponse).getJSONObject("DataSet");
        JSONArray proposalsArray = dataSetObject.getJSONArray("Table");
        ArrayList<SaleOpportunitieProposal> salesOpportunities = new ArrayList<>();
        for(int i = 0; i < proposalsArray.length(); i++)
        {
            JSONObject productObject = proposalsArray.getJSONObject(i);
            Integer value = productObject.getInt("Valor");

            SaleOpportunitieProposal saleOpportunitieProposal = new SaleOpportunitieProposal(saleOpportunitie, value, null);

            salesOpportunities.add(saleOpportunitieProposal);
        }

        return salesOpportunities;
    }

    public static void parseGetDetailsOfProposal(String getAllProposalsOfASalesOpportunityResponse, SaleOpportunitieProposal saleOpportunitieProposal) throws JSONException
    {
        if(getAllProposalsOfASalesOpportunityResponse == null)
            return;

        JSONObject dataSetObject = new JSONObject(getAllProposalsOfASalesOpportunityResponse).getJSONObject("DataSet");
        JSONArray proposalsArray = dataSetObject.getJSONArray("Table");
        ArrayList<SaleOpportunitieProposal> salesOpportunities = new ArrayList<>();
        for(int i = 0; i < proposalsArray.length(); i++)
        {
            JSONObject productObject = proposalsArray.getJSONObject(i);
            Integer value = productObject.getInt("Valor");

            //           SaleOpportunitieProposal saleOpportunitieProposal = new SaleOpportunitieProposal(saleOpportunitie, value, null);

            salesOpportunities.add(saleOpportunitieProposal);
        }
    }
}
