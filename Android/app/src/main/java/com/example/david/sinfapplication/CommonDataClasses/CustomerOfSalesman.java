package com.example.david.sinfapplication.CommonDataClasses;

public class CustomerOfSalesman extends Customer
{
    private String phoneNumber;

    public CustomerOfSalesman(String id, String name, String phoneNumber)
    {
        super(id, name);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
}
