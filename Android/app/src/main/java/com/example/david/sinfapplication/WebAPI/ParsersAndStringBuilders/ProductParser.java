package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.CommonDataClasses.Product;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductParser
{
    public static Product parseViewProductRequestResponse(String viewProductRequestResponse) throws
            JSONException
    {
        JSONObject dataSetObject = new JSONObject(viewProductRequestResponse);
        String id = dataSetObject.getString("Artigo");
        String family = dataSetObject.getString("Familia");
        String subfamily = dataSetObject.getString("SubFamilia");
        String description = dataSetObject.getString("Descricao");
        String observations = dataSetObject.getString("Observacoes");
        //TODO falta parsar a imagem
        int currentStock = dataSetObject.getInt("StkActual");
        double pvp = dataSetObject.getDouble("PVP1");
        String currency = dataSetObject.getString("Moeda");

        return new Product(id, family, subfamily, description,
                observations, currentStock, pvp, currency);
    }
}
