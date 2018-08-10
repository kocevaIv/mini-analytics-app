package com.twitter.app.controller;


import com.twitter.app.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(TwitterController.TWITTER_BASE_URI)
public class TwitterController {

    public static final String TWITTER_BASE_URI="svc/v1/tweets";

    @Autowired
    private TwitterService twitterService;

    //gets tweets in JSON format and saves them to a database
    @RequestMapping(value="{hashTag}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Tweet> getTweets(@PathVariable  String hashTag) {

        //searches twitter with the specified hashtag and gets max 20 pages
        List<Tweet> tweets=twitterService.getTweetsForHashtag(hashTag);
        twitterService.saveTweetsToDB(tweets);
        return tweets;

    }





}
