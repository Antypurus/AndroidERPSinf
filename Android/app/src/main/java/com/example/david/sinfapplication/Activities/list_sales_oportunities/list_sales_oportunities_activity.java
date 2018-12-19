package com.example.david.sinfapplication.Activities.list_sales_oportunities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.david.sinfapplication.Activities.create_sales_oportunity.create_sales_oportunity_activity;
import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitie;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class list_sales_oportunities_activity extends AppCompatActivity {

    RecyclerView sales_oportunities;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.list_sales_oportunitie);

        FloatingActionButton button = this.findViewById(R.id.add_sales_oportunity);
        button.setOnClickListener(view -> goToAddSalesoportunity(button));

        this.sales_oportunities = this.findViewById(R.id.sales_oportunities);
        this.sales_oportunities.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        this.sales_oportunities.setLayoutManager(this.layoutManager);

        String customerId = getIntent().getStringExtra("customerId");

        ArrayList<SaleOpportunitie> opportunities;
        try
        {
            if(customerId == null)
                opportunities = WebAPI.getAllSalesOpportunities();
            else
                opportunities = WebAPI.getSalesOpportunitiesOfCustomer(customerId);
        } catch (TimeoutException e)
        {
            e.printStackTrace();
            return;
        } catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        this.adapter = new list_sales_oportunities_adapter(opportunities);
        this.sales_oportunities.setAdapter(this.adapter);
    }

    public void goToAddSalesoportunity(View view)
    {
        Intent intent = new Intent(this,create_sales_oportunity_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}
