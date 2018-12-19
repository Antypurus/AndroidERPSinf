package com.example.david.sinfapplication.Activities.sale_order;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.david.sinfapplication.CommonDataClasses.DocumentLine;
import com.example.david.sinfapplication.R;

import java.util.ArrayList;

public class sale_order_adapter extends RecyclerView.Adapter<sale_order_adapter.sale_order_view_holder>{

    ArrayList<DocumentLine> products;

    public sale_order_adapter(ArrayList<DocumentLine>products)
    {
        this.products = products;
    }

    public static class sale_order_view_holder extends RecyclerView.ViewHolder {

        public ConstraintLayout layout;

        public Button quantity_button;
        public TextView product_name;
        public TextView product_price;
        public TextView product_discount;

        public sale_order_view_holder(ConstraintLayout layout) {
            super(layout);

            this.layout = layout;

            this.quantity_button = this.layout.findViewById(R.id.product_quantity);
            this.product_name = this.layout.findViewById(R.id.product_name);
            this.product_discount = this.layout.findViewById(R.id.discount);
            this.product_price = this.layout.findViewById(R.id.unit_price);
        }
    }

    @Override
    public sale_order_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout c_layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_order_product,parent,false);

        sale_order_view_holder holder = new sale_order_view_holder(c_layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(sale_order_view_holder holder, int position) {
        DocumentLine product = this.products.get(position);
        holder.product_price.setText(""+product.getUnitaryPrice()+" EUR");
        holder.product_discount.setText(""+product.getCommercialDiscount()+"%");
        holder.quantity_button.setText(""+product.getQuantity());
        holder.product_name.setText(product.getProductId());
    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }

}
