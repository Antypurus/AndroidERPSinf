package com.example.david.sinfapplication.WebAPI;


import com.example.david.sinfapplication.CommonDataClasses.AgendaEntry;
import com.example.david.sinfapplication.CommonDataClasses.CustomerBasic;
import com.example.david.sinfapplication.CommonDataClasses.CustomerFullyDetailed;
import com.example.david.sinfapplication.CommonDataClasses.CustomerOfSalesman;
import com.example.david.sinfapplication.CommonDataClasses.Document;
import com.example.david.sinfapplication.CommonDataClasses.DocumentLine;
import com.example.david.sinfapplication.CommonDataClasses.Product;
import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitie;
import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitieProposal;
import com.example.david.sinfapplication.WebAPI.Communication.ContentType;
import com.example.david.sinfapplication.WebAPI.Communication.PrimaveraAuthenticationCredentials;
import com.example.david.sinfapplication.WebAPI.Communication.PrimaveraWebAPI;
import com.example.david.sinfapplication.WebAPI.Communication.PythonResponseStrings;
import com.example.david.sinfapplication.WebAPI.Communication.PythonWebAPI;
import com.example.david.sinfapplication.WebAPI.Communication.RequestMethod;
import com.example.david.sinfapplication.WebAPI.Communication.Route;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.AgendaParser;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.CustomerParserAndStringBuilder;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.DocumentParser;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.ProductsListParser;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.PythonLoginResponseParser;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.SaleOpportunitieParser;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class WebAPI
{
    public enum loginResult {loginSucessfull, loginFailedServerError, loginFailedWrongUsernameOrPassword};


    /**
     * Logs in to the webapi. Returns 0 on success; 1 on server error
     * @param username A String representing the username to use to login to the WebAPI.
     * @param password A String representing the password to use to login to the WebAPI.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @return 0 on success; 1 on server error; 2 on wrong username/password combination
     */
    public static loginResult login(String username, String password) throws InterruptedException, ExecutionException, TimeoutException
    {
        try
        {
            PythonWebAPI pythonWebAPI = new PythonWebAPI();
            String pythonResponse = pythonWebAPI.makeLoginRequest(username, password);
            PrimaveraAuthenticationCredentials credentials = PythonLoginResponseParser.parsePythonLoginResponse(pythonResponse);
            if (credentials == null)
            {
                if (pythonResponse.equals(PythonResponseStrings.invalidUsernameOrPassword))
                    return loginResult.loginFailedWrongUsernameOrPassword;
                else
                    return loginResult.loginFailedServerError;
            }
            String primaveraUsername = credentials.getUsername();
            String primaveraPassword = credentials.getPassword();
            String company = credentials.getCompany();
            String instance = credentials.getInstance();
            String grant_type = credentials.getGrant_type();
            String line = credentials.getLine();
            PrimaveraWebAPI.login(primaveraUsername, primaveraPassword, company, instance,
                    grant_type, line);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return loginResult.loginFailedServerError;
        }

        return loginResult.loginSucessfull;
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
        String requestRoute = Route.listCustomersOfASalesman;
        String query = "\"SELECT Cliente, Nome FROM Clientes WHERE Vendedor = \'" + salesmanId + "\'\"";

        //TODO
        query = "\"SELECT Cliente, Nome, Fac_Tel FROM Clientes WHERE Vendedor = '"+salesmanId+"'\"";
        String viewCustomersOfSalesmanResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.listCustomersOfASalesman,
                ContentType.ApplicationJson, query.getBytes());
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
     * @param customerBasic An instance of class CustomerBasic, filled with the details to be sent to the ERP server.
     * @return A boolean indicating the success of the request. true indicates success; false indicates server error
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @throws JSONException
     */
    public static boolean addCustomer(CustomerBasic customerBasic) throws InterruptedException,
            ExecutionException, TimeoutException, JSONException
    {
        String requestBody = CustomerParserAndStringBuilder.buildJsonWithCustomerNonNullAttributes(customerBasic, false).toString();
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
    public static CustomerFullyDetailed viewCustomer(String customerId) throws InterruptedException,
            ExecutionException, TimeoutException, JSONException
    {
        String requestRoute = Route.viewCustomer + customerId;
        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.ViewCustomer,
                ContentType.UrlEncoded, new byte[0]);
        return CustomerParserAndStringBuilder.parseViewCustomerRequestResponse(viewCustomerRequestResponse);
    }

    /**
     * Edits the details of a customerFullyDetailed by id on the ERP server. Returns a boolean indicating the result of the request.
     * @param customerFullyDetailed An instance of class customerFullyDetailed representing the new customerFullyDetailed data to be sent to the server.
     * @return A boolean indicating the success of the request. true indicates success; false indicates server error
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static boolean editCustomer(CustomerBasic customerFullyDetailed) throws InterruptedException, ExecutionException, TimeoutException, JSONException
    {
        String requestRoute = Route.editCostumer;
        String requestBody = CustomerParserAndStringBuilder.buildJsonWithCustomerNonNullAttributes(customerFullyDetailed, true).toString();

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
        String query = "\"SELECT A.Artigo, A.Descricao, A.Observacoes, A.CDU_CampoVar1, A.StkActual, AM.PVP1, AM.Moeda, " +
                "A.Familia, A.SubFamilia from Artigo A INNER JOIN ArtigoMoeda AM ON A.Artigo = AM.Artigo\"";

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
    public static boolean createDocument(Document document, String customerId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String requestBody = DocumentParser.buildRequestBodyForCreateDocumentRequest(document, customerId);
        String createDocumentRequestResponse = PrimaveraWebAPI.sendRequest(Route.createDocument, RequestMethod.createDocument,
                ContentType.ApplicationJson, requestBody.getBytes());

        return DocumentParser.parseCreateDocumentRequestResponse(createDocumentRequestResponse);
    }

    /**
     * Retrieves all documents of a customer, of the given types. Returns an ArrayList of Document instances representing the documents retrieved from the ERP server.
     * @param customerName The name of the customer of which documents will be retrieved
     * @return An instance of class Document representing the document retrieved from server.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static ArrayList<Document> viewDocumentsFromCustomer(String customerName) throws InterruptedException, ExecutionException, TimeoutException
    {
        String query = "\"" + "SELECT  CD.Id, CD.TipoDoc, CD.Serie, CD.NumDoc, CD.TotalDocumento, CD.Data, CDS.Estado " +
                "FROM CabecDoc CD INNER JOIN CabecDocStatus CDS ON CDS.IdCabecDoc = CD.Id " +
                "WHERE CD.Entidade = '" + customerName + "' AND (CD.TipoDoc = 'FA' OR CD.TipoDoc = 'ECL' OR CD.TipoDoc = 'ORC')" + "\"";

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
        String query = "\"" + "SELECT LD.NumLinha, LD.Artigo, LD.TaxaIva, LD.Quantidade, " +
                "LD.PrecUnit, LD.Data, LD.DataSaida, LD.DataEntrega, LD.DescontoComercial, LD.Comissao, LD.Vendedor," +
                "LD.Descricao, LD.IdCabecDoc, CD.Id, CD.TipoDoc, CD.Serie, CD.NumDoc, CD.TotalDocumento, CD.Data, CDS.Estado from CabecDoc " +
                "CD INNER JOIN CabecDocStatus CDS ON CDS.IdCabecDoc = CD.Id INNER JOIN LinhasDoc LD ON CDS.IdCabecDoc = LD.IdCabecDoc where " +
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

    /**
     * Adds a saleOpportunitie on the ERP server. Returns a boolean indicating the result of the request.
     * @param saleOpportunitie A object of SaleOpportunitie that contains the saleOpportunitie to be created
     * @return A boolean indicating the success of the request. true indicates success; false indicates server error
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static boolean createSaleOpportunitie(SaleOpportunitie saleOpportunitie) throws
            InterruptedException, ExecutionException, TimeoutException, JSONException
    {
        String requestRoute = Route.createSaleOpportunitie;
        String createSaleOpportunitieRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.createSaleOpportunitie,
                ContentType.ApplicationJson, saleOpportunitie.getJson().getBytes());

        return SaleOpportunitieParser.parseCreateSaleOpportunitieRequestResponse(createSaleOpportunitieRequestResponse);
    }

    /**
     * Adds a proposal for a saleOpportunitie on the ERP server. Returns a boolean indicating the result of the request.
     * @param saleOpportunitieProposal A object of SaleOpportunitieProposal that contains the saleOpportunitieProposal to be created
     * @return A boolean indicating the success of the request. true indicates success; false indicates server error
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static boolean createProposalForSaleOpportunitie(SaleOpportunitieProposal saleOpportunitieProposal) throws
            InterruptedException, ExecutionException, TimeoutException
    {
        String requestRoute = Route.createProposalForSaleOpportunitie;
        String createSaleOpportunitieRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.createProposalForSaleOpportunitie,
                ContentType.ApplicationJson, saleOpportunitieProposal.getJson().getBytes());

        return SaleOpportunitieParser.parseCreateProposalForSaleOpportunitieRequestResponse(createSaleOpportunitieRequestResponse);
    }

    /**
     * Adds a saleOpportunitie on the ERP server. Returns a boolean indicating the result of the request.
     * @param saleOpportunitieNumber The number of the sale opportunitie to be transformed
     * @param numberProposalToAccept The number of the proposal, of the group of proposals that were made to that sale opportunitie, that will be accepted4
     * @return A boolean indicating the success of the request. true indicates success; false indicates server error
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static boolean transformSaleOpportunitie(String saleOpportunitieNumber, int numberProposalToAccept, String documentType) throws
            InterruptedException, ExecutionException, TimeoutException, JSONException
    {
        String requestRoute = Route.transformSaleOpportunitie;
        requestRoute.replaceFirst("{documentType}", documentType);

        String createSaleOpportunitieRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.transformSaleOpportunitie,
                ContentType.ApplicationJson, SaleOpportunitieParser.transformSaleOpportunitieProposalRequestBody(saleOpportunitieNumber, numberProposalToAccept).getBytes());

        return SaleOpportunitieParser.parseTransformSaleOpportunitieRequestResponse(createSaleOpportunitieRequestResponse);
    }

    public static String getAttribOfSalesOportunity(String saleOportunityNumber, String attribToRetrieve) throws InterruptedException, ExecutionException, TimeoutException, JSONException
    {
        String requestRoute = Route.getSalesOportunity + saleOportunityNumber;
        String requestBody = "[" +
                "\t\"Id\"\n" +
                "]";

        String getSaleOpportunitieRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.getSalesOportunity,
                ContentType.ApplicationJson, requestBody.getBytes());

        String saleOportunityAttribValue = SaleOpportunitieParser.parseGetSaleOportunityResponse(getSaleOpportunitieRequestResponse, attribToRetrieve);

        return saleOportunityAttribValue;
    }

    public static Integer getMaxIdOfProposalThatBelongToSaleOpportunity(String opportunityId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String query = "\"" + "SELECT MAX(NumProposta) as NumProposta FROM PropostasOPV WHERE IdOportunidade = '" + opportunityId + "'\"";

        String requestRoute = Route.getMaxIdOfProposalThatBelongToSaleOpportunity;
        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.getMaxIdOfProposalThatBelongToSaleOpportunity,
                ContentType.ApplicationJson, query.getBytes());
        try
        {
            return SaleOpportunitieParser.parseMaxIdOfProposalThatBelongToSaleOpportunity(viewCustomerRequestResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<SaleOpportunitie> getAllSalesOpportunities() throws InterruptedException, ExecutionException, TimeoutException
    {
        String query = "\"SELECT  * from CabecOportunidadesVenda ORDER BY DataCriacao,DataExpiracao ASC\"";

        String requestRoute = Route.getSalesOpportunitiesOfCustomer;
        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.getSalesOpportunitiesOfCustomer,
                ContentType.ApplicationJson, query.getBytes());
        try
        {
            return SaleOpportunitieParser.parseGetSalesOpportunitiesOfCustomer(viewCustomerRequestResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<SaleOpportunitie> getSalesOpportunitiesOfCustomer(String customerId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String query = "\"SELECT  * from CabecOportunidadesVenda WHERE Entidade='" + customerId + "' ORDER BY DataCriacao,DataExpiracao ASC\"";

        String requestRoute = Route.getSalesOpportunitiesOfCustomer;
        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.getSalesOpportunitiesOfCustomer,
                ContentType.ApplicationJson, query.getBytes());
        try
        {
            return SaleOpportunitieParser.parseGetSalesOpportunitiesOfCustomer(viewCustomerRequestResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<SaleOpportunitieProposal> getAllProposalsOfASalesOpportunity(String salesOpportunityId, SaleOpportunitie saleOpportunitie) throws InterruptedException, ExecutionException, TimeoutException
    {
        String query = "\"" + "SELECT  POPV.Valor, POPV.NumProposta, COV.EstadoVenda from CabecOportunidadesVenda COV INNER JOIN PropostasOPV POPV ON COV.ID = POPV.IdOportunidade WHERE IdOportunidade= '" + salesOpportunityId + "'\"";

        String requestRoute = Route.getAllProposalsOfASalesOpportunity;
        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.getAllProposalsOfASalesOpportunity,
                ContentType.ApplicationJson, query.getBytes());
        try
        {
            return SaleOpportunitieParser.parseGetAllProposalsOfASalesOpportunity(viewCustomerRequestResponse, saleOpportunitie);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean getDetailsOfProposal(SaleOpportunitieProposal saleOpportunitieProposal) throws InterruptedException, ExecutionException, TimeoutException
    {
        String requestRoute = Route.getProposalDetailsPart1 + saleOpportunitieProposal.getSaleOpportunitie().getOpportunitieId()
                + "/" + saleOpportunitieProposal.getProposalNumber() + Route.getProposalDetailsPart2;

        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.getProposalDetails,
                ContentType.UrlEncoded, new byte[0]);
        try
        {
            SaleOpportunitieParser.parseGetDetailsOfProposal(viewCustomerRequestResponse, saleOpportunitieProposal);
            return true;
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return false;
    }


    public static ArrayList<AgendaEntry> getAllAgendaEntries(String customerId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String query = "\"SELECT  * from Tarefas T JOIN Clientes C ON T.EntidadePrincipal = C.Cliente WHERE C.Vendedor = \'" + customerId + "\'\"";

        String requestRoute = Route.listAgendaEntries;
        String listAgendaEntriesRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.listAgendaEntries,
                ContentType.ApplicationJson, query.getBytes());
        try
        {
            return AgendaParser.parseListAgendaEntries(listAgendaEntriesRequestResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //TODO
    /*
    public static boolean setAgendaEntryNotes(String customerId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String query = "\"SELECT  * from Tarefas T JOIN Clientes C ON T.EntidadePrincipal = C.Cliente WHERE C.Vendedor = \'" + customerId + "\'\"";

        String requestRoute = Route.setAgendaEntryNotes;
        String setAgendaEntryNotesRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.setAgendaEntryNotes,
                ContentType.ApplicationJson, query.getBytes());
        try
        {
            return AgendaParser.parseSetAgendaEntryNotes(setAgendaEntryNotesRequestResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return false;
    }*/
}
