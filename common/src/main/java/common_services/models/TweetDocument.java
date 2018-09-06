package common_services.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


//the annotation @Document marks this class as a type that can be stored in mongo db database using Sping data
//we should specifiy the collection in the database where this document will be stored in
@Document(collection = "CollectedTweets")
public class TweetDocument {

    private long id;
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


    public TweetDocument(long id, String text, String fromUser, String source, Date createdAt) {
        this.id = id;
        this.text = text;
        this.fromUser = fromUser;
        this.source = source;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TweetDocument{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", source='" + source + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
