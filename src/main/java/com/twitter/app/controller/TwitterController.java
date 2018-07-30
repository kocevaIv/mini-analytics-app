package com.twitter.app.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(TwitterController.TWITTER_BASE_URI)
public class TwitterController {

    public static final String TWITTER_BASE_URI="svc/v1/tweets";

    @Autowired
    private Twitter twitter;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //gets tweets in JSON format
    @RequestMapping(value="{hashTag}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Tweet> getTweets(@PathVariable final String hashTag)
    {
        //searches twitter with the specified hashtag and gets max 20 pages
        return twitter.searchOperations().search(hashTag,20).getTweets();
    }





}
