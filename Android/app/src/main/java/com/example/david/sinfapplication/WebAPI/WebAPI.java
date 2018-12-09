package com.example.david.sinfapplication.WebAPI;


import com.example.david.sinfapplication.CommonDataClasses.CustomerFullyDetailed;
import com.example.david.sinfapplication.CommonDataClasses.CustomerOfSalesman;
import com.example.david.sinfapplication.CommonDataClasses.Document;
import com.example.david.sinfapplication.CommonDataClasses.DocumentLine;
import com.example.david.sinfapplication.WebAPI.Communication.ContentType;
import com.example.david.sinfapplication.WebAPI.Communication.RequestMethod;
import com.example.david.sinfapplication.CommonDataClasses.Product;
import com.example.david.sinfapplication.WebAPI.Communication.Route;
import com.example.david.sinfapplication.WebAPI.Communication.PrimaveraWebAPI;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.CustomerParserAndStringBuilder;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.DocumentParser;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.ProductsListParser;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
     * Retrieves a list of customers of the salesman passed as parameter. Returns an ArrayList with instances of class CustomerOfSalesman representing the customers retrieved from the ERP server.
     * @param salesmanId A String representing the id of the salesman whose customers should be retrieved from the ERP server.
     * @return An ArrayList with instances of class CustomerOfSalesman representing the customers retrieved from server.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static ArrayList<CustomerOfSalesman> listCustomersOfASalesman(String salesmanId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String requestRoute = Route.listCustomersOfASalesman + salesmanId;

        String viewCustomersOfSalesmanResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.listCustomersOfASalesman,
                ContentType.ApplicationJson, new byte[0]);
        try
        {
            return CustomerParserAndStringBuilder.parseViewCustomersOfSalesmanResponse(viewCustomersOfSalesmanResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Adds a CustomerFullyDetailed to the ERP server. Returns a boolean indicating the result of the request.
     * @param customerFullyDetailed An instance of class CustomerFullyDetailed, filled with the details to be sent to the ERP server.
     * @return A boolean indicating the success of the request. true indicates success; false indicates server error
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @throws JSONException
     */
    public static boolean addCustomer(CustomerFullyDetailed customerFullyDetailed) throws InterruptedException,
            ExecutionException, TimeoutException, JSONException
    {
        String requestBody = CustomerParserAndStringBuilder.buildJsonWithCustomerNonNullAttributes(customerFullyDetailed).toString();
        String addCustomerRequestResponse = PrimaveraWebAPI.sendRequest(Route.addCostumer, RequestMethod.AddCustomer,
                ContentType.ApplicationJson, requestBody.getBytes());
        if(addCustomerRequestResponse == null)
            return false;

        return CustomerParserAndStringBuilder.parseAddCustomerRequestResponse(addCustomerRequestResponse);

    }

    /**
     * Retrieves details of a customer by id from the ERP server. Returns an ArrayList with instances of class Product representing the products retrieved from the ERP server.
     * @param customerId A String representing the id of the customer to retrieve from the ERP server.
     * @return An instance of class CustomerFullyDetailed representing the customer retrieved from server.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static CustomerFullyDetailed viewCustomer(String customerId) throws InterruptedException, ExecutionException, TimeoutException
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

    /**
     * Edits the details of a customerFullyDetailed by id on the ERP server. Returns a boolean indicating the result of the request.
     * @param customerId A String representing the id of the customerFullyDetailed whose information will be edited in the ERP server.
     * @param customerFullyDetailed An instance of class customerFullyDetailed representing the new customerFullyDetailed data to be sent to the server.
     * @return A boolean indicating the success of the request. true indicates success; false indicates server error
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static boolean editCustomer(String customerId, CustomerFullyDetailed customerFullyDetailed) throws InterruptedException, ExecutionException, TimeoutException, JSONException
    {
        String requestRoute = Route.editCostumer + customerId;
        String requestBody = CustomerParserAndStringBuilder.buildJsonWithCustomerNonNullAttributes(customerFullyDetailed).toString();

        String editCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.EditCustomer,
                ContentType.ApplicationJson, requestBody.getBytes());

        return CustomerParserAndStringBuilder.parseEditCustomerRequestResponse(editCustomerRequestResponse);
    }

    /**
     * Retrieves the products list from the ERP server. Returns an ArrayList with instances of class Product representing the products retrieved from the ERP server.
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
     * Retrieves details of a product by id from the ERP server. Returns an instance of class Product representing the product retrieved from the ERP server.
     * @param productId A String representing the id of the product to retrieve from the ERP server.
     * @return An instance of class Product representing the product retrieved from server.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    //TODO no need em principio porque o list faz tudo
    /*public static Product viewProductAndStock(String productId) throws InterruptedException, ExecutionException, TimeoutException
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
    }*/

    /**
     * Adds a document on the ERP server. Returns a boolean indicating the result of the request.
     * @param document An instance of class Document representing the document to be added to the ERP server.
     * @return A boolean indicating the success of the request. true indicates success; false indicates server error
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static boolean createDocument(Document document) throws InterruptedException, ExecutionException, TimeoutException
    {
        String requestBody = DocumentParser.buildRequestBodyForCreateDocumentRequest(document);
        String createDocumentRequestResponse = PrimaveraWebAPI.sendRequest(Route.createDocument, RequestMethod.createDocument,
                ContentType.ApplicationJson, requestBody.getBytes());
        try
        {
            return DocumentParser.parseCreateDocumentRequestResponse(createDocumentRequestResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Retrieves all documents of a customer, of the given types. Returns an ArrayList of Document instances representing the documents retrieved from the ERP server.
     * @param customerId A list of string containing the id of the customer whose documents should be displayed.
     * @return An instance of class Document representing the document retrieved from server.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static ArrayList<Document> viewDocumentsFromCustomer(String customerId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String query = "\"" + "SELECT  CD.Id, CD.TipoDoc,  CD.Serie, CD.NumDoc, CD.TotalDocumento, CD.Data, CDS.Estado " +
                "FROM CabecDoc CD INNER JOIN CabecDocStatus CDS ON CDS.IdCabecDoc = CD.Id " +
                "WHERE CD.Entidade = 'C0001' AND (CD.TipoDoc = 'FA' OR CD.TipoDoc = 'ECL' OR CD.TipoDoc = 'ORC')" + "\"";

        String requestRoute = Route.viewCustomerDocuments;
        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.ViewDocument,
                ContentType.ApplicationJson, query.getBytes());
        try
        {
            return DocumentParser.parseViewCustomerDocumentRequestResponse(viewCustomerRequestResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves details of a document by id from the ERP server. Returns an instance of class Document representing the document retrieved from the ERP server.
     * @param documentId A String representing the id of the document to retrieve from the ERP server.
     * @return A list of instances of class DocumentLine representing the document details retrieved from server.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static ArrayList<DocumentLine> viewDocumentDetails(String documentId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String query = "\"" + "SELECT LD.NumLinha, LD.Artigo, LD.Desconto1, LD.TaxaIva, LD.Quantidade, " +
                "LD.PrecUnit, LD.Data, LD.DataSaida, LD.DataEntrega, LD.DescontoComercial, LD.Comissao, LD.PrecoLiquido, LD.Vendedor," +
                "LD.Descricao, LD.IdCabecDoc, CD.Id, CD.TipoDoc, CD.Serie, CD.NumDoc, CD.TotalDocumento, CD.Data, CDS.Estado from CabecDoc" +
                "CD INNER JOIN CabecDocStatus CDS ON CDS.IdCabecDoc = CD.Id INNER JOIN LinhasDoc LD ON CDS.IdCabecDoc = LD.IdCabecDoc where" +
                "LD.IdCabecDoc = '" + documentId + "'" + "\"";

        String requestRoute = Route.viewDocument;
        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.ViewDocument,
                ContentType.ApplicationJson, query.getBytes());
        try
        {
            return DocumentParser.parseViewDocumentDetailsRequestResponse(viewCustomerRequestResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
