package com.example.david.sinfapplication.Activities.list_sales_oportunities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.david.sinfapplication.Activities.create_sales_oportunity.create_sales_oportunity_activity;
import com.example.david.sinfapplication.R;

public class list_sales_oportunities_activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.list_sales_oportunitie);
    }

    public void goToAddSalesoportunity(View view)
    {
        Intent intent = new Intent(this,create_sales_oportunity_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}
