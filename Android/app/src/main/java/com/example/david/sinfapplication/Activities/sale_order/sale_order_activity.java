package com.example.david.sinfapplication.Activities.sale_order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.sales_history.sales_history_adapter;
import com.example.david.sinfapplication.CommonDataClasses.Document;
import com.example.david.sinfapplication.CommonDataClasses.DocumentLine;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class sale_order_activity extends AppCompatActivity {

    RecyclerView products;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstances)
    {
        Document doc = (Document) getIntent().getSerializableExtra("Document");

        super.onCreate(savedInstances);
        this.setContentView(R.layout.sales_order_layout);

        this.products = this.findViewById(R.id.doc_products);
        this.products.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        this.products.setLayoutManager(this.layoutManager);

        ArrayList<DocumentLine>products = null;
        try {
            products = WebAPI.viewDocumentDetails(doc.getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        this.adapter = new sale_order_adapter(products);
        this.products.setAdapter(this.adapter);

        ((TextView)this.findViewById(R.id.doc_date)).setText(doc.getDate());
        ((TextView)this.findViewById(R.id.doc_series)).setText(doc.getSeries());
        ((TextView)this.findViewById(R.id.doc_state)).setText(doc.getState());
        ((TextView)this.findViewById(R.id.doc_type)).setText(doc.getDocType());
        ((TextView)this.findViewById(R.id.doc_total)).setText(doc.getDocumentTotal());
    }

}
