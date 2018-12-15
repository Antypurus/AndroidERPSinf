package com.example.david.sinfapplication.CommonDataClasses;

import com.example.david.sinfapplication.Utils.UtilsClass;

import org.json.JSONException;
import org.json.JSONObject;

public class SaleOpportunitie
{
    private String opportunitieId;
    private String opportunitieNumber;
    private String description;
    private String creationDate;
    private String expirationDate;
    private String resume;
    private String entity;
    private String entityType;
    private String saleState;
    private String currency;
    private String salesman;
    private String saleCycle;


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
        this.currency = currency;
        this.salesman = salesman;
        this.saleCycle = saleCycle;
        this.opportunitieNumber = String.valueOf((description + creationDate + expirationDate + resume + entity + entityType + saleState).hashCode());
    }

    public SaleOpportunitie(String opportunitieId, String opportunitieNumber, String description, String creationDate,
                            String expirationDate, String resume, String entity, String entityType, String saleState,
                            String currency, String salesman, String saleCycle)
    {
        this.opportunitieId = opportunitieId;
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
        this.opportunitieNumber = String.valueOf((description + creationDate + expirationDate + resume + entity + entityType + saleState).hashCode());
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
