package com.example.david.sinfapplication.Activities.product_catalog;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.register_order.register_order_product_list_adapter;
import com.example.david.sinfapplication.CommonDataClasses.Product;
import com.example.david.sinfapplication.R;

public class product_list_adapter extends RecyclerView.Adapter<product_list_adapter.product_list_holder> {

    Product[] dataset;

    public product_list_adapter(Product[] products)
    {
        this.dataset = products;
    }

    public static class product_list_holder extends RecyclerView.ViewHolder
    {
        CardView product;

        public ImageView product_image;
        public TextView product_name;
        public TextView product_price;

        public product_list_holder(CardView product) {
            super(product);
            this.product = product;

            product_image = (ImageView) this.product.findViewById(R.id.product_image);
            product_name = (TextView) this.product.findViewById(R.id.product_name);
            product_price = (TextView) this.product.findViewById(R.id.product_price);
        }
    }

    @Override
    public product_list_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create a new text view
        CardView c_layout = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.catalog_product, parent, false);

        product_list_adapter.product_list_holder holder = new product_list_adapter.product_list_holder(c_layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(product_list_holder holder, int position) {
        holder.product_price.setText(dataset[position].getCurrency()+dataset[position].getPvp());
        holder.product_name.setText(dataset[position].getDescription());
    }

    @Override
    public int getItemCount() {
        return this.dataset.length;
    }


}
