package com.example.david.sinfapplication.WebAPI;


import com.example.david.sinfapplication.CommonDataClasses.Customer;
import com.example.david.sinfapplication.CommonDataClasses.Document;
import com.example.david.sinfapplication.Utils;
import com.example.david.sinfapplication.WebAPI.Communication.ContentType;
import com.example.david.sinfapplication.WebAPI.Communication.RequestMethod;
import com.example.david.sinfapplication.CommonDataClasses.Product;
import com.example.david.sinfapplication.WebAPI.Communication.Route;
import com.example.david.sinfapplication.WebAPI.Communication.PrimaveraWebAPI;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.CustomerParserAndStringBuilder;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.DocumentParser;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.ProductParser;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.ProductsListParser;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class WebAPI
{
    /**
     * Logs in to the webapi. Returns 0 on success; 1 on server error
     *
     * @return 0 on success; 1 on server error
     */
    public static int login()
    {
        try
        {
            PrimaveraWebAPI.login("FEUP", "qualquer1", "BELAFLOR", "DEFAULT",
                    "password", "professional");
        } catch (UnsupportedEncodingException e)
        {
            return 1;
        }

        return 0;
    }

    /**
     * Retrieves the products list from the ERP server. Returns an ArrayList with instances of class Product representing the products retrieved from the ERP server.
     *
     * @return An ArrayList with instances of class Product representing the products retrieved from the ERP server.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @throws JSONException
     */
    public static ArrayList<Product> getProductsList() throws InterruptedException, ExecutionException, TimeoutException,
            JSONException
    {
        String query = "\"" + "SELECT A.Artigo, A.Descricao, A.Observacoes, A.StkActual, AM.PVP1, AM.PVP2, AM.PVP3, AM.PVP4, AM.PVP5, " +
                "AM.PVP6, AM.Moeda from Artigo A INNER JOIN ArtigoMoeda AM ON A.Artigo = AM.Artigo" + "\"";

        String listProductsRequestResponse = PrimaveraWebAPI.sendRequest(Route.ListProducts, RequestMethod.ListProducts,
                ContentType.ApplicationJson, query.getBytes());
        return ProductsListParser.parseListProductsRequestResponse(listProductsRequestResponse);
    }

    /**
     * Retrieves details of a customer by id from the ERP server. Returns an ArrayList with instances of class Product representing the products retrieved from the ERP server.
     * @param customerId A String representing the id of the customer to retrieve from the ERP server.
     * @return An instance of class Customer representing the customer retrieved from server.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static Customer viewCustomer(String customerId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String requestRoute = Route.viewCustomer + customerId;

        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.ViewCustomer,
                ContentType.UrlEncoded, new byte[0]);
        try
        {
            return CustomerParserAndStringBuilder.parseViewCustomerRequestResponse(viewCustomerRequestResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;

    }

    public static boolean addCustomer(Customer customer) throws InterruptedException,
            ExecutionException, TimeoutException, JSONException
    {
        String requestBody = CustomerParserAndStringBuilder.buildJsonWithCustomerNonNullAttributes(customer).toString();
        String addCustomerRequestResponse = PrimaveraWebAPI.sendRequest(Route.addCostumer, RequestMethod.AddCustomer,
                ContentType.ApplicationJson, requestBody.getBytes());
        if(addCustomerRequestResponse == null)
            return false;

        return CustomerParserAndStringBuilder.parseAddCustomerRequestResponse(addCustomerRequestResponse);

    }

    /**
     * Edits the details of a customer by id on the ERP server. Returns an int indicating the result of the request.
     * @param customerId A String representing the id of the customer whose information will be edited in the ERP server.
     * @param customer An instance of class customer representing the new customer data to be sent to the server.
     * @return An int indicating the success of the request. 0 indicates success; 1 indicates server error
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static int editCustomer(String customerId, Customer customer) throws InterruptedException, ExecutionException, TimeoutException, JSONException
    {
        String requestRoute = Route.editCostumer + customerId;
        String requestBody = CustomerParserAndStringBuilder.buildJsonWithCustomerNonNullAttributes(customer).toString();

        String editCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.EditCustomer,
                ContentType.ApplicationJson, requestBody.getBytes());

        boolean success = CustomerParserAndStringBuilder.parseEditCustomerRequestResponse(editCustomerRequestResponse);

        if (success)
            return 0;
        else
            return 1;
    }

    /**
     * Retrieves details of a product by id from the ERP server. Returns an instance of class Product representing the product retrieved from the ERP server.
     * @param productId A String representing the id of the product to retrieve from the ERP server.
     * @return An instance of class Product representing the product retrieved from server.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static Product viewProductAndStock(String productId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String requestRoute = Route.viewProduct + productId;
        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.ViewProduct,
                ContentType.UrlEncoded, new byte[0]);
        try
        {
            return ProductParser.parseViewProductRequestResponse(viewCustomerRequestResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves details of a document by id from the ERP server. Returns an instance of class Document representing the document retrieved from the ERP server.
     * @param documentId A String representing the id of the document to retrieve from the ERP server.
     * @return An instance of class Document representing the document retrieved from server.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static Document viewDocumentDetails(String documentId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String query = "\"" + "SELECT LD.NumLinha, LD.Artigo, LD.Desconto1, LD.Desconto2, LD.Desconto3, LD.TaxaIva, LD.Quantidade, " +
                "LD.PrecUnit, LD.Data, LD.DataSaida, LD.DataEntrega, LD.DescontoComercial, LD.Comissao, LD.PrecoLiquido, LD.Vendedor," +
                "LD.Descricao, LD.IdCabecDoc, CD.Id, CD.TipoDoc, CD.Serie, CD.NumDoc, CD.TotalDocumento, CD.Data, CDS.Estado from CabecDoc" +
                "CD INNER JOIN CabecDocStatus CDS ON CDS.IdCabecDoc = CD.Id INNER JOIN LinhasDoc LD ON CDS.IdCabecDoc = LD.IdCabecDoc where" +
                "LD.IdCabecDoc = '" + documentId + "'" + "\"";

        String requestRoute = Route.viewDocument + documentId;
        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.ViewDocument,
                ContentType.ApplicationJson, query.getBytes());
        try
        {
            return DocumentParser.parseViewProductRequestResponse(viewCustomerRequestResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
