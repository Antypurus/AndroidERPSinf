package com.example.david.sinfapplication.CommonDataClasses;

public class AgendaEntry
{
    private String id;
    private String prioridade;
    private String estado;
    private String resumo;
    private String descriçao; //Descriçao1
    private String entidadePrincipal;
    private String dataInicio;
    private String dataFim;
    private String localRealizaçao;


    public AgendaEntry(String id, String prioridade, String estado, String resumo, String descriçao, String entidadePrincipal,
                       String dataInicio, String dataFim, String localRealizaçao)
    {
        this.id = id;
        this.prioridade = prioridade;
        this.estado = estado;
        this.resumo = resumo;
        this.descriçao = descriçao;
        this.entidadePrincipal = entidadePrincipal;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.localRealizaçao = localRealizaçao;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPrioridade()
    {
        return prioridade;
    }

    public void setPrioridade(String prioridade)
    {
        this.prioridade = prioridade;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public String getResumo()
    {
        return resumo;
    }

    public void setResumo(String resumo)
    {
        this.resumo = resumo;
    }

    public String getDescriçao()
    {
        return descriçao;
    }

    public void setDescriçao(String descriçao)
    {
        this.descriçao = descriçao;
    }

    public String getEntidadePrincipal()
    {
        return entidadePrincipal;
    }

    public void setEntidadePrincipal(String entidadePrincipal)
    {
        this.entidadePrincipal = entidadePrincipal;
    }

    public String getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public String getDataFim()
    {
        return dataFim;
    }

    public void setDataFim(String dataFim)
    {
        this.dataFim = dataFim;
    }

    public String getLocalRealizaçao()
    {
        return localRealizaçao;
    }

    public void setLocalRealizaçao(String localRealizaçao)
    {
        this.localRealizaçao = localRealizaçao;
    }

}
