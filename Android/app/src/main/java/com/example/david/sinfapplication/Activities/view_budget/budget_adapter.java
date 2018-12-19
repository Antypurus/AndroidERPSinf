package com.example.david.sinfapplication.Activities.view_budget;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.david.sinfapplication.CommonDataClasses.DocumentLine;
import com.example.david.sinfapplication.R;

import java.util.ArrayList;

public class budget_adapter extends RecyclerView.Adapter<budget_adapter.budget_holder>{

    ArrayList<DocumentLine> products;

    public budget_adapter(ArrayList<DocumentLine>products)
    {
        this.products = products;
    }

    public static class budget_holder extends RecyclerView.ViewHolder {

        public ConstraintLayout layout;

        public Button quantity_button;
        public TextView product_name;
        public TextView product_price;
        public TextView product_discount;

        public budget_holder(ConstraintLayout layout) {
            super(layout);

            this.layout = layout;

            this.quantity_button = this.layout.findViewById(R.id.product_quantity);
            this.product_name = this.layout.findViewById(R.id.product_name);
            this.product_discount = this.layout.findViewById(R.id.discount);
            this.product_price = this.layout.findViewById(R.id.unit_price);
        }
    }

    @Override
    public budget_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout c_layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_order_product,parent,false);

        budget_holder holder = new budget_holder(c_layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(budget_holder holder, int position) {
        DocumentLine product = this.products.get(position);
        holder.product_price.setText(""+product.getUnitaryPrice());
        holder.product_discount.setText(""+product.getCommercialDiscount()+"%");
        holder.quantity_button.setText(""+product.getQuantity());
        holder.product_name.setText(product.getProductId());
    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }

}
