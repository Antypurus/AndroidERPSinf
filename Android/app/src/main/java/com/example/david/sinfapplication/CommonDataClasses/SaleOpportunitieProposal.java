package com.example.david.sinfapplication.CommonDataClasses;

import java.util.ArrayList;

public class SaleOpportunitieProposal
{
    private SaleOpportunitie saleOpportunitie;
    private ArrayList<CartProduct> productsList;


    public SaleOpportunitieProposal(SaleOpportunitie saleOpportunitie, ArrayList<CartProduct> productsList)
    {
        this.saleOpportunitie = saleOpportunitie;
        this.productsList = productsList;
    }

    public SaleOpportunitie getSaleOpportunitie()
    {
        return saleOpportunitie;
    }

    public void setSaleOpportunitie(SaleOpportunitie saleOpportunitie)
    {
        this.saleOpportunitie = saleOpportunitie;
    }

    public ArrayList<CartProduct> getProductsList()
    {
        return productsList;
    }

    public void setProductsList(ArrayList<CartProduct> productsList)
    {
        this.productsList = productsList;
    }
}
