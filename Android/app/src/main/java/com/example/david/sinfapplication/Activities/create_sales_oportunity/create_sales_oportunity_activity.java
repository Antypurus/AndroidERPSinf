package com.example.david.sinfapplication.Activities.create_sales_oportunity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.view_sales_opportunity.view_sales_opportunity_activity;
import com.example.david.sinfapplication.CommonDataClasses.CommonStorage;
import com.example.david.sinfapplication.CommonDataClasses.CustomerOfSalesman;
import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitie;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.Utils.UtilsClass;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.SaleOpportunitieParser;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class create_sales_oportunity_activity extends AppCompatActivity implements SelectCustomerFragment.CustomerSelectionInterface{

    private ArrayList<CustomerOfSalesman> customers;
    private ArrayList<String> names;

    private void updateLabel(EditText date, int day,int month,int year) {
        date.setText(new StringBuilder().append(month + 1).append("/").append(day).append("/").append(year).append(" "));
    }

    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        this.setContentView(R.layout.create_sales_oportunity);

        final Calendar calendar = Calendar.getInstance();

        EditText expiration_date = (EditText)findViewById(R.id.expiration_date);
        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> updateLabel(expiration_date,dayOfMonth,month,year);

        expiration_date.setOnClickListener(v -> new DatePickerDialog(expiration_date.getContext(), date, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show());


        ArrayList<CustomerOfSalesman> customers = null;
        try {
            customers = WebAPI.listCustomersOfASalesman(CommonStorage.vender_id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        if(customers!=null) {
            ArrayList<String> names = new ArrayList<>();
            for(CustomerOfSalesman customer:customers)
            {
                names.add(customer.getName());
            }

            this.names = names;
            this.customers = customers;
        }
        else
        {
            ((TextView)this.findViewById(R.id.error_pane)).setText("Salesperson has no clients");
        }
    }

    public void create_sales_oportunity(View view)
    {
        DialogFragment select_customer = new SelectCustomerFragment();
        select_customer.show(this.getFragmentManager(), "Customers");
    }

    @Override
    public void onDialogClick(DialogFragment dialog, String custumerId)
    {
        String creationDate = UtilsClass.getDateAsString();
        String expirationDate = ((EditText) this.findViewById(R.id.expiration_date)).getText().toString();
        String description = ((EditText) this.findViewById(R.id.description)).getText().toString();

        SaleOpportunitie saleOpportunitie = SaleOpportunitieParser.createSaleOpportunitieRequestBody(description, creationDate, expirationDate, custumerId);
        try
        {
            //call for registering the sales oportunity
            WebAPI.createSaleOpportunitie(saleOpportunitie);
            Intent intent = new Intent(this, view_sales_opportunity_activity.class);
            startActivity(intent);
        } catch (TimeoutException e)
        {
            ((TextView) this.findViewById(R.id.error_pane)).setText("Network error!");
            e.printStackTrace();
        } catch (Exception e)
        {
            ((TextView) this.findViewById(R.id.error_pane)).setText("Server error!");
            e.printStackTrace();
        }


    }
}
