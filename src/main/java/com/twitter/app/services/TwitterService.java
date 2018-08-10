package com.twitter.app.services;

import com.twitter.app.classes.TweetDocument;
import com.twitter.app.repositories.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterService {

    @Autowired
    private Twitter twitter;

    @Autowired
    TwitterRepository twitterRepository;

    public List<Tweet> getTweetsForHashtag(String hashTag){

        return twitter.searchOperations().search(hashTag,20).getTweets();

    }

    public void saveTweetsToDB(List<Tweet> tweets){
        //for each fetched tweet a new TweetDocument is created and stored in the database
        for (Tweet tweet:tweets) {
            TweetDocument tweetDocument=new TweetDocument(tweet.getId(),tweet.getText(),tweet.getFromUser(),tweet.getSource(),tweet.getCreatedAt());
            twitterRepository.saveTweetToDB(tweetDocument);
        }
    }


}
