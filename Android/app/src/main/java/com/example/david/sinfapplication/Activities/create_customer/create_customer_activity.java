package com.example.david.sinfapplication.Activities.create_customer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.sinfapplication.Activities.register_order.register_order_activity;
import com.example.david.sinfapplication.CommonDataClasses.CustomerBasic;
import com.example.david.sinfapplication.CommonDataClasses.CustomerFullyDetailed;
import com.example.david.sinfapplication.CommonDataClasses.CustomerOfSalesman;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class create_customer_activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_customer);
    }

    public void sendMessage(View view)
    {
        String customerName = ((EditText)this.findViewById(R.id.customerName)).getText().toString();
        String customerAddress = ((EditText)this.findViewById(R.id.customerAddress)).getText().toString();
        String customerEmail = ((EditText)this.findViewById(R.id.customerEmail)).getText().toString();
        String customerPhoneNumber = ((EditText)this.findViewById(R.id.customerPhoneNumber)).getText().toString();
        String customerTaxNumber = ((EditText)this.findViewById(R.id.customerTaxNumber)).getText().toString();

        CustomerBasic customerBasic = new CustomerBasic(customerName, customerAddress,
                customerEmail, customerPhoneNumber, customerTaxNumber);

        try
        {
            boolean result = WebAPI.addCustomer(customerBasic);
            if (result)
                ;
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

        //CustomerFullyDetailed customerOfSalesman = new CustomerFullyDetailed(customerName, re)
          // prototype, change to the checkout view
      Intent intent = new Intent(this, register_order_activity.class);
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
