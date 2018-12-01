package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.CommonDataClasses.Customer;
import com.example.david.sinfapplication.CommonDataClasses.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomerParserAndStringBuilder
{

    public static Customer parseViewCustomerRequestResponse(String viewCustomerRequestResponse) throws JSONException
    {
        //TODO
        JSONObject dataSetObject = new JSONObject(viewCustomerRequestResponse).getJSONObject("DataSet");
        JSONArray productsArray = dataSetObject.getJSONArray("Table");
        JSONArray productsArray2 = dataSetObject.getJSONArray("Table");

/*
            JSONObject productObject = productsArray.getJSONObject(i);
            String id = productObject.getString("Artigo");
            String description = productObject.getString("Descricao");
            String observations = productObject.getString("Observacoes");
            int stockAtual = productObject.getInt("StkActual");
            int pvp = productObject.getInt("PVP1");
            String currency = productObject.getString("Moeda");

            Product product = new Product(id, description, observations, stockAtual, pvp, currency);
            products.add(product);

        return products;
*/
        return new Customer("ldfjls", "---fjsdlkfs", null, null,
                null, null, null, null, null, null, null,
                null, null,  null, null, null);
    }

    public static boolean parseAddCustomerRequestResponse(String addCustomerRequestResponse)
    {
        if(addCustomerRequestResponse.isEmpty())
            return true;
        else
            return false;
    }

    public static boolean parseEditCustomerRequestResponse(String editCustomerRequestResponse)
    {
        if(editCustomerRequestResponse.isEmpty())
            return true;
        else
            return false;
    }

    public static JSONObject buildJsonWithCustomerNonNullAttributes(Customer customer) throws
            JSONException
    {
        JSONObject jsonObject = new JSONObject();
        addToJsonObjectIfNotNull(jsonObject, "Cliente", customer.getId());
        addToJsonObjectIfNotNull(jsonObject, "Nome", customer.getName());
        addToJsonObjectIfNotNull(jsonObject, "Descricao", customer.getDescription());
        addToJsonObjectIfNotNull(jsonObject, "Morada", customer.getAddress());
        addToJsonObjectIfNotNull(jsonObject, "Localidade", customer.getCity());
        addToJsonObjectIfNotNull(jsonObject, "CodigoPostal", customer.getPostalCode());
        addToJsonObjectIfNotNull(jsonObject, "LocalidadeCodigoPostal", customer.getPostalCodeCity());
        addToJsonObjectIfNotNull(jsonObject, "Telefone", customer.getPhoneNumber());
        addToJsonObjectIfNotNull(jsonObject, "Fax", customer.getFaxNumber());
        addToJsonObjectIfNotNull(jsonObject, "EnderecoWeb", customer.getWebSite());
        addToJsonObjectIfNotNull(jsonObject, "Distrito", customer.getState());
        addToJsonObjectIfNotNull(jsonObject, "NumContribuinte", customer.getTaxNumber());
        addToJsonObjectIfNotNull(jsonObject, "Pais", customer.getCountry());
        addToJsonObjectIfNotNull(jsonObject, "Moeda", customer.getCurrency());
        addToJsonObjectIfNotNull(jsonObject, "DebitoContaCorrente", customer.getCheckingAccountDebit());
        addToJsonObjectIfNotNull(jsonObject, "DebitoEncomendasPendentes", customer.getPendingOrdersDebit());

        return jsonObject;
    }

    private static void addToJsonObjectIfNotNull(JSONObject jsonObject, String key, String value) throws
            JSONException
    {
        if(value == null)
            value = "";

        jsonObject.put(key, value);
    }
}
