package com.example.david.sinfapplication.Activities.view_sales_proposal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitieProposal;
import com.example.david.sinfapplication.R;

public class view_sales_proposal_activity extends AppCompatActivity {

    RecyclerView products;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);
        this.setContentView(R.layout.view_sale_proposal);

        SaleOpportunitieProposal proposal = (SaleOpportunitieProposal) getIntent().getSerializableExtra("SaleProposal");

        TextView title_bar = this.findViewById(R.id.proposal_number);
        title_bar.setText(""+proposal.getProposalNumber());

        this.products = this.findViewById(R.id.products);
        this.products.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        this.products.setLayoutManager(this.layoutManager);

        this.adapter = new view_sales_proposal_adapter(proposal.getProductsList());
        this.products.setAdapter(this.adapter);
    }

}
