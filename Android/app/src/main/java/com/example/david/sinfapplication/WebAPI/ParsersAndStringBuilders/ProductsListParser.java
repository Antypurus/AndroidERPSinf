package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.CommonDataClasses.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ProductsListParser
{
    public static ArrayList<Product> parseListProductsRequestResponse(String listProductsRequestResponse) throws
            JSONException, InterruptedException, ExecutionException, TimeoutException
    {
        ArrayList<Product> products = new ArrayList<>();
        JSONObject dataSetObject = new JSONObject(listProductsRequestResponse).getJSONObject("DataSet");
        JSONArray productsArray = dataSetObject.getJSONArray("Table");
        for(int i = 0; i < productsArray.length(); i++)
        {
            JSONObject productObject = productsArray.getJSONObject(i);
            String id = productObject.getString("Artigo");
            String family = productObject.getString("Familia");
            String subfamily = productObject.getString("SubFamilia");
            String description = productObject.getString("Descricao");
            String observations = productObject.getString("Observacoes");
            String imagePath = productObject.getString("CDU_CampoVar1");
            int stockAtual = productObject.getInt("StkActual");
            int pvp = productObject.getInt("PVP1");
            String currency = productObject.getString("Moeda");

            Product product = new Product(id, family, subfamily, description, imagePath, observations, stockAtual, pvp, currency);
            products.add(product);
        }

        return products;

    }
}
