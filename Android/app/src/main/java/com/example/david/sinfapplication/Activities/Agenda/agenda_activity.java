package com.example.david.sinfapplication.Activities.Agenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.david.sinfapplication.CommonDataClasses.AgendaEntry;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.ArrayList;

public class agenda_activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);
        this.setContentView(R.layout.agenda);

        RecyclerView agenda_events = this.findViewById(R.id.agenda);
        agenda_events.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        agenda_events.setLayoutManager(layoutManager);

        ArrayList<AgendaEntry> events = new ArrayList<>();

        RecyclerView.Adapter adapter = new agenda_adapter(events);
        agenda_events.setAdapter(adapter);
    }

}
