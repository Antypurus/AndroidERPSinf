package com.example.david.sinfapplication.CommonDataClasses;


import android.media.Image;

import java.io.Serializable;

public class Product implements Serializable
{
    /**
     * Corresponds to Artigo in primavera
     */
    private String id;
    /**
     * Corresponds to Familia in primavera
     */
    private String family;
    /**
     * Corresponds to SubFamilia in primavera
     */
    private String subfamily;
    /**
     * Corresponds to Descricao in primavera
     */
    private String description;
    /**
     * Corresponds to Observacoes in primavera
     */
    private String observations;
    private Image image;
    /**
     * Corresponds to StkActual in primavera
     */
    private int currentStock;
    /**
     * Corresponds to PVP1 in primavera
     */
    private double pvp;
    /**
     * Corresponds to Moeda in primavera
     */
    private String currency;


    public Product(String id, String family, String subfamily, String description, String observations, int currentStock, double pvp, String currency)
    {
        this.id = id;
        this.family = family;
        this.subfamily = subfamily;
        this.description = description;
        this.observations = observations;
        this.currentStock = currentStock;
        this.pvp = pvp;
        this.currency = currency;
        getImageFromObservations();
    }

    public Product(Product product)
    {
        this(new String(product.id), new String(product.family), new String(product.subfamily), new String(product.description), new String(product.observations),
                product.currentStock,  product.pvp, new String(product.currency));
    }

    private void getImageFromObservations()
    {

    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getFamily()
    {
        return family;
    }

    public void setFamily(String family)
    {
        this.family = family;
    }

    public String getSubfamily()
    {
        return subfamily;
    }

    public void setSubfamily(String subfamily)
    {
        this.subfamily = subfamily;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getObservations()
    {
        return observations;
    }

    public void setObservations(String observations)
    {
        this.observations = observations;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public int getCurrentStock()
    {
        return currentStock;
    }

    public void setCurrentStock(int currentStock)
    {
        this.currentStock = currentStock;
    }

    public double getPvp()
    {
        return pvp;
    }

    public void setPvp(double pvp)
    {
        this.pvp = pvp;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

}
