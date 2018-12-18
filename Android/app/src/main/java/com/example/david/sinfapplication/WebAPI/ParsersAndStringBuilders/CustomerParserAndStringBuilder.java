package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.CommonDataClasses.CustomerBasic;
import com.example.david.sinfapplication.CommonDataClasses.CustomerFullyDetailed;
import com.example.david.sinfapplication.CommonDataClasses.CustomerOfSalesman;
import com.example.david.sinfapplication.Utils.UtilsClass;

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
        String faxNumber = dataSetObject.getString("Fax");
        String webSite = dataSetObject.getString("EnderecoWeb");
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
        //TODO ver se isto ta bem quando o primavera estiver a
        if(viewCustomersOfSalesmanResponse==null)
            return null;

        JSONObject dataSetObject = new JSONObject(viewCustomersOfSalesmanResponse).getJSONObject("DataSet");
        JSONArray customersArray = dataSetObject.getJSONArray("Table");
        ArrayList<CustomerOfSalesman> customers = new ArrayList<>();
        for(int i = 0; i < customersArray.length(); i++)
        {
            JSONObject productObject = customersArray.getJSONObject(i);
            String id = productObject.getString("Cliente");
            String name = productObject.getString("Nome");
            String phoneNumber = productObject.getString("Telefone");
            CustomerOfSalesman customerOfSalesman = new CustomerOfSalesman(id, name, phoneNumber);
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

    public static JSONObject buildJsonWithCustomerNonNullAttributes(CustomerBasic customerFullyDetailed, Boolean editMode) throws
            JSONException
    {
        JSONObject jsonObject = new JSONObject();
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "Cliente", customerFullyDetailed.getId());
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "Nome", customerFullyDetailed.getName());
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "Morada", customerFullyDetailed.getAddress());
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "Telefone", customerFullyDetailed.getPhoneNumber());
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "NumContribuinte", customerFullyDetailed.getTaxNumber());
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "Moeda", customerFullyDetailed.getCurrency());
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "EmModoEdicao", editMode.toString());

        return jsonObject;
    }
}
