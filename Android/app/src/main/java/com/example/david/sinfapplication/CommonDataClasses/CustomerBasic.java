package com.example.david.sinfapplication.CommonDataClasses;

import com.example.david.sinfapplication.Utils.UtilsClass;

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
    /**
     * Corresponds to Moeda in primavera
     */
    protected String currency;

    public CustomerBasic(String id, String name, String address, String phoneNumber, String taxNumber, String currency)
    {
        super(id, name);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
        this.currency = currency;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getTaxNumber()
    {
        return taxNumber;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public CustomerBasic(String name, String address, String phoneNumber, String taxNumber, String currency)
    {
        super("", name);
        String fullObjString = name + address + phoneNumber + taxNumber;

        this.id = UtilsClass.getSHA256OfStringInHexadecimalEncoding(fullObjString).substring(0, 12);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
        this.currency = currency;
    }
}
