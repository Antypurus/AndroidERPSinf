package com.example.david.sinfapplication.Activities.view_sales_proposal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.view_sales_opportunity.view_sales_opportunity_activity;
import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitieProposal;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.concurrent.TimeoutException;

public class view_sales_proposal_activity extends AppCompatActivity {

    private SaleOpportunitieProposal saleOpportunitieProposal;
    RecyclerView products;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);
        this.setContentView(R.layout.view_sale_proposal);

        saleOpportunitieProposal = (SaleOpportunitieProposal) getIntent().getSerializableExtra("SaleProposal");

        boolean success = true;
        if(saleOpportunitieProposal != null)
        {
            try
            {
                if(!WebAPI.getDetailsOfProposal(saleOpportunitieProposal))
                    success = false;
            } catch (Exception e)
            {
                e.printStackTrace();
                success = false;
            }

            if(!success)
            {
                Intent intent = new Intent(this, view_sales_opportunity_activity.class);
                startActivity(intent);
                return;
            }

            TextView title_bar = this.findViewById(R.id.proposal_number);
            title_bar.setText("" + saleOpportunitieProposal.getProposalNumber());

            if(saleOpportunitieProposal.getProductsList() != null)
            {
                this.products = this.findViewById(R.id.products);
                this.products.setHasFixedSize(true);

                this.layoutManager = new LinearLayoutManager(this);
                this.products.setLayoutManager(this.layoutManager);

                this.adapter = new view_sales_proposal_adapter(saleOpportunitieProposal.getProductsList());
                this.products.setAdapter(this.adapter);
            }

        }
        else
            success = false;

        if(!success)
        {
            Intent intent = new Intent(this, view_sales_opportunity_activity.class);
            startActivity(intent);
        }
    }

    public void transformSaleIntoOrder(View view)
    {
        try
        {
            boolean success = WebAPI.transformSaleOpportunitie(saleOpportunitieProposal.getSaleOpportunitie().getOpportunitieNumber(),
                    saleOpportunitieProposal.getProposalNumber(), "ECL");
            if(success)
                ((TextView)this.findViewById(R.id.error_pane)).setText("Transform into sale successfully!");
            else
                ((TextView)this.findViewById(R.id.error_pane)).setText("Transform into sale has failed!");

        }
        catch (TimeoutException e)
        {
            ((TextView)this.findViewById(R.id.error_pane)).setText("Network error!");
            e.printStackTrace();
        }
        catch (Exception e)
        {
            ((TextView)this.findViewById(R.id.error_pane)).setText("Server error!");
            e.printStackTrace();
        }
    }

}
