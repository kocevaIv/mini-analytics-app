package com.brandwatch.ivanatwitterapp.common.models;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Represents a crawled tweet from Twitter's API
 */

@SolrDocument(collection = "resources")
public class Resource {

    @Id
    private long resourceId;

    @Indexed(name = "text", type = "string")
    private String text;

    @Indexed(name = "author", type = "string")
    private String author;

    @Indexed(name = "createdAt", type = "string")
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant createdAt;

    @Indexed(name = "soruce", type = "string")
    private String source;

    public Resource() {

    }

    private Resource(long resourceId, String text, String author, Instant createdAt, String source) {
        this.resourceId = resourceId;
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
        this.source = source;
    }

    public long getResourceId() {
        return resourceId;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getSource() {
        return source;
    }

    public static class Builder {

        private long resourceId;

        private String text;

        private String author;

        private Instant createdAt;

        private String source;

        public Builder withId(long resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder fromAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withSoruce(String source) {
            this.source = source;
            return this;
        }

        public Resource build() {
            return new Resource(resourceId, text, author, createdAt, source);
        }
    }
}
