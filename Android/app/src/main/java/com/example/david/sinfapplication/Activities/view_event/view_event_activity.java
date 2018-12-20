package com.example.david.sinfapplication.Activities.view_event;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.david.sinfapplication.CommonDataClasses.AgendaEntry;
import com.example.david.sinfapplication.R;

import java.util.HashMap;

public class view_event_activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);
        this.setContentView(R.layout.view_event);

        AgendaEntry event = (AgendaEntry) getIntent().getSerializableExtra("Event");

        String priority = event.getPrioridade();
        HashMap<String,String> prioridateMap = new HashMap<>();
        prioridateMap.put("0","Baixa");
        prioridateMap.put("1","Normal");
        prioridateMap.put("2","Alta");
        prioridateMap.put("3","Extrema");

        priority = prioridateMap.get(priority);

        ((TextView)this.findViewById(R.id.resumo)).setText(event.getResumo());
        ((TextView)this.findViewById(R.id.start_time)).setText("Start Time: "+event.getDataInicio());
        ((TextView)this.findViewById(R.id.end_time)).setText("End Time: "+event.getDataFim());
        ((TextView)this.findViewById(R.id.description)).setText(event.getDescriçao());
        ((TextView)this.findViewById(R.id.client)).setText("Client: "+event.getEntidadePrincipal());
        ((TextView)this.findViewById(R.id.location)).setText("Location: "+event.getLocalRealizaçao());
        ((TextView)this.findViewById(R.id.priority)).setText("Priority: " + priority);
    }

}
