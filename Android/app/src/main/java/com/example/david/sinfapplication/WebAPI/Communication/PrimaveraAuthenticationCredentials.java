package com.example.david.sinfapplication.WebAPI.Communication;

public class PrimaveraAuthenticationCredentials
{
    private String username;
    private String password;
    private String company;
    private String instance;
    private String grant_type;
    private String line;

    public PrimaveraAuthenticationCredentials(String username, String password, String company, String instance, String grant_type, String line)
    {
        this.username = username;
        this.password = password;
        this.company = company;
        this.instance = instance;
        this.grant_type = grant_type;
        this.line = line;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getCompany()
    {
        return company;
    }

    public String getInstance()
    {
        return instance;
    }

    public String getGrant_type()
    {
        return grant_type;
    }

    public String getLine()
    {
        return line;
    }
}
