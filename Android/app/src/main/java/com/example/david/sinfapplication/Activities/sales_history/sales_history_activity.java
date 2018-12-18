package com.example.david.sinfapplication.Activities.sales_history;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.david.sinfapplication.R;

public class sales_history_activity extends AppCompatActivity {

    private RecyclerView m_sales_history;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.sales_history_layout);

        this.m_sales_history = (RecyclerView)this.findViewById(R.id.sales_history);
        this.m_sales_history.setHasFixedSize(true);

        this.mManager = new LinearLayoutManager(this);
        this.m_sales_history.setLayoutManager(this.mManager);

        this.mAdapter = new sales_history_adapter();
        this.m_sales_history.setAdapter(this.mAdapter);
    }

}
