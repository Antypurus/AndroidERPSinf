package com.example.david.sinfapplication.Activities.view_sales_opportunity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.view_sales_proposal.view_sales_proposal_activity;
import com.example.david.sinfapplication.CommonDataClasses.SaleOpportunitieProposal;
import com.example.david.sinfapplication.R;

import java.util.ArrayList;

public class view_sales_opportunity_adapter extends RecyclerView.Adapter<view_sales_opportunity_adapter.view_sales_oportunity_holder>{

    ArrayList<SaleOpportunitieProposal> proposals;

    public view_sales_opportunity_adapter(ArrayList<SaleOpportunitieProposal> proposals)
    {
        this.proposals = proposals;
    }

    public static class view_sales_oportunity_holder extends RecyclerView.ViewHolder {

        public ConstraintLayout layout;
        public TextView proposal_number;

        public view_sales_oportunity_holder(ConstraintLayout layout) {
            super(layout);

            this.layout = layout;
            this.proposal_number = this.layout.findViewById(R.id.proposal_number);
        }
    }

    @Override
    public view_sales_oportunity_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout c_layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_oportunity_proposals, parent, false);

        view_sales_oportunity_holder holder = new view_sales_oportunity_holder(c_layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(view_sales_oportunity_holder holder, int position) {
        SaleOpportunitieProposal proposal = this.proposals.get(position);

        holder.proposal_number.setText(""+proposal.getProposalNumber());
        holder.layout.setOnClickListener(view -> goToProposal(holder.layout,proposal));
    }

    @Override
    public int getItemCount() {
        return this.proposals.size();
    }

    public void goToProposal(View view, SaleOpportunitieProposal proposal)
    {
        Intent intent = new Intent(view.getContext(),view_sales_proposal_activity.class);
        intent.putExtra("SaleProposal",proposal);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        view.getContext().startActivity(intent);
    }

}
