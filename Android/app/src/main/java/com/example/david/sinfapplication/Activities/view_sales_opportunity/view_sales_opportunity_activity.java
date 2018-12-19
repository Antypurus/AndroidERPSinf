package com.example.david.sinfapplication.Activities.view_sales_opportunity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitie;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class view_sales_opportunity_activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);

        SaleOpportunitie saleOpportunitie = (SaleOpportunitie) getIntent().getSerializableExtra("SaleOportunity");

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
    }

}
