package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.CommonDataClasses.CustomerFullyDetailed;
import com.example.david.sinfapplication.CommonDataClasses.CustomerOfSalesman;
import com.example.david.sinfapplication.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomerParserAndStringBuilder
{

    public static CustomerFullyDetailed parseViewCustomerRequestResponse(String viewCustomerRequestResponse) throws JSONException
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

        return new CustomerFullyDetailed(id, name, description, address,
                city, postalCode, postalCodeCity, phoneNumber,
                faxNumber, webSite, state, taxNumber,
                country, currency, checkingAccountDebit,
                pendingOrdersDebit);
    }


    public static ArrayList<CustomerOfSalesman> parseViewCustomersOfSalesmanResponse(String viewCustomersOfSalesmanResponse) throws JSONException
    {
        //TODO ver se isto ta bem quando o primavera estiver a dar
        JSONObject dataSetObject = new JSONObject(viewCustomersOfSalesmanResponse);
        JSONArray customersArray = dataSetObject.getJSONArray("Table");
        ArrayList<CustomerOfSalesman> customers = new ArrayList<>();
        for(int i = 0; i < customersArray.length(); i++)
        {
            JSONObject productObject = customersArray.getJSONObject(i);
            String id = productObject.getString("Cliente");
            String name = productObject.getString("Nome");
            CustomerOfSalesman customerOfSalesman = new CustomerOfSalesman(id, name);
            customers.add(customerOfSalesman);
        }

        return customers;
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

    public static JSONObject buildJsonWithCustomerNonNullAttributes(CustomerFullyDetailed customerFullyDetailed) throws
            JSONException
    {
        JSONObject jsonObject = new JSONObject();
        Utils.addToJsonObjectIfNotNull(jsonObject, "Cliente", customerFullyDetailed.getId());
        Utils.addToJsonObjectIfNotNull(jsonObject, "Nome", customerFullyDetailed.getName());
        Utils.addToJsonObjectIfNotNull(jsonObject, "Descricao", customerFullyDetailed.getDescription());
        Utils.addToJsonObjectIfNotNull(jsonObject, "Morada", customerFullyDetailed.getAddress());
        Utils.addToJsonObjectIfNotNull(jsonObject, "Localidade", customerFullyDetailed.getCity());
        Utils.addToJsonObjectIfNotNull(jsonObject, "CodigoPostal", customerFullyDetailed.getPostalCode());
        Utils.addToJsonObjectIfNotNull(jsonObject, "LocalidadeCodigoPostal", customerFullyDetailed.getPostalCodeCity());
        Utils.addToJsonObjectIfNotNull(jsonObject, "Telefone", customerFullyDetailed.getPhoneNumber());
        Utils.addToJsonObjectIfNotNull(jsonObject, "Fax", customerFullyDetailed.getFaxNumber());
        Utils.addToJsonObjectIfNotNull(jsonObject, "EnderecoWeb", customerFullyDetailed.getWebSite());
        Utils.addToJsonObjectIfNotNull(jsonObject, "Distrito", customerFullyDetailed.getState());
        Utils.addToJsonObjectIfNotNull(jsonObject, "NumContribuinte", customerFullyDetailed.getTaxNumber());
        Utils.addToJsonObjectIfNotNull(jsonObject, "Pais", customerFullyDetailed.getCountry());
        Utils.addToJsonObjectIfNotNull(jsonObject, "Moeda", customerFullyDetailed.getCurrency());
        Utils.addToJsonObjectIfNotNull(jsonObject, "DebitoContaCorrente", customerFullyDetailed.getCheckingAccountDebit());
        Utils.addToJsonObjectIfNotNull(jsonObject, "DebitoEncomendasPendentes", customerFullyDetailed.getPendingOrdersDebit());

        return jsonObject;
    }
}
