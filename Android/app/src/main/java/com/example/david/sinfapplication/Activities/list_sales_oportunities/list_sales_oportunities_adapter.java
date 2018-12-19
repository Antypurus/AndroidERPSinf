package com.example.david.sinfapplication.Activities.list_sales_oportunities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitie;
import com.example.david.sinfapplication.R;

import java.util.ArrayList;

public class list_sales_oportunities_adapter extends RecyclerView.Adapter<list_sales_oportunities_adapter.list_sales_oportunities_holder>{

    ArrayList<SaleOpportunitie> salesOportunities;

    public list_sales_oportunities_adapter(ArrayList<SaleOpportunitie> salesOportunities)
    {
        this.salesOportunities = salesOportunities;
    }

    public static class list_sales_oportunities_holder extends RecyclerView.ViewHolder {

        public TextView timeline;
        public TextView name;

        public list_sales_oportunities_holder(ConstraintLayout layout) {
            super(layout);

            this.timeline = layout.findViewById(R.id.timeline);
            this.name = layout.findViewById(R.id.costumer_name);
        }
    }

    @Override
    public list_sales_oportunities_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout c_layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sales_oportunities_item, parent, false);

        list_sales_oportunities_holder holder = new list_sales_oportunities_holder(c_layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(list_sales_oportunities_holder holder, int position) {
        SaleOpportunitie oportunity = this.salesOportunities.get(position);

        holder.timeline.setText(oportunity.getCreationDate()+" - "+oportunity.getExpirationDate());
        holder.name.setText(oportunity.getEntity());
    }

    @Override
    public int getItemCount() {
        return this.salesOportunities.size();
    }

}
