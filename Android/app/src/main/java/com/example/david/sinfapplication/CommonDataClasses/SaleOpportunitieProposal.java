package com.example.david.sinfapplication.CommonDataClasses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class SaleOpportunitieProposal implements Serializable
{
    private int proposalNumber; //TODO see how to handle it...como saber o numero que deve ser +1 que o anterior
    private SaleOpportunitie saleOpportunitie;
    private Integer value;
    private ArrayList<CartProduct> productsList;


    public SaleOpportunitieProposal(int proposalNumber, SaleOpportunitie saleOpportunitie, Integer value, ArrayList<CartProduct> productsList)
    {
        this.proposalNumber = proposalNumber;
        this.saleOpportunitie = saleOpportunitie;
        this.value = value;
        this.productsList = productsList;
    }

    public SaleOpportunitie getSaleOpportunitie()
    {
        return saleOpportunitie;
    }

    public void setSaleOpportunitie(SaleOpportunitie saleOpportunitie)
    {
        this.saleOpportunitie = saleOpportunitie;
    }

    public ArrayList<CartProduct> getProductsList()
    {
        return productsList;
    }

    public void setProductsList(ArrayList<CartProduct> productsList)
    {
        this.productsList = productsList;
    }

    public String getJson()
    {
        JSONObject requestBody = new JSONObject();
        try
        {
            String opportunitieId = saleOpportunitie.getOpportunitieId();
            JSONArray linesArray = new JSONArray();
            for(int i = 0; i < productsList.size(); i++)
            {
                JSONObject line = new JSONObject();
                CartProduct cartProduct = productsList.get(i);

                String id = cartProduct.getId();
                line.put("Artigo", id);

                int quantity = cartProduct.getQuantity();
                line.put("Quantidade", String.valueOf(quantity));

                int discount = cartProduct.getDiscount();
                line.put("Desconto1", String.valueOf(discount));

                line.put("IdOportunidade", opportunitieId);
                line.put("NumProposta", proposalNumber);

                if(i > 0)
                    line.put("Linha", i);

                linesArray.put(line);
            }
            requestBody.putOpt("Linhas", linesArray);

            requestBody.put("IdOportunidade", opportunitieId);
            requestBody.put("NumProposta", proposalNumber);

            return requestBody.toString();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public int getProposalNumber()
    {
        return this.proposalNumber;
    }
}
