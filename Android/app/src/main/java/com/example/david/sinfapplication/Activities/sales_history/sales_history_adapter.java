package com.example.david.sinfapplication.Activities.sales_history;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.sale_order.sale_order_activity;
import com.example.david.sinfapplication.CommonDataClasses.Document;
import com.example.david.sinfapplication.R;

import java.util.ArrayList;

public class sales_history_adapter extends RecyclerView.Adapter<sales_history_adapter.sales_adapter_holder>{

    ArrayList<Document>documents = null;

    public sales_history_adapter(ArrayList<Document>documents)
    {
        this.documents = documents;
    }

    public static class sales_adapter_holder extends RecyclerView.ViewHolder
    {
        public ConstraintLayout layout;

        public TextView sale_date;
        public TextView sale_value;
        public TextView sale_type;

        public sales_adapter_holder(ConstraintLayout layout) {
            super(layout);

            this.layout = layout;
            this.sale_date = layout.findViewById(R.id.sale_date);
            this.sale_value = layout.findViewById(R.id.sale_value);
            this.sale_type = layout.findViewById(R.id.sale_type);
        }
    }

    @Override
    public sales_adapter_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout c_layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_history_item, parent, false);
        sales_history_adapter.sales_adapter_holder adapter = new sales_history_adapter.sales_adapter_holder(c_layout);
        return adapter;
    }

    @Override
    public void onBindViewHolder(sales_adapter_holder holder, int position) {
        holder.sale_type.setText(this.documents.get(position).getDocType());
        holder.sale_value.setText(this.documents.get(position).getDocumentTotal()+" EUR");
        holder.sale_date.setText(this.documents.get(position).getDate());

        holder.layout.setOnClickListener(view -> goToDocument(holder.layout,this.documents.get(position)));
    }

    @Override
    public int getItemCount() {
        return this.documents.size();
    }

    public void goToDocument(View view, Document document)
    {
        Intent intent = new Intent(view.getContext(),sale_order_activity.class);
        intent.putExtra("Document",document);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        view.getContext().startActivity(intent);
    }

}
