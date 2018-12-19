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
            JSONObject line = documentsArray.getJSONObject(i);
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
        if(viewDocumentDetailsRequestResponse == null)
            return null;
        JSONObject dataSetObject = new JSONObject(viewDocumentDetailsRequestResponse).getJSONObject("DataSet");
        if(dataSetObject == null)
            return null;
        JSONArray documentLinesArray = dataSetObject.getJSONArray("Table");
        if(documentLinesArray == null)
            return null;

        int numberDocumentLines = documentLinesArray.length();
        for (int i = 0; i < numberDocumentLines; i++)
        {
            JSONObject line = documentLinesArray.getJSONObject(i);
            int number = line.getInt("NumLinha");
            String productId = line.getString("Artigo");
            String productDescription = line.getString("Descricao");
            int commercialDiscount = line.getInt("DescontoComercial");
            int quantity = line.getInt("Quantidade");
            int unitaryPrice = line.getInt("PrecUnit");
            String date = line.getString("Data"); //ver
            String outDate = line.getString("DataSaida");
            String deliveryDate = line.getString("DataEntrega");
            int comission = line.getInt("Comissao");
            String idCabecDoc = line.getString("IdCabecDoc");

            DocumentLine documentLine = new DocumentLine(number, productId, productDescription, commercialDiscount,
                    quantity, unitaryPrice, date, outDate, deliveryDate, comission, idCabecDoc);
            documentLines.add(documentLine);
        }

        return documentLines;
    }

    public static boolean parseCreateDocumentRequestResponse(String createDocumentRequestResponse)
    {
        if(createDocumentRequestResponse == null)
            return false;

        if(createDocumentRequestResponse == "true")
            return true;
        else
            return false;
    }

    public static String buildRequestBodyForCreateDocumentRequest(Document document, String customerId)
    {
        //TODO NAO consigo colocar DescontoComercial nem Desconto1 no criar documento
        JSONObject requestBody = new JSONObject();
        try
        {
            JSONArray linesArray = new JSONArray();
            ArrayList<DocumentLine> documentLines = document.getLines();
            for(int i = 0; i < documentLines.size(); i++)
            {
                JSONObject line = new JSONObject();
                DocumentLine documentLine = documentLines.get(i);
                String item = documentLine.getProductId();
                line.put("Artigo", item);
                int quantity = documentLine.getQuantity();
                line.put("Quantidade", String.valueOf(quantity));
                linesArray.put(line);
            }
            requestBody.putOpt("Linhas", linesArray);
            //produtCart com as suas respetivas cenas--- Artigo, Descricao, Quantidade, PrecUnit, TaxaIva(maybe as IVA from product)

            requestBody.put("Tipodoc", document.getDocType());
            requestBody.put("Serie", document.getSeries());
            requestBody.put("Entidade", customerId);
            requestBody.put("TipoEntidade","C");  //client always
            //requestBody.put("DescontoComercial", document.getDocType());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }


        return requestBody.toString();
    }
}
