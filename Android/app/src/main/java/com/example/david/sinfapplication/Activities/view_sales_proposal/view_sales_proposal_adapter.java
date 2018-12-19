package com.example.david.sinfapplication.Activities.view_sales_proposal;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.david.sinfapplication.CommonDataClasses.CartProduct;
import com.example.david.sinfapplication.R;

import java.util.ArrayList;

public class view_sales_proposal_adapter extends RecyclerView.Adapter<view_sales_proposal_adapter.view_sales_proposal_holder>{

    ArrayList<CartProduct> products;

    public view_sales_proposal_adapter(ArrayList<CartProduct> products)
    {
        this.products = products;
    }

    public static class view_sales_proposal_holder extends RecyclerView.ViewHolder {

        public ConstraintLayout layout;
        public Button quantity;
        public TextView name;
        public TextView price;

        public view_sales_proposal_holder(ConstraintLayout layout) {
            super(layout);

            this.layout = layout;
            this.name = this.layout.findViewById(R.id.product_name);
            this.quantity = this.layout.findViewById(R.id.product_quantity);
            this.price = this.layout.findViewById(R.id.product_price);
        }
    }

    @Override
    public view_sales_proposal_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout c_layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_proposal_item, parent, false);

        view_sales_proposal_holder holder = new view_sales_proposal_holder(c_layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(view_sales_proposal_holder holder, int position) {
        CartProduct product = this.products.get(position);

        holder.price.setText(product.getCurrency()+" "+product.getPvp());
        holder.name.setText(product.getDescription());
        holder.quantity.setText(""+product.getQuantity());
    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }

}
