package com.example.david.sinfapplication.Activities.Product;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.sinfapplication.CommonDataClasses.Product;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.Utils.LoadImage;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Product_activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);

        Product product = (Product) getIntent().getSerializableExtra("product");
        Bitmap productImage = product.getImage();
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

}
