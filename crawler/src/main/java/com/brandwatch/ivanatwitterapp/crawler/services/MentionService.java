package com.brandwatch.ivanatwitterapp.crawler.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.models.Resource;
import com.brandwatch.ivanatwitterapp.mentionstorage.repository.MentionRepository;

@Service
public class MentionService {

    @Autowired
    private Twitter twitter;

    @Autowired
    private MentionRepository mentionRepository;


//    public List<Tweet> findMentionsForHashtag(String hashtag) {
//        return twitter.searchOperations().search(hashtag, 20).getTweets();
//    }

    public void saveMentions(List<Resource> resources, long queryId) {
        //for each fetched tweet a new Mention is created and stored in the database
        for (Resource resource : resources) {
            String mentionID = resource.getResourceId()+"!"+queryId;
            Mention mention = new Mention.Builder()
                    .withMentionId(mentionID)
                    .withQueryId(queryId)
                    .withText(resource.getText())
                    .fromUser(resource.getAuthor())
                    .withSource(resource.getSource())
                    .createdAt(resource.getCreatedAt())
                    .build();
            mentionRepository.save(mention);
        }
    }

    public List<Resource> findResourcesForHashTag(String hashtag) throws TwitterException {
        Query query = new Query(hashtag);
        query.setCount(100);
        QueryResult queryResult = twitter.search(query);
        return queryResult.getTweets().stream()
                .map(this::mapTweetToResource)
                .collect(Collectors.toList());
    }

    public Resource mapTweetToResource(Status tweet) {
        Resource twitterResource = new Resource.Builder()
                .withId(tweet.getId())
                .fromAuthor(tweet.getUser().getName())
                .withSoruce(tweet.getSource())
                .withText(tweet.getText())
                .createdAt(Instant.ofEpochMilli(tweet.getCreatedAt().getTime()))
                .build();
        return twitterResource;
    }
}
