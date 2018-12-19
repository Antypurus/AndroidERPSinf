package com.example.david.sinfapplication.Activities.view_sales_oportunity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitie;
import com.example.david.sinfapplication.R;

public class view_sales_oportunity_activity extends AppCompatActivity {

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

        description.setText(saleOpportunitie.getDescription());
        creation_date.setText("Creation Date: "+saleOpportunitie.getCreationDate());
        expiration_date.setText("Expiration Date: "+saleOpportunitie.getExpirationDate());
        entity.setText("Costumer: "+saleOpportunitie.getEntity());
        state.setText("State: "+saleOpportunitie.getSaleState());
    }

}
