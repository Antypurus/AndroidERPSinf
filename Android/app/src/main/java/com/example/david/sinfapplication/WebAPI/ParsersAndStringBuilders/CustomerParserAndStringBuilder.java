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
        JSONObject dataSetObject = new JSONObject(viewCustomerRequestResponse);
        String id = dataSetObject.getString("Cliente");
        String name = dataSetObject.getString("Nome");
        String description = dataSetObject.getString("Descricao");
        String address = dataSetObject.getString("Morada");
        String city = dataSetObject.getString("Localidade");
        String postalCode = dataSetObject.getString("CodigoPostal");
        String postalCodeCity = dataSetObject.getString("LocalidadeCodigoPostal");
        String phoneNumber = dataSetObject.getString("Telefone");
        String faxNumber = dataSetObject.getString("faxNumber");
        String webSite = dataSetObject.getString("webSite");
        String state = dataSetObject.getString("Distrito");
        String taxNumber = dataSetObject.getString("NumContribuinte");
        String country = dataSetObject.getString("Pais");
        String currency = dataSetObject.getString("Moeda");
        String checkingAccountDebit = dataSetObject.getString("DebitoContaCorrente");
        String pendingOrdersDebit = dataSetObject.getString("DebitoEncomendasPendentes");

        return new Customer(id, name, description, address,
                city, postalCode, postalCodeCity, phoneNumber,
                faxNumber, webSite, state, taxNumber,
                country, currency, checkingAccountDebit,
                pendingOrdersDebit);
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
