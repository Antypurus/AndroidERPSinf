package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.CommonDataClasses.Customer;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomerParserAndStringBuilder
{

    public static Customer parseViewCustomerRequestResponse(String viewCustomerRequestResponse)
    {
        return new Customer("f", "---fjsdlkfs", null, null,
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
