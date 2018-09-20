package com.brandwatch.ivanatwitterapp.crawler.services;

import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.models.MentionID;
import com.brandwatch.ivanatwitterapp.common.repositories.MongoMentionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentionService {

    @Autowired
    private Twitter twitter;

    @Autowired
    private MongoMentionRepository twitterRepository;

    public List<Tweet> getMentionsForHashtag(String hashtag) {
        return twitter.searchOperations().search(hashtag, 20).getTweets();
    }

    public void saveMentions(List<Tweet> tweets, long queryId) {
        //for each fetched tweet a new Mention is created and stored in the database
        for (Tweet tweet : tweets) {
            MentionID mentionID = new MentionID(tweet.getId(), queryId);
            Mention mention = new Mention(mentionID, tweet.getText(), tweet.getFromUser(), tweet.getSource(), tweet.getCreatedAt());
            twitterRepository.saveMention(mention);
        }
    }
}
