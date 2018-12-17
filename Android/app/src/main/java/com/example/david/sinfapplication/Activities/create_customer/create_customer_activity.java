package com.example.david.sinfapplication.Activities.create_customer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.sinfapplication.Activities.customer_list.customer_list_activity;
import com.example.david.sinfapplication.CommonDataClasses.CustomerBasic;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.Utils.UtilsClass;
import com.example.david.sinfapplication.WebAPI.WebAPI;

public class create_customer_activity extends Activity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_customer);
    }

    public void sendMessage(View view)
    {
        String customerName = ((EditText) this.findViewById(R.id.customerName)).getText().toString();
        String customerAddress = ((EditText) this.findViewById(R.id.customerAddress)).getText().toString();
        String customerPhoneNumber = ((EditText) this.findViewById(R.id.customerPhoneNumber)).getText().toString();
        String customerTaxNumber = ((EditText) this.findViewById(R.id.customerTaxNumber)).getText().toString();

        if(customerName.isEmpty() || customerAddress.isEmpty() || customerPhoneNumber.isEmpty() ||
                customerTaxNumber.isEmpty() || customerTaxNumber.isEmpty())
        {
            ((TextView) this.findViewById(R.id.error_pane)).setText("All inputs must be filled");
            return;
        }

        if (UtilsClass.IsNifValid(customerTaxNumber) == false)
        {
            ((TextView) this.findViewById(R.id.error_pane)).setText("Invalid NIF");
            return;
        }

        CustomerBasic customerBasic = new CustomerBasic(customerName, customerAddress,
                customerPhoneNumber, customerTaxNumber, "EUR");

        try
        {
            boolean result = WebAPI.addCustomer(customerBasic);
            if (result == false)
            {
                ((TextView) this.findViewById(R.id.error_pane)).setText("Error adding the client!");
                return;
            }

        } catch (InterruptedException e)
        {
            ((TextView) this.findViewById(R.id.error_pane)).setText("Network error!");
            e.printStackTrace();
            return;
        } catch (Exception e)
        {
            ((TextView) this.findViewById(R.id.error_pane)).setText("Server error");
            e.printStackTrace();
            return;
        }

        Intent intent = new Intent(this, customer_list_activity.class);
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
