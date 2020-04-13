package com.brandwatch.ivanatwitterapp.crawler.twitter;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.brandwatch.ivanatwitterapp.common.models.Resource;
import com.brandwatch.ivanatwitterapp.crawler.kafka.Producer;

@Component
public class TwitterListener implements StatusListener {

    @Autowired
    private Producer producer;

    @Override
    public void onStatus(Status status) {
        Resource twitterResource = new Resource.Builder()
                .withId(status.getId())
                .fromAuthor(status.getUser().getName())
                .withSoruce(status.getSource())
                .withText(status.getText())
                .createdAt(Instant.ofEpochMilli(status.getCreatedAt().getTime()))
                .build();
        producer.send(twitterResource);
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {

    }

    @Override
    public void onStallWarning(StallWarning warning) {

    }

    @Override
    public void onException(Exception ex) {

    }
}
