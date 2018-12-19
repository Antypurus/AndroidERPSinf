package com.example.david.sinfapplication.Activities.Main_Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.david.sinfapplication.Activities.Agenda.agenda_activity;
import com.example.david.sinfapplication.Activities.customer_list.customer_list_activity;
import com.example.david.sinfapplication.Activities.list_sales_oportunities.list_sales_oportunities_activity;
import com.example.david.sinfapplication.Activities.product_cart.cart_activity;
import com.example.david.sinfapplication.Activities.product_catalog.product_catalog_activity;
import com.example.david.sinfapplication.R;

public class main_menu_activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void toCatalog(View view)
    {
        Intent intent = new Intent(this,product_catalog_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.startActivity(intent);
    }

    public void toCostumerList(View view)
    {
        Intent intent = new Intent(this,customer_list_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.startActivity(intent);
    }

    public void toCart(View view)
    {
        Intent intent = new Intent(this,cart_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.startActivity(intent);
    }

    public void goToSalesOportunities(View view)
    {
        Intent intent = new Intent(this,list_sales_oportunities_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.startActivity(intent);
    }

    public void goToAgenda(View view)
    {
        Intent intent = new Intent(this,agenda_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.startActivity(intent);
    }

}
