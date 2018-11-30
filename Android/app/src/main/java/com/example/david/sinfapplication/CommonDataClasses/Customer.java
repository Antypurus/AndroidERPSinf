package com.example.david.sinfapplication.CommonDataClasses;

public class Customer
{
    /**
     * Corresponds to Cliente in primavera
     */
    private String id;
    /**
     * Corresponds to Nome in primavera
     */
    private String name;
    /**
     * Corresponds to Descricao in primavera
     */
    private String description;
    /**
     * Corresponds to Morada in primavera
     */
    private String address;
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
     * Corresponds to Telefone in primavera
     */
    private String phoneNumber;
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
     * Corresponds to NumContribuinte in primavera
     */
    private String taxNumber;
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


    public Customer(String id, String name, String description, String address, String city,
                    String postalCode, String postalCodeCity, String phoneNumber,
                    String faxNumber, String webSite, String state, String taxNumber,
                    String country, String currency, String checkingAccountDebit,
                    String pendingOrdersDebit)
    {
        this.id = id;
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

    public Customer(String name, String description, String address, String city,
                    String postalCode, String postalCodeCity, String phoneNumber,
                    String faxNumber, String webSite, String state, String taxNumber,
                    String country, String currency, String checkingAccountDebit,
                    String pendingOrdersDebit)
    {
        //TODO this.id = SHA256 ....;
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
