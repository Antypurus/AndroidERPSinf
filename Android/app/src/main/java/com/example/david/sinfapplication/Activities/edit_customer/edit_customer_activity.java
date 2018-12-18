package com.example.david.sinfapplication.Activities.edit_customer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.view_customer.view_customer_activity;
import com.example.david.sinfapplication.CommonDataClasses.CustomerBasic;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.concurrent.TimeoutException;

public class edit_customer_activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_customer);
    }

    public void sendMessage(View view)
    {
        CustomerBasic customer = (CustomerBasic) getIntent().getSerializableExtra("customer");

        String customerName = ((EditText)this.findViewById(R.id.customerName)).getText().toString();
        String customerAddress = ((EditText)this.findViewById(R.id.customerAddress)).getText().toString();
        String customerPhoneNumber = ((EditText)this.findViewById(R.id.customerPhoneNumber)).getText().toString();
        if(customerName.isEmpty() || customerAddress.isEmpty() || customerPhoneNumber.isEmpty())
        {
            ((TextView) this.findViewById(R.id.error_pane)).setText("All inputs must be filled");
            return;
        }

        customer.setName(customerName);
        customer.setAddress(customerAddress);
        customer.setPhoneNumber(customerPhoneNumber);

        try
        {
            boolean result = WebAPI.editCustomer(customer);
            if (result == false)
            {
                ((TextView) this.findViewById(R.id.error_pane)).setText("Error edit the client!");
                return;
            }
        } catch (TimeoutException e)
        {
            e.printStackTrace();
            ((TextView) this.findViewById(R.id.error_pane)).setText("Network error!");
            return;
        } catch (Exception e)
        {
            e.printStackTrace();
            ((TextView) this.findViewById(R.id.error_pane)).setText("Server error!");
            return;
        }

        // prototype, change to the checkout view
        Intent intent = new Intent(this, view_customer_activity.class);
        intent.putExtra("customerId", customer.getId());
        startActivity(intent);
    }

}
