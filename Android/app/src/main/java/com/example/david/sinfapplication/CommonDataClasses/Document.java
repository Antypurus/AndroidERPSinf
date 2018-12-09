package com.example.david.sinfapplication.CommonDataClasses;

import java.util.ArrayList;

public class Document
{
    private String id;
    private String docType;
    private String serie;
    private String docNumber;
    private String documentTotal;
    private String date;
    private String state;
    private ArrayList<DocumentLine> lines;


    public Document(String id, String docType, String serie, String docNumber, String documentTotal, String date, String state)
    {
        this.id = id;
        this.docType = docType;
        this.serie = serie;
        this.docNumber = docNumber;
        this.documentTotal = documentTotal;
        this.date = date;
        this.state = state;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDocType()
    {
        return docType;
    }

    public void setDocType(String docType)
    {
        this.docType = docType;
    }

    public String getSerie()
    {
        return serie;
    }

    public void setSerie(String serie)
    {
        this.serie = serie;
    }

    public String getDocNumber()
    {
        return docNumber;
    }

    public void setDocNumber(String docNumber)
    {
        this.docNumber = docNumber;
    }

    public String getDocumentTotal()
    {
        return documentTotal;
    }

    public void setDocumentTotal(String documentTotal)
    {
        this.documentTotal = documentTotal;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public ArrayList<DocumentLine> getLines()
    {
        return lines;
    }

    public void setLines(ArrayList<DocumentLine> lines)
    {
        this.lines = lines;
    }

}
