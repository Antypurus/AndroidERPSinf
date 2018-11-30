package com.example.david.sinfapplication.WebAPI;


import android.util.JsonReader;

import com.example.david.sinfapplication.CommonDataClasses.Customer;
import com.example.david.sinfapplication.WebAPI.Communication.ContentType;
import com.example.david.sinfapplication.WebAPI.Communication.RequestMethod;
import com.example.david.sinfapplication.CommonDataClasses.Product;
import com.example.david.sinfapplication.WebAPI.Communication.Route;
import com.example.david.sinfapplication.WebAPI.Communication.PrimaveraWebAPI;
import com.example.david.sinfapplication.WebAPI.Parsers.CustomerParser;
import com.example.david.sinfapplication.WebAPI.Parsers.ProductsListParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class WebAPI
{
    public static ArrayList<Product> getProductsList() throws InterruptedException, ExecutionException, TimeoutException,
            JSONException
    {
        String query = "\""+ "SELECT A.Artigo, A.Descricao, A.Observacoes, A.StkActual, AM.PVP1, AM.PVP2, AM.PVP3, AM.PVP4, AM.PVP5, " +
                "AM.PVP6, AM.Moeda from Artigo A INNER JOIN ArtigoMoeda AM ON A.Artigo = AM.Artigo" + "\"";

        String listProductsRequestResponse = PrimaveraWebAPI.sendRequest(Route.ListProducts, RequestMethod.ListProducts,
                ContentType.ApplicationJson, query.getBytes());
        return ProductsListParser.parseListProductsRequestResponse(listProductsRequestResponse);
    }

    public static Customer viewCustomer(String customerId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String requestRoute = Route.viewCustomer + customerId;

        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.ViewCustomer,
                ContentType.ApplicationJson, new byte[0]);
        return CustomerParser.parseViewCustomerRequestResponse(viewCustomerRequestResponse);

    }

    public static Customer addCustomer(Customer customer) throws InterruptedException,
            ExecutionException, TimeoutException, JSONException
    {
        String requestBody = buildJsonWithCustomerNonNullAttributes(customer).toString();
        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(Route.addCostumer, RequestMethod.ViewCustomer,
                ContentType.ApplicationJson, requestBody.getBytes());
        return CustomerParser.parseViewCustomerRequestResponse(viewCustomerRequestResponse);

    }

    private static JSONObject buildJsonWithCustomerNonNullAttributes(Customer customer) throws
            JSONException
    {
        JSONObject jsonObject = new JSONObject();
        addToJsonObjectIfNotNull(jsonObject, "id", customer.getId());
        addToJsonObjectIfNotNull(jsonObject, "name", customer.getName());
        addToJsonObjectIfNotNull(jsonObject, "description", customer.getDescription());
        addToJsonObjectIfNotNull(jsonObject, "address", customer.getAddress());
        addToJsonObjectIfNotNull(jsonObject, "city", customer.getCity());
        addToJsonObjectIfNotNull(jsonObject, "postalCode", customer.getPostalCode());
        addToJsonObjectIfNotNull(jsonObject, "postalCodeCity", customer.getPostalCodeCity());
        addToJsonObjectIfNotNull(jsonObject, "phoneNumber", customer.getPhoneNumber());
        addToJsonObjectIfNotNull(jsonObject, "faxNumber", customer.getFaxNumber());
        addToJsonObjectIfNotNull(jsonObject, "webSite", customer.getWebSite());
        addToJsonObjectIfNotNull(jsonObject, "state", customer.getState());
        addToJsonObjectIfNotNull(jsonObject, "taxNumber", customer.getTaxNumber());
        addToJsonObjectIfNotNull(jsonObject, "country", customer.getCountry());
        addToJsonObjectIfNotNull(jsonObject, "currency", customer.getCurrency());
        addToJsonObjectIfNotNull(jsonObject, "checkingAccountDebit", customer.getCheckingAccountDebit());
        addToJsonObjectIfNotNull(jsonObject, "pendingOrdersDebit", customer.getPendingOrdersDebit());

        return jsonObject;
    }

    private static void addToJsonObjectIfNotNull(JSONObject jsonObject, String key, String value) throws
            JSONException
    {
        if(value != null)
            jsonObject.put(key, value);
    }
}
