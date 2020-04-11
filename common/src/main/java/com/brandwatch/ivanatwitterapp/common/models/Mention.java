package com.brandwatch.ivanatwitterapp.common.models;

import java.time.Instant;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonFormat;

//the annotation @SolrDocument marks this class as a type that can be stored in solr database using Sping data
//we should specifiy the collection in the database where this document will be stored in
@SolrDocument(collection = "mentions")
public class Mention {

    @Id
    //makes the field searchable
    @Indexed(name = "id", type = "string")
    private String mentionID;

    @Indexed(name = "queryId", type = "string")
    private long queryId;

    /**
     * the text from the twitter post
     */
    @Indexed(name = "text", type = "string")
    private String text;

    /**
     * the userName that posted the tweet
     */
    @Indexed(name = "fromUser", type = "string")
    private String fromUser;

    /**
     * from where the tweet was posted: web client, mobile, instagram...
     */
    @Indexed(name = "source", type = "string")
    private String source;

    /**
     * the date when the tweet was created
     */
    @Indexed(name = "createdAt", type = "string")
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant createdAt;

    private Mention(String mentionID, long queryId, String text, String fromUser, String source, Instant createdAt) {
        this.mentionID = mentionID;
        this.queryId = queryId;
        this.text = text;
        this.fromUser = fromUser;
        this.source = source;
        this.createdAt = createdAt;
    }

    public Mention(){

    }

    public String getMentionID() {
        return mentionID;
    }

    public String getText() {
        return text;
    }

    public long getQueryId() {
        return queryId;
    }

    public String getFromUser() {
        return fromUser;
    }

    public String getSource() {
        return source;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mention mention = (Mention) o;
        return queryId == mention.queryId &&
                Objects.equals(mentionID, mention.mentionID) &&
                Objects.equals(text, mention.text) &&
                Objects.equals(fromUser, mention.fromUser) &&
                Objects.equals(source, mention.source) &&
                Objects.equals(createdAt, mention.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mentionID, text, queryId, fromUser, source, createdAt);
    }

    public static class Builder {

        private String mentionID;
        private long queryId;
        private String text;
        private String fromUser;
        private String source;
        private Instant createdAt;

        public Builder withMentionId(String mentionId) {
            this.mentionID = mentionId;
            return this;
        }

        public Builder withQueryId(long queryId) {
            this.queryId = queryId;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder fromUser(String fromUser) {
            this.fromUser = fromUser;
            return this;
        }

        public Builder withSource(String source) {
            this.source = source;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Mention build() {
            return new Mention(mentionID, queryId, text, fromUser, source, createdAt);
        }
    }
}

