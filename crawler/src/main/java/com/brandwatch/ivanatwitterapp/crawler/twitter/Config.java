package com.brandwatch.ivanatwitterapp.crawler.twitter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Configuration
@EnableScheduling
public class Config {

    @Bean
    public Twitter getTwitterInstance() {
        return TwitterFactory.getSingleton();
    }

    @Bean
    public TwitterStream getTwitterStream(TwitterListener twitterListener) {
       TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
       twitterStream.addListener(twitterListener);
       twitterStream.sample("en");
       return twitterStream;
    }





}

