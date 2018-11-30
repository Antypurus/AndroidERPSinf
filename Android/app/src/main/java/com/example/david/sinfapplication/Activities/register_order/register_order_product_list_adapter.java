package com.example.david.sinfapplication.Activities.register_order;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.david.sinfapplication.CommonDataClasses.CartProduct;
import com.example.david.sinfapplication.R;

public class register_order_product_list_adapter extends RecyclerView.Adapter<register_order_product_list_adapter.register_order_product_list_holder>
{
    //the database is always an array it seems
    private CartProduct[] dataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class register_order_product_list_holder extends RecyclerView.ViewHolder
    {
        public ConstraintLayout product;
        public TextView product_name;
        public TextView product_price;
        public Button product_ammount;

        public register_order_product_list_holder(ConstraintLayout product) {
            super(product);
            this.product = product;

            this.product_name = (TextView) this.product.findViewById(R.id.product_name);
            this.product_price = (TextView) this.product.findViewById(R.id.product_price);
            this.product_ammount = (Button) this.product.findViewById(R.id.product_ammount);
        }
    }

    //constructor
    public register_order_product_list_adapter(CartProduct[] dataset)
    {
        this.dataset = dataset;
    }

    // TODO: change this to our view
    // Create new views (invoked by the layout manager) here we use the text view example we need to later change this to out case
    @Override
    public register_order_product_list_adapter.register_order_product_list_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create a new text view
        ConstraintLayout c_layout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_layout, parent, false);

        register_order_product_list_holder holder = new register_order_product_list_holder(c_layout);
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(register_order_product_list_adapter.register_order_product_list_holder holder, int position) {
       holder.product_name.setText(dataset[position].getDescription());
       holder.product_ammount.setText(""+dataset[position].getQuantity());
       holder.product_price.setText(""+dataset[position].getCurrency() + dataset[position].getPvp());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.length;
    }

}
