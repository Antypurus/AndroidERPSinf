package com.example.david.sinfapplication.Activities.customer_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.david.sinfapplication.Activities.Main_Menu.main_menu_activity;
import com.example.david.sinfapplication.Activities.create_customer.create_customer_activity;
import com.example.david.sinfapplication.CommonDataClasses.CartProduct;
import com.example.david.sinfapplication.CommonDataClasses.CommonStorage;
import com.example.david.sinfapplication.CommonDataClasses.CustomerOfSalesman;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class customer_list_activity extends AppCompatActivity
{

    private RecyclerView m_costumer_list;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.costumer_list_layout);

        m_costumer_list = (RecyclerView) this.findViewById(R.id.costumer_list);
        m_costumer_list.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        m_costumer_list.setLayoutManager(mLayoutManager);

        ArrayList<CustomerOfSalesman> costumers = null;
        try
        {
            costumers = WebAPI.listCustomersOfASalesman(CommonStorage.vender_id);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (TimeoutException e)
        {
            e.printStackTrace();
        }

        if (costumers != null)
        {
            String isPerformingCheckout = getIntent().getStringExtra("performingCheckout");
            if (isPerformingCheckout != null)
            {
                if (isPerformingCheckout.equals("true"))
                {
                    ArrayList<CartProduct> cartProductArrayList = (ArrayList<CartProduct>) getIntent().getSerializableExtra("cartProductArrayList");
                    Boolean isSale = (Boolean) getIntent().getSerializableExtra("isSale");
                    mAdapter = new costumer_list_adapter(costumers, cartProductArrayList,
                            isSale);
                    m_costumer_list.setAdapter(mAdapter);
                } else
                {
                    mAdapter = new costumer_list_adapter(costumers);
                    m_costumer_list.setAdapter(mAdapter);
                }
            } else
            {
                mAdapter = new costumer_list_adapter(costumers);
                m_costumer_list.setAdapter(mAdapter);
            }
        }

        FloatingActionButton add_button = (FloatingActionButton) this.findViewById(R.id.add_costumer_button);
        add_button.setOnClickListener(view -> toAddCostumer(add_button));
    }

    public void toAddCostumer(View view)
    {
        Intent intent = new Intent(this, create_customer_activity.class);
        this.startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, main_menu_activity.class);
        startActivity(intent);
    }

}
