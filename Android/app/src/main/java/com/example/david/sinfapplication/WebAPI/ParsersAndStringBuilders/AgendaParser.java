package com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders;

import com.example.david.sinfapplication.CommonDataClasses.AgendaEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AgendaParser
{

    public static ArrayList<AgendaEntry> parseListAgendaEntries(String listAgendaEntriesRequestResponse) throws
            JSONException
    {
        ArrayList<AgendaEntry> agendaEntries = new ArrayList<>();
        JSONObject dataSetObject = new JSONObject(listAgendaEntriesRequestResponse).getJSONObject("DataSet");
        JSONArray documentsArray = dataSetObject.getJSONArray("Table");

        int numberDocumentLines = documentsArray.length();
        for (int i = 0; i < numberDocumentLines; i++)
        {
            JSONObject line = documentsArray.getJSONObject(i);
            String id = line.getString("Id");
            String prioridade = line.getString("Prioridade");
            String estado = line.getString("Estado");
            String resumo = line.getString("Resumo");
            String descriçao = line.getString("Descricao");
            String entidadePrincipal = line.getString("EntidadePrincipal");
            String dataInicio = line.getString("DataInicio");
            String dataFim = line.getString("DataFim");
            String localRealizaçao = line.getString("LocalRealizacao");

            AgendaEntry agendaEntry = new AgendaEntry(id, prioridade, estado, resumo, descriçao, entidadePrincipal,
                    dataInicio, dataFim, localRealizaçao);
            agendaEntries.add(agendaEntry);
        }

        return agendaEntries;
    }

    //TODO
   /* public static boolean parseSetAgendaEntryNotes(String setAgendaEntryNotesRequestResponse)
    {

        return false;
    }*/
}
