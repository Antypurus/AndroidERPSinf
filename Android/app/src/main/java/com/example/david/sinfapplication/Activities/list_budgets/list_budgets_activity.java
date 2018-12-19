package com.example.david.sinfapplication.Activities.list_budgets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.david.sinfapplication.CommonDataClasses.Document;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class list_budgets_activity extends AppCompatActivity {

    private RecyclerView m_sales_history;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        String customerId = getIntent().getStringExtra("customerId");

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.sales_history_layout);

        this.m_sales_history = (RecyclerView)this.findViewById(R.id.sales_history);
        this.m_sales_history.setHasFixedSize(true);

        this.mManager = new LinearLayoutManager(this);
        this.m_sales_history.setLayoutManager(this.mManager);

        ArrayList<Document> documents = null;
        try {
            documents = WebAPI.viewDocumentsFromCustomer(customerId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        ArrayList<Document>interesting_documents = new ArrayList<>();
        for(Document document:documents)
        {
            if(document.getDocType().equals("ORC"))
            {
                interesting_documents.add(document);
            }
        }

        this.mAdapter = new list_budgets_adapter(interesting_documents);
        this.m_sales_history.setAdapter(this.mAdapter);
    }

}
