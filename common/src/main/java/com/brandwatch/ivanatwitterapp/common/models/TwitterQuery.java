package com.brandwatch.ivanatwitterapp.common.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "queries")
public class TwitterQuery {

    @Id
    private long id;

    private String hashtag;

    public TwitterQuery(long id, String hashtag) {
        this.hashtag = hashtag;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    @Override
    public String toString() {
        return "Query{" +
                "id=" + id +
                ", hashtag='" + hashtag + '\'' +
                '}';
    }
}
