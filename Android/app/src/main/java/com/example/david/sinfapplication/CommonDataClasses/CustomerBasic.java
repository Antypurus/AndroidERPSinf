package com.example.david.sinfapplication.CommonDataClasses;

public class CustomerBasic extends Customer
{

    /**
     * Corresponds to Morada in primavera
     */
    protected String address;
    /**
     * Corresponds to Telefone in primavera
     */
    protected String phoneNumber;
    /**
     * Corresponds to NumContribuinte in primavera
     */
    protected String taxNumber;

    public CustomerBasic(String id, String name, String address, String phoneNumber, String taxNumber)
    {
        super(id, name);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
    }
}
