package com.example.david.sinfapplication.Activities.Agenda;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.view_event.view_event_activity;
import com.example.david.sinfapplication.CommonDataClasses.AgendaEntry;
import com.example.david.sinfapplication.R;

import java.util.ArrayList;

public class agenda_adapter extends RecyclerView.Adapter<agenda_adapter.agenda_view_holder>{

    ArrayList<AgendaEntry> entries;

    public agenda_adapter(ArrayList<AgendaEntry> entries)
    {
        this.entries = entries;
    }

    public static class agenda_view_holder extends RecyclerView.ViewHolder {

        public ConstraintLayout layout;
        public TextView timeline;
        public TextView event_name;

        public agenda_view_holder(ConstraintLayout layout) {
            super(layout);

            this.layout = layout;
            this.timeline = layout.findViewById(R.id.timeline);
            this.event_name = layout.findViewById(R.id.event_name);
        }
    }

    @Override
    public agenda_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout c_layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_item, parent, false);
        agenda_view_holder holder = new agenda_view_holder(c_layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(agenda_view_holder holder, int position) {
        AgendaEntry entry = this.entries.get(position);

        holder.timeline.setText(entry.getDataInicio()+" - "+entry.getDataFim());
        holder.event_name.setText(entry.getResumo());
        holder.layout.setOnClickListener(view -> goToEvent(holder.layout,entry));
    }

    @Override
    public int getItemCount() {
        return this.entries.size();
    }

    public void goToEvent(View view,AgendaEntry event)
    {
        Intent intent = new Intent(view.getContext(),view_event_activity.class);
        intent.putExtra("Event",event);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        view.getContext().startActivity(intent);
    }

}
