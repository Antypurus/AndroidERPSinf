package com.example.david.sinfapplication.Activities.product_catalog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.david.sinfapplication.CommonDataClasses.CartProduct;
import com.example.david.sinfapplication.CommonDataClasses.Product;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class product_catalog_activity extends Activity {

    private RecyclerView m_product_list;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_catalog);
        m_product_list = (RecyclerView) findViewById(R.id.products);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        m_product_list.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        m_product_list.setLayoutManager(mLayoutManager);


        ArrayList<Product> products = new ArrayList<>();
        try {
            products = WebAPI.getProductsList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mAdapter = new product_list_adapter(products);
        m_product_list.setAdapter(mAdapter);
    }

}
