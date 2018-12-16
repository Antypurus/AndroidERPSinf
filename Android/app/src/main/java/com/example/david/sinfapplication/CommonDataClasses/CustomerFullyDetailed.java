package com.example.david.sinfapplication.CommonDataClasses;

import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomerFullyDetailed extends CustomerBasic
{
    /**
     * Corresponds to Descricao in primavera
     */
    private String description;
    /**
     * Corresponds to Localidade in primavera
     */
    private String city;
    /**
     * Corresponds to CodigoPostal in primavera
     */
    private String postalCode;
    /**
     * Corresponds to LocalidadeCodigoPostal in primavera
     */
    private String postalCodeCity;
    /**
     * Corresponds to Fax in primavera
     */
    private String faxNumber;
    /**
     * Corresponds to EnderecoWeb in primavera
     */
    private String webSite;
    /**
     * Corresponds to Distrito in primavera
     */
    private String state;
    /**
     * Corresponds to Pais in primavera
     */
    private String country;
    /**
     * Corresponds to Moeda in primavera
     */
    private String currency;
    /**
     * Corresponds to DebitoContaCorrente in primavera
     */
    private String checkingAccountDebit;
    /**
     * Corresponds to DebitoEncomendasPendentes in primavera
     */
    private String pendingOrdersDebit;


    public CustomerFullyDetailed(String id, String name, String description, String address, String city,
                                 String postalCode, String postalCodeCity, String phoneNumber,
                                 String faxNumber, String webSite, String state, String taxNumber,
                                 String country, String currency, String checkingAccountDebit,
                                 String pendingOrdersDebit)
    {
        super(id, name, address, phoneNumber, taxNumber);
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.postalCodeCity = postalCodeCity;
        this.phoneNumber = phoneNumber;
        this.faxNumber = faxNumber;
        this.webSite = webSite;
        this.state = state;
        this.taxNumber = taxNumber;
        this.country = country;
        this.currency = currency;
        this.checkingAccountDebit = checkingAccountDebit;
        this.pendingOrdersDebit = pendingOrdersDebit;
    }

    public CustomerFullyDetailed(String name, String description, String address, String city,
                                 String postalCode, String postalCodeCity, String phoneNumber,
                                 String faxNumber, String webSite, String state, String taxNumber,
                                 String country, String currency, String checkingAccountDebit,
                                 String pendingOrdersDebit)
    {
        super("", name, address, phoneNumber, taxNumber);
        //TODO ver se isto ta a funcionar direito;
        String fullObjString = name + description + address + city + postalCode
                + postalCodeCity + phoneNumber + faxNumber + webSite + state
                + taxNumber + country + currency + checkingAccountDebit + pendingOrdersDebit;
        MessageDigest digest = null;
        try
        {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            Log.e("CustomerFullyDetailed", "Error computing SHA-256 of CustomerBasic");
        }
        byte hash[] = digest.digest(fullObjString.getBytes());
        StringBuilder hashSB = new StringBuilder();
        for (byte b : hash)
            hashSB.append(String.format("%02X ", b));

        this.id = hashSB.toString();
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.postalCodeCity = postalCodeCity;
        this.phoneNumber = phoneNumber;
        this.faxNumber = faxNumber;
        this.webSite = webSite;
        this.state = state;
        this.taxNumber = taxNumber;
        this.country = country;
        this.currency = currency;
        this.checkingAccountDebit = checkingAccountDebit;
        this.pendingOrdersDebit = pendingOrdersDebit;
        this.hashCode();
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String getAddress()
    {
        return address;
    }

    public String getCity()
    {
        return city;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public String getPostalCodeCity()
    {
        return postalCodeCity;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getFaxNumber()
    {
        return faxNumber;
    }

    public String getWebSite()
    {
        return webSite;
    }

    public String getState()
    {
        return state;
    }

    public String getTaxNumber()
    {
        return taxNumber;
    }

    public String getCountry()
    {
        return country;
    }

    public String getCurrency()
    {
        return currency;
    }

    public String getCheckingAccountDebit()
    {
        return checkingAccountDebit;
    }

    public String getPendingOrdersDebit()
    {
        return pendingOrdersDebit;
    }
}
