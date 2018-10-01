package com.brandwatch.ivanatwitterapp.common.models;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//the annotation @Document marks this class as a type that can be stored in mongo db database using Sping data
//we should specifiy the collection in the database where this document will be stored in
@Document(collection = "mentions")
public class Mention {

    @Id
    private MentionID mentionId;
    /**
     * the text from the twitter post
     */
    private String text;

    /**
     * the userName that posted the tweet
     */
    private String fromUser;

    /**
     * from where the tweet was posted: web client, mobile, instagram...
     */
    private String source;

    /**
     * the date when the tweet was created
     */
    private Date createdAt;

    public Mention(MentionID mentionId, String text, String fromUser, String source, Date createdAt) {
        this.mentionId = mentionId;
        this.text = text;
        this.fromUser = fromUser;
        this.source = source;
        this.createdAt = createdAt;
    }

    public Mention() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mention mention = (Mention) o;
        return Objects.equals(mentionId.getQueryId(), mention.mentionId.getQueryId()) &&
                Objects.equals(text, mention.text) &&
                Objects.equals(fromUser, mention.fromUser) &&
                Objects.equals(source, mention.source) &&
                Objects.equals(createdAt, mention.createdAt);
    }

    public MentionID getMentionId() {
        return mentionId;
    }

    public String getText() {
        return text;
    }

    public String getFromUser() {
        return fromUser;
    }

    public String getSource() {
        return source;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
