package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.CommonDataClasses.Customer;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomerParserAndStringBuilder
{

    public static Customer parseViewCustomerRequestResponse(String viewCustomerRequestResponse)
    {
        return new Customer("ldfjls", "---fjsdlkfs", null, null,
                null, null, null, null, null, null, null,
                null, null,  null, null, null);
    }

    public static Customer parseAddCustomerRequestResponse(String addCustomerRequestResponse)
    {
        return new Customer("ldfjls", "---fjsdlkfs", null, null,
                null, null, null, null, null, null, null,
                null, null,  null, null, null);
    }

    public static JSONObject buildJsonWithCustomerNonNullAttributes(Customer customer) throws
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
