package com.example.david.sinfapplication;


import android.media.Image;

public class Product
{
    private String id;
    private String description;
    private String observations;
    private Image image;
    private int currentStock;
    private int pvp;
    private String currency;


    public Product(String id, String description, String observations, int currentStock, int pvp, String currency)
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
}
