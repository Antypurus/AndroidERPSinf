package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.CommonDataClasses.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductsListParser
{
    public static ArrayList<Product> parseListProductsRequestResponse(String listProductsRequestResponse) throws
            JSONException
    {
        ArrayList<Product> products = new ArrayList<>();
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

        return products;

    }
}
