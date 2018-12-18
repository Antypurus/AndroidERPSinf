package com.example.david.sinfapplication.Activities.product_cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.david.sinfapplication.Activities.register_order.register_order_activity;
import com.example.david.sinfapplication.CommonDataClasses.CartProduct;
import com.example.david.sinfapplication.CommonDataClasses.CommonStorage;
import com.example.david.sinfapplication.R;

import java.util.ArrayList;

public class cart_activity extends AppCompatActivity {

    private RecyclerView products;
    private RecyclerView.Adapter product_adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        this.setContentView(R.layout.cart_layout);

        this.products = this.findViewById(R.id.products);
        this.products.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        this.products.setLayoutManager(this.layoutManager);

        ArrayList<CartProduct> products = CommonStorage.cartProducts;

        this.product_adapter = new cart_adapter(products);
        this.products.setAdapter(this.product_adapter);
    }

    public void goToCheckOut(View view)
    {
        Intent intent = new Intent(this,register_order_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.startActivity(intent);
    }

}
