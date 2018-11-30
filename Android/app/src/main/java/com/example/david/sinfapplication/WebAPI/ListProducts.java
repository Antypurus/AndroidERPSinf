package com.example.david.sinfapplication.WebAPI;


import com.example.david.sinfapplication.ContentType;
import com.example.david.sinfapplication.Method;
import com.example.david.sinfapplication.Product;
import com.example.david.sinfapplication.Route;
import com.example.david.sinfapplication.WebAPI.Communication.PrimaveraWebAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ListProducts
{
    private static byte[] queryBytes = ("\""+ "SELECT A.Artigo, A.Descricao, A.Observacoes, A.StkActual, AM.PVP1, AM.PVP2, AM.PVP3, AM.PVP4, AM.PVP5, " +
            "AM.PVP6, AM.Moeda from Artigo A INNER JOIN ArtigoMoeda AM ON A.Artigo = AM.Artigo" + "\"").getBytes();
    private ArrayList<Product> products = new ArrayList<>();

    public ListProducts() throws InterruptedException, ExecutionException, TimeoutException,
            JSONException
    {
        String listProductsRequestResponse = PrimaveraWebAPI.sendRequest(Route.ListProducts, Method.ListProducts, ContentType.ApplicationJson, queryBytes);
        parseListProductsRequestResponse(listProductsRequestResponse);
    }

    private void parseListProductsRequestResponse(String listProductsRequestResponse) throws
            JSONException
    {
        JSONObject dataSetObject = new JSONObject(listProductsRequestResponse).getJSONObject("DataSet");
        JSONArray productsArray = dataSetObject.getJSONArray("Table");
        for(int i = 0; i < productsArray.length(); i++)
        {
            JSONObject productObject = productsArray.getJSONObject(i);
            String id = productObject.getString("Artigo");
            String description = productObject.getString("Descricao");
            String observations = productObject.getString("Observacoes");
            int stockAtual = productObject.getInt("StkActual");
            int pvp = productObject.getInt("PVP1");
            String currency = productObject.getString("Moeda");

            Product product = new Product(id, description, observations, stockAtual, pvp, currency);
            products.add(product);
        }

    }
}
