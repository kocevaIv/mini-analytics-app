package com.brandwatch.ivanatwitterapp.common.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Queries")
public class TwitterQuery {

    @Id
    private long id;

    private String hashTag;

    public TwitterQuery(long id, String hashTag) {
        this.hashTag = hashTag;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    @Override
    public String toString() {
        return "Query{" +
                "id=" + id +
                ", hashTag='" + hashTag + '\'' +
                '}';
    }
}
