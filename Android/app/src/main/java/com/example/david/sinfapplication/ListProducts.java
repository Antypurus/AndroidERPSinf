package com.example.david.sinfapplication;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ListProducts
{
    private static byte[] queryBytes = ("SELECT A.Artigo, A.Descricao, A.Observacoes, AM.PVP1, AM.PVP2, AM.PVP3, AM.PVP4, AM.PVP5, " +
            "AM.PVP6, AM.Moeda from Artigo A INNER JOIN ArtigoMoeda AM ON A.Artigo = AM.Artigo").getBytes();
    private ArrayList<Product> products;

    public ListProducts() throws InterruptedException, ExecutionException, TimeoutException,
            JSONException
    {
        String listProductsRequestResponse = PrimaveraWebAPI.sendRequest(Route.ListProducts, Method.ListProducts, queryBytes);
        parseListProductsRequestResponse(listProductsRequestResponse);
    }

    private void parseListProductsRequestResponse(String listProductsRequestResponse) throws
            JSONException
    {
        JSONObject dataSetObject = new JSONObject(listProductsRequestResponse);
        JSONArray productsArray = dataSetObject.getJSONArray("Table");
        for(int i = 0; i < productsArray.length(); i++)
        {
            JSONObject productObject = productsArray.getJSONObject(i);
            String id = productObject.getString("Artigo");
            String description = productObject.getString("Descricao");
            String observations = productObject.getString("Observacoes");
            int pvp1 = productObject.getInt("PVP1");
            int pvp2 = productObject.getInt("PVP2");
            int pvp3 = productObject.getInt("PVP3");
            int pvp4 = productObject.getInt("PVP4");
            int pvp5 = productObject.getInt("PVP5");
            int pvp6 = productObject.getInt("PVP6");
            String currency = productObject.getString("Moeda");

            Product product = new Product(id, description, observations, pvp1, pvp2, pvp3, pvp4, pvp5, pvp6, currency);
            products.add(product);
        }

    }
}
