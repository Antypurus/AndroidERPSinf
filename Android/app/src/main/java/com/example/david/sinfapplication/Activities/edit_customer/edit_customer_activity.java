package com.example.david.sinfapplication.Activities.edit_customer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.david.sinfapplication.Activities.register_order.register_order_activity;
import com.example.david.sinfapplication.CommonDataClasses.CustomerBasic;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;
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
        
        customer.setName(customerName);
        customer.setAddress(customerAddress);
        customer.setPhoneNumber(customerPhoneNumber);

        try
        {
            boolean result = WebAPI.editCustomer(customer);
            if (result)
                ; //TODO mostrar mensagem de success
            else
                ;//TODO mostrar mensagem de erro
        } catch (InterruptedException e)
        {
            //TODO mostrar mensagem de erro ao user
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (TimeoutException e)
        {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        // prototype, change to the checkout view
        Intent intent = new Intent(this, register_order_activity.class);
        startActivity(intent);
    }

}
