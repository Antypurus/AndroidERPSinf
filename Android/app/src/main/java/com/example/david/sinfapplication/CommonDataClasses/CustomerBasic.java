package com.example.david.sinfapplication.CommonDataClasses;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public CustomerBasic(String name, String address, String phoneNumber, String taxNumber, String currency)
    {
        super("", name);
        //TODO ver se isto ta a funcionar direito;
        String fullObjString = name + address + phoneNumber + taxNumber;
        MessageDigest digest = null;
        try
        {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            Log.e("CustomerBasic", "Error computing SHA-256 of CustomerBasic");
        }
        byte hash[] = digest.digest(fullObjString.getBytes());
        StringBuilder hashSB = new StringBuilder();
        for (byte b : hash)
            hashSB.append(String.format("%02X ", b));

        this.id = hashSB.toString();
        //this.id = "888442";
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
        this.currency = currency;
    }
}
