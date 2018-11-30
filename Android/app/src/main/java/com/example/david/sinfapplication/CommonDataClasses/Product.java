package com.example.david.sinfapplication.CommonDataClasses;


import android.media.Image;

public class Product
{
    private String id;
    private String description;
    private String observations;
    private Image image;
    private int currentStock;
    private double pvp;
    private String currency;


    public Product(String id, String description, String observations, int currentStock, double pvp, String currency)
    {
        this.id = id;
        this.description = description;
        this.observations = observations;
        this.currentStock = currentStock;
        this.pvp = pvp;
        this.currency = currency;
        getImageFromObservations();
    }

    public Product(Product product)
    {
        this(new String(product.id), new String(product.description), new String(product.observations),
                product.currentStock,  product.pvp, new String(product.currency));
    }

    private void getImageFromObservations()
    {

    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getObservations() {
        return observations;
    }

    public Image getImage() {
        return image;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public double getPvp() {
        return pvp;
    }

    public String getCurrency() {
        return currency;
    }
}
