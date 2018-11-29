package com.example.david.sinfapplication.Activities.register_order;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.sinfapplication.R;

public class register_order_product_list_adapter extends RecyclerView.Adapter<register_order_product_list_adapter.register_order_product_list_holder>
{
    //the database is always an array it seems
    private String[] dataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class register_order_product_list_holder extends RecyclerView.ViewHolder
    {
        public TextView m_product_name;
        public register_order_product_list_holder(TextView product_name) {
            super(product_name);
            this.m_product_name = product_name;
        }
    }

    //constructor
    public register_order_product_list_adapter(String[] dataset)
    {
        this.dataset = dataset;
    }

    // TODO: change this to our view
    // Create new views (invoked by the layout manager) here we use the text view example we need to later change this to out case
    @Override
    public register_order_product_list_adapter.register_order_product_list_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create a new text view
        TextView tv = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_layout, parent, false);

        register_order_product_list_holder holder = new register_order_product_list_holder(tv);
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(register_order_product_list_adapter.register_order_product_list_holder holder, int position) {
        holder.m_product_name.setText(dataset[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.length;
    }

}
