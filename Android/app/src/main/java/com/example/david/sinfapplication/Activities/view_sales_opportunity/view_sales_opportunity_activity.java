package com.example.david.sinfapplication.Activities.view_sales_opportunity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.david.sinfapplication.CommonDataClasses.CommonStorage;
import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitie;
import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitieProposal;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class view_sales_opportunity_activity extends AppCompatActivity {

    private SaleOpportunitie saleOpportunitie;

    @Override
    public void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);
        this.setContentView(R.layout.view_sales_oportunity);

        saleOpportunitie = (SaleOpportunitie) getIntent().getSerializableExtra("SaleOportunity");

        TextView description = this.findViewById(R.id.description);
        TextView creation_date = this.findViewById(R.id.creation_date);
        TextView expiration_date = this.findViewById(R.id.expiration_date);
        TextView entity = this.findViewById(R.id.entity);
        TextView state = this.findViewById(R.id.sale_state);

        if(saleOpportunitie.getDescription() != null)
            description.setText(saleOpportunitie.getDescription());
        if(saleOpportunitie.getCreationDate() != null)
            creation_date.setText("Creation Date: "+saleOpportunitie.getCreationDate());
        if(saleOpportunitie.getExpirationDate() != null)
            expiration_date.setText("Expiration Date: "+saleOpportunitie.getExpirationDate());
        if(saleOpportunitie.getEntity() != null)
            entity.setText("Costumer: "+saleOpportunitie.getEntity());
        if(saleOpportunitie.getSaleState() != null)
            state.setText("State: "+saleOpportunitie.getSaleState());

        try
        {
            saleOpportunitie.setProposals(WebAPI.getAllProposalsOfASalesOpportunity(saleOpportunitie.getOpportunitieId(), saleOpportunitie));

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

        RecyclerView proposals = this.findViewById(R.id.sales_proposals);
        proposals.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        proposals.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new view_sales_opportunity_adapter(saleOpportunitie.getProposals());
        proposals.setAdapter(adapter);
    }

    public void addSaleProposal(View view)
    {
        if(CommonStorage.cartProducts.isEmpty())
        {
            ((TextView)this.findViewById(R.id.error_pane)).setText("Cart must be have products!");
            return;
        }
        try
        {
            int maxIdProposal = WebAPI.getMaxIdOfProposalThatBelongToSaleOpportunity(saleOpportunitie.getOpportunitieId());
            SaleOpportunitieProposal saleOpportunitieProposal = new SaleOpportunitieProposal(maxIdProposal + 1,
                    saleOpportunitie, CommonStorage.cartProducts);
            boolean success = WebAPI.createProposalForSaleOpportunitie(saleOpportunitieProposal);
            if(success)
            {
                CommonStorage.cartProducts.clear();
                ((TextView)this.findViewById(R.id.error_pane)).setText("Added sale proposal successfully!");
            }
            else
                ((TextView)this.findViewById(R.id.error_pane)).setText("Add sale proposal has failed!");
        } catch (TimeoutException e)
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
