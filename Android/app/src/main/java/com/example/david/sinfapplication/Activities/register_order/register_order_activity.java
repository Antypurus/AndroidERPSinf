package com.example.david.sinfapplication.Activities.register_order;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.sinfapplication.CommonDataClasses.CartProduct;
import com.example.david.sinfapplication.CommonDataClasses.CommonStorage;
import com.example.david.sinfapplication.R;

import java.util.ArrayList;

public class register_order_activity extends Activity
{

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
        ArrayList<CartProduct> cartProductArrayList = CommonStorage.cartProducts;
        mAdapter = new register_order_product_list_adapter(cartProductArrayList);
        m_checkout_product_list_recycler_view.setAdapter(mAdapter);

        float total_price = 0;
        String currency = "";
        for (CartProduct product : cartProductArrayList)
        {
            total_price += product.getPvp();
            currency = product.getCurrency();
        }

        TextView total_pay_ammount = findViewById(R.id.product_price);
        total_pay_ammount.setText(total_price + currency);

        if(cartProductArrayList.isEmpty())
        {
            ((TextView)findViewById(R.id.error_pane)).setText("No products in cart!");
            findViewById(R.id.finish_checkout).setClickable(false);
        }
    }

    public void finish_checkout(View view)
    {
        ArrayList<CartProduct> cartProductArrayList = CommonStorage.cartProducts;
        if(cartProductArrayList.isEmpty())
            return;

        Boolean isSale = null;
        if(((RadioButton)findViewById(R.id.saleRadioButton)).isChecked())
            isSale = true;
        else if(((RadioButton)findViewById(R.id.budgetRadioButton)).isChecked())
            isSale = false;

        if(isSale == null)
        {
            ((TextView)findViewById(R.id.error_pane)).setText("Order must be sale or budget!");
            return;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

}
