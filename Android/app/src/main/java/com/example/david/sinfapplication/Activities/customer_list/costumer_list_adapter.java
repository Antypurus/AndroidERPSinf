package com.example.david.sinfapplication.Activities.customer_list;

import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.view_customer.view_customer_activity;
import com.example.david.sinfapplication.CommonDataClasses.CustomerOfSalesman;
import com.example.david.sinfapplication.R;

import java.util.ArrayList;


public class costumer_list_adapter extends RecyclerView.Adapter<costumer_list_adapter.costumer_list_holder> {

    ArrayList<CustomerOfSalesman> customers;

    public costumer_list_adapter(ArrayList<CustomerOfSalesman> customers)
    {
        this.customers = customers;
    }

    public static class costumer_list_holder extends RecyclerView.ViewHolder
    {
        public ConstraintLayout costumer = null;
        public TextView costumer_name = null;
        public ConstraintLayout costumer_layout = null;
        public Button costumer_call_button = null;

        public costumer_list_holder(ConstraintLayout costumer) {
            super(costumer);

            this.costumer = costumer;
            this.costumer_name = (TextView)this.costumer.findViewById(R.id.costumer_name);
            this.costumer_layout = (ConstraintLayout)this.costumer.findViewById(R.id.costumer_layout);
            this.costumer_call_button = (Button)this.costumer.findViewById(R.id.costumer_call_button);
        }
    }

    @Override
    public costumer_list_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout c_layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.costumer_list_item, parent, false);

        costumer_list_adapter.costumer_list_holder adapter = new costumer_list_adapter.costumer_list_holder(c_layout);


        return adapter;
    }

    @Override
    public void onBindViewHolder(costumer_list_holder holder, int position) {
        holder.costumer_name.setText(this.customers.get(position).getName());

        holder.costumer_layout.setOnClickListener(view -> goToCustomer(holder.costumer,customers.get(position).getId()));
        holder.costumer_call_button.setOnClickListener(view -> callClient(holder.costumer_call_button,customers.get(position)));
    }

    @Override
    public int getItemCount() {
        return this.customers.size();
    }

    public void goToCustomer(View view,String client)
    {
        Intent intent = new Intent(view.getContext(),view_customer_activity.class);
        intent.putExtra("customerId",client);
        view.getContext().startActivity(intent);
    }

    public void callClient(View view,String client_number)
    {
        view.getContext().startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", client_number, null)));
    }

}
