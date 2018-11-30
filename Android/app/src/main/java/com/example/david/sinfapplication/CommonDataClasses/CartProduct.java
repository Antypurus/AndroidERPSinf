package com.example.david.sinfapplication.CommonDataClasses;


public class CartProduct extends Product
{
    private int quantity;

    public CartProduct(Product product, int quantity)
    {
        super(product);
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

}
