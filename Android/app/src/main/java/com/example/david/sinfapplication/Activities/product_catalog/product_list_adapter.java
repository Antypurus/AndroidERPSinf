package com.example.david.sinfapplication.Activities.product_catalog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.Product.Product_activity;
import com.example.david.sinfapplication.CommonDataClasses.Product;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.Utils.UtilsClass;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class product_list_adapter extends RecyclerView.Adapter<product_list_adapter.product_list_holder> {

    ArrayList<Product> dataset;

    public product_list_adapter(ArrayList<Product> products)
    {
        this.dataset = products;
    }

    public static class product_list_holder extends RecyclerView.ViewHolder
    {
        public CardView product;

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
    public void onBindViewHolder(final product_list_holder holder, final int position) {
        holder.product_price.setText(dataset.get(position).getCurrency()+dataset.get(position).getPvp());
        holder.product_name.setText(dataset.get(position).getDescription());

        Product product = dataset.get(position);
        Bitmap image = null;
        try
        {
            image = UtilsClass.loadImageFromServer(product.getImagePath());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (TimeoutException e)
        {
            e.printStackTrace();
        }
        if (image != null)
        {
            holder.product_image.setImageBitmap(image);
        }


        holder.product.setOnClickListener(view -> go_to_product(position, holder.product));
    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }

    public void go_to_product(int position, View view)
    {
        Intent intent = new Intent(view.getContext(), Product_activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("product",this.dataset.get(position));
        view.getContext().startActivity(intent);
    }

}
