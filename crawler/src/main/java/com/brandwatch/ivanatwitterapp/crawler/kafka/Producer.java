package com.brandwatch.ivanatwitterapp.crawler.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.brandwatch.ivanatwitterapp.common.models.Resource;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String, Resource> kafkaTemplate;

    private static final String KAFKA_TOPIC = "resources";
    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    public void send(Resource resource) {
        log.info("sending resource: ", resource.getText());
        kafkaTemplate.send(KAFKA_TOPIC, String.valueOf(System.currentTimeMillis()), resource);
    }
}
