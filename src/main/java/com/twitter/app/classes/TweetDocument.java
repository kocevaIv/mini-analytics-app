package com.twitter.app.classes;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.social.twitter.api.Tweet;

import java.util.Date;

//the class TweetDocument is only created as an extended classs from Tweet so it can be used as a Document type
//the annotation @Document marks this class as a type that can be stored in mongo db database using Sping data
//we should specifiy the collection in the database where this document will be stored in
@Document(collection = "CollectedTweets")
public class TweetDocument extends Tweet
{
    public TweetDocument(long id, String text, Date createdAt, String fromUser, String profileImageUrl, Long toUserId, long fromUserId, String languageCode, String source) {
        super(id, text, createdAt, fromUser, profileImageUrl, toUserId, fromUserId, languageCode, source);
    }


}

