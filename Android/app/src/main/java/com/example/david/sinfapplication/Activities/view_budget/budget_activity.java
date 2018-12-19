package com.example.david.sinfapplication.Activities.view_budget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.david.sinfapplication.CommonDataClasses.Document;
import com.example.david.sinfapplication.CommonDataClasses.DocumentLine;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class budget_activity extends AppCompatActivity {

    RecyclerView products;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstances)
    {
        Document doc = (Document) getIntent().getSerializableExtra("Document");

        super.onCreate(savedInstances);
        this.setContentView(R.layout.budget_layout);

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

        this.adapter = new budget_adapter(products);
        this.products.setAdapter(this.adapter);

        ((TextView)this.findViewById(R.id.doc_date)).setText("Date: "+doc.getDate());
        ((TextView)this.findViewById(R.id.doc_series)).setText("Series: "+doc.getSeries());
        ((TextView)this.findViewById(R.id.doc_state)).setText("State:"+doc.getState());
        ((TextView)this.findViewById(R.id.doc_type)).setText("Document Type: "+doc.getDocType());
        ((TextView)this.findViewById(R.id.doc_total)).setText("Document Total: "+doc.getDocumentTotal()+" EUR");
    }

    public void convertToSalesOrder(View view)
    {
        //TODO: Conver to sales order
    }

}
