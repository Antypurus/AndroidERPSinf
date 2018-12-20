package com.example.david.sinfapplication.Activities.Product;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.product_catalog.product_catalog_activity;
import com.example.david.sinfapplication.CommonDataClasses.CartProduct;
import com.example.david.sinfapplication.CommonDataClasses.CommonStorage;
import com.example.david.sinfapplication.CommonDataClasses.Product;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.Utils.UtilsClass;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Product_activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);

        Product product = (Product) getIntent().getSerializableExtra("product");
        Bitmap productImage = null;
        try
        {
            productImage = UtilsClass.loadImageFromServer(product.getImagePath());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (TimeoutException e)
        {
            e.printStackTrace();
        }
        if(productImage != null)
            ((ImageView)this.findViewById(R.id.product_image)).setImageBitmap(productImage);

        ((TextView)this.findViewById(R.id.product_name)).setText(product.getDescription());
        if(!product.getFamily().equals("null"))
        {
            ((TextView) this.findViewById(R.id.product_family)).setText(product.getFamily());
        }
        if(!product.getSubfamily().equals("null"))
        {
            ((TextView) this.findViewById(R.id.product_subfamily)).setText(product.getSubfamily());
        }
        ((TextView)this.findViewById(R.id.product_stock)).setText((product.getCurrentStock()+" Units Available"));
        ((TextView)this.findViewById(R.id.product_price)).setText((product.getCurrency()+" "+product.getPvp()));
        if(!product.getObservations().equals("null"))
        {
            ((TextView) this.findViewById(R.id.product_description)).setText(product.getObservations());
        }
    }

    public void addProductToCart(View view)
    {
        Product product = (Product) getIntent().getSerializableExtra("product");
        String quantityString = ((TextView)this.findViewById(R.id.buy_quantity)).getText().toString();
        String discountString = ((TextView)this.findViewById(R.id.buy_discount)).getText().toString();

        if(quantityString.equals(""))
        {
            quantityString = "1";
        }

        if(discountString.equals(""))
        {
            discountString = "0";
        }

        Integer quantityInt = new Integer(quantityString);
        Integer discountInt = new Integer(discountString);

        if (quantityInt <= 0)
        {
            ((TextView) this.findViewById(R.id.error_pane)).setText("Quantity must be greater than 0");
            return;
        }

        if (! (0 <= discountInt && discountInt <= 100))
        {
            ((TextView) this.findViewById(R.id.error_pane)).setText("Discount must be between 0 and 100");
            return;
        }

        //add to cart
        CartProduct cartProduct = new CartProduct(product, quantityInt, discountInt);
        CommonStorage.cartProducts.add(cartProduct);

        //redirect to product list
        Intent intent = new Intent(this, product_catalog_activity.class);
        startActivity(intent);
    }

}
