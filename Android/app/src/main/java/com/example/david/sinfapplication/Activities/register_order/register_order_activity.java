package com.example.david.sinfapplication.Activities.register_order;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.sinfapplication.Activities.product_catalog.product_catalog_activity;
import com.example.david.sinfapplication.Activities.view_customer.view_customer_activity;
import com.example.david.sinfapplication.CommonDataClasses.CartProduct;
import com.example.david.sinfapplication.CommonDataClasses.Product;
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
        CartProduct[] dataset = {new CartProduct(new Product("PID","Core i7","This is shit",258,2578,"$"),5),new CartProduct(new Product("PID","AMD RX590","This is shit",258,256,"$"),5)};
        mAdapter = new register_order_product_list_adapter(dataset);
        m_checkout_product_list_recycler_view.setAdapter(mAdapter);

        float total_price = 0;
        String currency = "";
        for(CartProduct product:dataset)
        {
            total_price += product.getPvp();
            currency = product.getCurrency();
        }

        TextView total_pay_ammount = (TextView) findViewById(R.id.product_price);
        total_pay_ammount.setText(currency+total_price);
    }

    public void sendMessage(View view)
    {
        // prototype, change to the checkout view
        Intent intent = new Intent(this, view_customer_activity.class);
        startActivity(intent);
    }

    public void goto_catalgo(View view)
    {
        Intent intent = new Intent(this, product_catalog_activity.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

}
