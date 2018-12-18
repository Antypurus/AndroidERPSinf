package com.example.david.sinfapplication.Activities.sales_history;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class sales_history_adapter extends RecyclerView.Adapter<sales_history_adapter.sales_adapter_holder>{

    public static class sales_adapter_holder extends RecyclerView.ViewHolder
    {
        public sales_adapter_holder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public sales_adapter_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(sales_adapter_holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
