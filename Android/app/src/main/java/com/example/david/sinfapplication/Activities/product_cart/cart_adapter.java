package com.example.david.sinfapplication.Activities.product_cart;

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

public class cart_adapter extends RecyclerView.Adapter<cart_adapter.cart_view_holder>{

    private ArrayList<CartProduct>products = null;

    public cart_adapter(ArrayList<CartProduct> products)
    {
        this.products = products;
    }

    public static class cart_view_holder extends RecyclerView.ViewHolder
    {
        public ConstraintLayout layout;
        public Button removeButton;
        public Button quantity;
        public TextView product_name;
        public TextView product_price;

        public cart_view_holder(ConstraintLayout layout) {
            super(layout);

            this.layout = layout;

            this.removeButton = layout.findViewById(R.id.remove_product);
            this.quantity = layout.findViewById(R.id.product_quantity);
            this.product_name = layout.findViewById(R.id.product_name);
            this.product_price = layout.findViewById(R.id.product_price);
        }
    }

    @Override
    public cart_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout c_layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);

        cart_view_holder holder = new cart_view_holder(c_layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(cart_view_holder holder, int position) {

        double price = this.products.get(position).getPvp();
        price = price - (price * (this.products.get(position).getDiscount()/100.0));

        holder.product_price.setText(this.products.get(position).getCurrency()+" "+price);
        holder.product_name.setText(this.products.get(position).getDescription());
        holder.quantity.setText(""+this.products.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }


}
