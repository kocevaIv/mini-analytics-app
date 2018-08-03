package com.twitter.app.controller;


import org.springframework.http.MediaType;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwitterOpsController
{

    //by creating a twitter template with the generated access tokens for our twitter app we can access the twitter API
    private final TwitterTemplate template=new TwitterTemplate("bQMOAbWbR6UwBrElxxNvVRJKq","zYcUI6Uys9AIfQf9yECsygrnWkU9JdJCFauymZ7dFbSdu4BSuX","132150991-PkdwT9KE0t7qDMURReBFpzeOqq7x1fEiCUc1fXso","EyttgoNhJRiCDRBfrak5KNb1lMiwKa1BNlFmRqiTwF3nF");

    @RequestMapping(value="twitterFriends",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CursoredList<TwitterProfile> getFriends()
    {
        //gets all the friends the user has on twitter
        return template.friendOperations().getFriends();

    }

    @RequestMapping(value="screenName",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getMyTwitterName()
    {

        String profileId=template.userOperations().getScreenName();
        return profileId;
    }





}
