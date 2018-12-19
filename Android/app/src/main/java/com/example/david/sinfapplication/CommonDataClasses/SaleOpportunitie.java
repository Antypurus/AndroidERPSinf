package com.example.david.sinfapplication.CommonDataClasses;

import com.example.david.sinfapplication.Utils.UtilsClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class SaleOpportunitie implements Serializable
{
    /**
     * corresponds to ID in primavera
     */
    private String opportunitieId;
    /**
     * corresponds to Oportunidade in primavera
     */
    private String opportunitieNumber;
    /**
     * corresponds to Descricao in primavera
     */
    private String description;
    /**
     * corresponds to DataCriacao in primavera
     */
    private String creationDate;
    /**
     * corresponds to DataExpiracao in primavera
     */
    private String expirationDate;
    /**
     * corresponds to Resumo in primavera
     */
    private String resume;
    /**
     * corresponds to Entidade in primavera
     */
    private String entity;
    /**
     * corresponds to TipoEntidade in primavera
     */
    private String entityType;
    /**
     * corresponds to EstadoVenda in primavera
     */
    private String saleState;
    private String currency;
    /**
     * corresponds to Vendedor in primavera
     */
    private String salesman;
    /**
     * corresponds to CicloVenda in primavera
     */
    private String saleCycle;
    private ArrayList<SaleOpportunitieProposal> proposals;


    public SaleOpportunitie(String description, String creationDate, String expirationDate, String resume,
                            String entity, String entityType, String saleState, String currency,
                            String salesman, String saleCycle)
    {
        this.description = description;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.resume = resume;
        this.entity = entity;
        this.entityType = entityType;
        this.saleState = saleState;
        this.currency = "EUR";
        this.salesman = salesman;
        this.saleCycle = saleCycle;
        String generatedValue = String.valueOf(new Random().nextLong());
        this.opportunitieNumber = generatedValue.substring(0, Math.min(18, generatedValue.length()));
    }

    public SaleOpportunitie(String opportunitieId, String opportunitieNumber, String description, String creationDate,
                            String expirationDate, String resume, String entity, String entityType, String saleState,
                            String currency, String salesman, String saleCycle)
    {
        this.opportunitieId = opportunitieId;
        this.opportunitieNumber = opportunitieNumber;
        this.description = description;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.resume = resume;
        this.entity = entity;
        this.entityType = entityType;
        this.saleState = saleState;
        this.currency = currency;
        this.salesman = salesman;
        this.saleCycle = saleCycle;
        String generatedValue = String.valueOf(new Random().nextLong());
        this.opportunitieNumber = generatedValue.substring(0, Math.min(18, generatedValue.length()));
    }

    public String getOpportunitieId()
    {
        return opportunitieId;
    }

    public void setOpportunitieId(String opportunitieId)
    {
        this.opportunitieId = opportunitieId;
    }

    public String getOpportunitieNumber()
    {
        return opportunitieNumber;
    }

    public void setOpportunitieNumber(String opportunitie)
    {
        this.opportunitieNumber = opportunitie;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(String creationDate)
    {
        this.creationDate = creationDate;
    }

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    public String getResume()
    {
        return resume;
    }

    public void setResume(String resume)
    {
        this.resume = resume;
    }

    public String getEntity()
    {
        return entity;
    }

    public void setEntity(String entity)
    {
        this.entity = entity;
    }

    public String getEntityType()
    {
        return entityType;
    }

    public void setEntityType(String entityType)
    {
        this.entityType = entityType;
    }

    public String getSaleState()
    {
        return saleState;
    }

    public void setSaleState(String saleState)
    {
        this.saleState = saleState;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getSalesman()
    {
        return salesman;
    }

    public void setSalesman(String salesman)
    {
        this.salesman = salesman;
    }

    public String getSaleCycle()
    {
        return saleCycle;
    }

    public void setSaleCycle(String saleCycle)
    {
        this.saleCycle = saleCycle;
    }

    public void setProposals(ArrayList<SaleOpportunitieProposal> proposals)
    {
        this.proposals = proposals;
    }

    public String getJson() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "Oportunidade", opportunitieNumber);
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "Descricao", description);
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "DataCriacao", creationDate);
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "DataExpiracao", expirationDate);
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "Resumo", resume);
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "Entidade", entity);
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "TipoEntidade", entityType);
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "EstadoVenda", saleState);
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "Moeda", currency);
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "Vendedor", salesman);
        UtilsClass.addToJsonObjectIfNotNull(jsonObject, "CicloVenda", saleCycle);

        return jsonObject.toString();
    }
}
