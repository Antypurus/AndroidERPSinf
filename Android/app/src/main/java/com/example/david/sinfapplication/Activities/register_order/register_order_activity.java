package com.example.david.sinfapplication.Activities.register_order;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.david.sinfapplication.CartProduct;
import com.example.david.sinfapplication.Product;
import com.example.david.sinfapplication.R;

public class register_order_activity extends Activity {

    private RecyclerView m_checkout_product_list_recycler_view;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_order);
        m_checkout_product_list_recycler_view = (RecyclerView) findViewById(R.id.checkout_product_list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        m_checkout_product_list_recycler_view.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        m_checkout_product_list_recycler_view.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        CartProduct[] dataset = {new CartProduct(new Product("PID","Core i7","This is shit",258,2569.48,"$"),5),new CartProduct(new Product("PID","Core i7","This is shit",258,2569.48,"$"),5)};
        mAdapter = new register_order_product_list_adapter(dataset);
        m_checkout_product_list_recycler_view.setAdapter(mAdapter);
    }

}
