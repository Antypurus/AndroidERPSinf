package com.example.david.sinfapplication.Activities.customer_list;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

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

        public costumer_list_holder(ConstraintLayout costumer) {
            super(costumer);

            this.costumer = costumer;
            this.costumer_name = (TextView)this.costumer.findViewById(R.id.costumer_name);
            this.costumer_layout = (ConstraintLayout)this.costumer.findViewById(R.id.costumer_layout);
        }
    }

    @Override
    public costumer_list_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout c_layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.costumer_list_item, parent, false);

        costumer_list_adapter.costumer_list_holder adapter = new costumer_list_adapter.costumer_list_holder(c_layout);
        return null;
    }

    @Override
    public void onBindViewHolder(costumer_list_holder holder, int position) {
        holder.costumer_name.setText(this.customers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.customers.size();
    }

}
