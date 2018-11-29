package com.example.david.sinfapplication;


import android.media.Image;

public class Product
{
    private String id;
    private String description;
    private String observations;
    private Image image;
    private int currentStock;
    private int pvp1;
    private int pvp2;
    private int pvp3;
    private int pvp4;
    private int pvp5;
    private int pvp6;
    private String currency;


    public Product(String id, String description, String observations, int currentStock, int pvp1,
                   int pvp2, int pvp3, int pvp4, int pvp5, int pvp6, String currency)
    {
        this.id = id;
        this.description = description;
        this.observations = observations;
        this.currentStock = currentStock;
        this.pvp1 = pvp1;
        this.pvp2 = pvp2;
        this.pvp3 = pvp3;
        this.pvp4 = pvp4;
        this.pvp5 = pvp5;
        this.pvp6 = pvp6;
        this.currency = currency;
        getImageFromObservations();
    }

    private void getImageFromObservations()
    {

    }
}
