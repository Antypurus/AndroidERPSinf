package com.example.david.sinfapplication.CommonDataClasses;

public class Customer
{
    /**
     * The id of the customer
     */
    protected String id;
    /**
     * The name of the customer
     */
    protected String name;

    public Customer(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
