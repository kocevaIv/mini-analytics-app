package com.brandwatch.ivanatwitterapp.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customSequences")
public class CustomSequence {

    @Id
    private String id;
    private int seq;

    public String getId() {
        return id;
    }

    public int getSeq() {
        return seq;
    }
}
