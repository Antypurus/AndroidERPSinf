package com.example.david.sinfapplication.WebAPI;


import com.example.david.sinfapplication.ContentType;
import com.example.david.sinfapplication.Method;
import com.example.david.sinfapplication.Product;
import com.example.david.sinfapplication.Route;
import com.example.david.sinfapplication.WebAPI.Communication.PrimaveraWebAPI;
import com.example.david.sinfapplication.WebAPI.Parsers.ProductsListParser;

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
        ProductsListParser.parseListProductsRequestResponse(listProductsRequestResponse);
    }


}
