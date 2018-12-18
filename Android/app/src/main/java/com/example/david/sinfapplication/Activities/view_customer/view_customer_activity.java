package com.example.david.sinfapplication.Activities.view_customer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.sinfapplication.Activities.edit_customer.edit_customer_activity;
import com.example.david.sinfapplication.Activities.sales_history.sales_history_activity;
import com.example.david.sinfapplication.CommonDataClasses.CustomerFullyDetailed;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

public class view_customer_activity extends Activity
{
    private CustomerFullyDetailed customer;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_customer);
        String customerId = getIntent().getStringExtra("customerId");
        getAndSetCustomerInfoOnScreen("1"); //TODO
    }

    private void getAndSetCustomerInfoOnScreen(String customerId)
    {
        try
        {
            customer = WebAPI.viewCustomer(customerId);
            ((TextView)(this.findViewById(R.id.customer_address))).setText(customer.getAddress());
            ((TextView)(this.findViewById(R.id.customer_name))).setText(customer.getName());
            ((TextView)(this.findViewById(R.id.customer_phone))).setText(customer.getPhoneNumber());
            ((TextView)(this.findViewById(R.id.customer_tax_no))).setText(customer.getTaxNumber());
            //TODO ((TextView)(this.findViewById(R.id.customer_description))).setText(customer.getDescription());

        } catch (Exception e)
        {
            //TODO VIEW ERROR PAGE
            e.printStackTrace();
        }
    }

    public void goto_edit_customer(View view)
    {
        Intent intent = new Intent(this, edit_customer_activity.class);
        intent.putExtra("customer", customer);
        startActivity(intent);
    }

    public void goto_customer_meetings(View view)
    {
        //TODO
        Intent intent = new Intent(this, edit_customer_activity.class);
        intent.putExtra("customerId", customer.getId());
        startActivity(intent);
    }

    public void goto_customer_sales_history(View view)
    {
        //TODO
        Intent intent = new Intent(this, sales_history_activity.class);
        intent.putExtra("customerId", customer.getId());
        startActivity(intent);
    }

    public void goto_customer_orders(View view)
    {
        //TODO
        Intent intent = new Intent(this, edit_customer_activity.class);
        intent.putExtra("customerId", customer.getId());
        startActivity(intent);
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
