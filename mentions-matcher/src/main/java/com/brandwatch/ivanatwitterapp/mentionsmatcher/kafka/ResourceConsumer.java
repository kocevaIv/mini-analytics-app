package com.brandwatch.ivanatwitterapp.mentionsmatcher.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.brandwatch.ivanatwitterapp.common.models.Resource;
import com.brandwatch.ivanatwitterapp.mentionsmatcher.services.LuceneService;

@Component
public class ResourceConsumer {

    @Autowired
    LuceneService luceneService;
    private static final Logger log = LoggerFactory.getLogger(ResourceConsumer.class);

    @KafkaListener(topics = "unique-resources", groupId = "resource-matching")
    public void consumeResources(Resource resource) {
        log.info("Resource consumed from kafka topic resources");
        luceneService.addResourceInQueue(resource);
    }
}
