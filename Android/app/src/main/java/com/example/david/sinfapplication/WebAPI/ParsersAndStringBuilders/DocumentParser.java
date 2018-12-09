package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.CommonDataClasses.Document;
import com.example.david.sinfapplication.CommonDataClasses.DocumentLine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DocumentParser
{
    public static ArrayList<Document> parseViewCustomerDocumentRequestResponse(String viewCustomerDocumentRequestResponse) throws
            JSONException
    {
        ArrayList<Document> documents = new ArrayList<>();
        JSONObject dataSetObject = new JSONObject(viewCustomerDocumentRequestResponse).getJSONObject("DataSet");
        JSONArray documentsArray = dataSetObject.getJSONArray("Table");

        int numberDocumentLines = documentsArray.length();
        for (int i = 0; i < numberDocumentLines; i++)
        {
            JSONObject line = documentsArray.getJSONObject(0);
            String id = line.getString("Id");
            String docType = line.getString("TipoDoc");
            String serie = line.getString("Serie");
            String docNumber = line.getString("NumDoc");
            String documentTotal = line.getString("TotalDocumento");
            String date = line.getString("Data");
            String state = line.getString("Estado");

            Document document = new Document(id, docType, serie, docNumber, documentTotal, date, state);
            documents.add(document);
        }

        return documents;
    }

    public static ArrayList<DocumentLine> parseViewDocumentDetailsRequestResponse(String viewDocumentDetailsRequestResponse) throws
            JSONException
    {
        ArrayList<DocumentLine> documentLines = new ArrayList<>();
        JSONObject dataSetObject = new JSONObject(viewDocumentDetailsRequestResponse).getJSONObject("DataSet");
        JSONArray documentLinesArray = dataSetObject.getJSONArray("Table");

        int numberDocumentLines = documentLinesArray.length();
        for (int i = 0; i < numberDocumentLines; i++)
        {
            JSONObject line = documentLinesArray.getJSONObject(i);
            int number = line.getInt("NumLinha");
            String productId = line.getString("Artigo");
            String productDescription = line.getString("Descricao");
            int discount = line.getInt("Desconto1");
            int commercialDiscount = line.getInt("DescontoComercial");
            int taxes = line.getInt("TaxaIva");
            int quantity = line.getInt("Quantidade");
            int unitaryPrice = line.getInt("PrecUnit");
            int netPrice = line.getInt("NumLinha");
            String date = line.getString("PrecUnit");
            String outDate = line.getString("DataSaida");
            String deliveryDate = line.getString("DataEntrega");
            int comission = line.getInt("Comissao");
            String idCabecDoc = line.getString("IdCabecDoc");

            DocumentLine documentLine = new DocumentLine(number, productId, productDescription, discount, commercialDiscount, taxes,
                    quantity, unitaryPrice, netPrice, date, outDate, deliveryDate, comission, idCabecDoc);
            documentLines.add(documentLine);
        }

        return documentLines;
    }

    public static boolean parseCreateDocumentRequestResponse(String createDocumentRequestResponse) throws
            JSONException
    {
        //TODO
        return true;
    }

    public static String buildRequestBodyForCreateDocumentRequest(Document document)
    {
        //TODO
        return "";
    }
}
