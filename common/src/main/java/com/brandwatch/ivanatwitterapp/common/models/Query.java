package com.brandwatch.ivanatwitterapp.common.models;

public class Query {


    private long id;

    private String queryDefinition;

    public Query(String queryDefinition) {
        this.queryDefinition = queryDefinition;
    }

    public Query() {
    }

    public long getId() {
        return id;
    }

    public String getQueryDefinition() {
        return queryDefinition;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQueryDefinition(String queryDefinition) {
        this.queryDefinition = queryDefinition;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", queryDefinition='" + queryDefinition + '\'' +
                '}';
    }
}
