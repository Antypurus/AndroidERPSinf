package com.example.david.sinfapplication.CommonDataClasses;


public class CartProduct extends Product
{
    private int quantity;
    private int discount;

    public CartProduct(Product product, int quantity, int discount)
    {
        super(product);
        this.quantity = quantity;
        this.discount = discount;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public int getDiscount()
    {
        return discount;
    }
}
