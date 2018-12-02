package com.example.david.sinfapplication.Activities.product_catalog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.david.sinfapplication.Activities.register_order.register_order_product_list_adapter;
import com.example.david.sinfapplication.CommonDataClasses.CartProduct;
import com.example.david.sinfapplication.CommonDataClasses.Product;
import com.example.david.sinfapplication.R;

public class product_catalog_activity extends Activity {

    private RecyclerView m_product_list;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_catalog);
        m_product_list = (RecyclerView) findViewById(R.id.products);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        m_product_list.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        m_product_list.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        CartProduct[] dataset = {new CartProduct(new Product("PID","Core i7","This is shit",258,2578,"$"),5),new CartProduct(new Product("PID","AMD RX590","This is shit",258,256,"$"),5)};

        mAdapter = new product_list_adapter(dataset);
        m_product_list.setAdapter(mAdapter);
    }

}
