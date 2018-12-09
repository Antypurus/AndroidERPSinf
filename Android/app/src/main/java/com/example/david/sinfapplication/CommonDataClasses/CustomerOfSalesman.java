package com.example.david.sinfapplication.CommonDataClasses;

public class CustomerOfSalesman
{
    /**
     * The id of the salesman to which the customer corresponds.
     */
    private String salesmanId;
    /**
     * The id of the customer
     */
    private String id;
    /**
     * The name of the customer
     */
    private String name;

    public CustomerOfSalesman(String salesmanId, String id, String name)
    {
        this.salesmanId = salesmanId;
        this.id = id;
        this.name = name;
    }
}
