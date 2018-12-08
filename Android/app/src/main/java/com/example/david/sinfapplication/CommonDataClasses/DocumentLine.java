package com.example.david.sinfapplication.CommonDataClasses;

public class DocumentLine
{
    private int number;
    private String productId;
    private String productDescription;
    private int discount;
    private int commercialDiscount;
    private int taxes;
    private int quantity;
    private int unitaryPrice;
    private int netPrice;
    private String date;
    private String outDate;
    private String deliveryDate;
    private int comission;
    private String idCabecDoc;


    public DocumentLine(int number, String productId, String productDescription, int discount, int commercialDiscount, int taxes, int quantity, int unitaryPrice, int netPrice, String date, String outDate, String deliveryDate, int comission, String idCabecDoc)
    {
        this.number = number;
        this.productId = productId;
        this.productDescription = productDescription;
        this.discount = discount;
        this.commercialDiscount = commercialDiscount;
        this.taxes = taxes;
        this.quantity = quantity;
        this.unitaryPrice = unitaryPrice;
        this.netPrice = netPrice;
        this.date = date;
        this.outDate = outDate;
        this.deliveryDate = deliveryDate;
        this.comission = comission;
        this.idCabecDoc = idCabecDoc;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getProductDescription()
    {
        return productDescription;
    }

    public void setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
    }

    public int getDiscount()
    {
        return discount;
    }

    public void setDiscount(int discount)
    {
        this.discount = discount;
    }

    public int getCommercialDiscount()
    {
        return commercialDiscount;
    }

    public void setCommercialDiscount(int commercialDiscount)
    {
        this.commercialDiscount = commercialDiscount;
    }

    public int getTaxes()
    {
        return taxes;
    }

    public void setTaxes(int taxes)
    {
        this.taxes = taxes;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getUnitaryPrice()
    {
        return unitaryPrice;
    }

    public void setUnitaryPrice(int unitaryPrice)
    {
        this.unitaryPrice = unitaryPrice;
    }

    public int getNetPrice()
    {
        return netPrice;
    }

    public void setNetPrice(int netPrice)
    {
        this.netPrice = netPrice;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getOutDate()
    {
        return outDate;
    }

    public void setOutDate(String outDate)
    {
        this.outDate = outDate;
    }

    public String getDeliveryDate()
    {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }

    public int getComission()
    {
        return comission;
    }

    public void setComission(int comission)
    {
        this.comission = comission;
    }

    public String getIdCabecDoc()
    {
        return idCabecDoc;
    }

    public void setIdCabecDoc(String idCabecDoc)
    {
        this.idCabecDoc = idCabecDoc;
    }


}
