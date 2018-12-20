package com.example.david.sinfapplication.Activities.Agenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.david.sinfapplication.CommonDataClasses.AgendaEntry;
import com.example.david.sinfapplication.CommonDataClasses.CommonStorage;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

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

        String customerId = getIntent().getStringExtra("customerId");
        ArrayList<AgendaEntry> events = new ArrayList<>();
        try {
            if(customerId == null)
                events = WebAPI.getAllAgendaEntries(CommonStorage.vender_id);
            else
                events = getAgendaEntriesFromCustomerWithGivenId(WebAPI.getAllAgendaEntries(CommonStorage.vender_id), customerId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        RecyclerView.Adapter adapter = new agenda_adapter(events);
        agenda_events.setAdapter(adapter);
    }

    private ArrayList<AgendaEntry> getAgendaEntriesFromCustomerWithGivenId(ArrayList<AgendaEntry> allAgendaEntries, String customerId)
    {
        ArrayList<AgendaEntry> result = new ArrayList<>();
        for(AgendaEntry agendaEntry : allAgendaEntries)
        {
            String entidadePrincipal = agendaEntry.getEntidadePrincipal();
            if(entidadePrincipal == null)
                continue;
            if(entidadePrincipal.equals(customerId))
                result.add(agendaEntry);
        }

        return result;
    }

}
