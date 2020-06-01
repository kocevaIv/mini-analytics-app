package com.brandwatch.ivanatwitterapp.resourcefilterer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.brandwatch.ivanatwitterapp.common.models.Resource;

@Component
public class UniqueResourceProducer {

    @Autowired
    private KafkaTemplate<String, Resource> kafkaTemplate;

    private static final String KAFKA_TOPIC = "unique-resources";
    private static final Logger log = LoggerFactory.getLogger(UniqueResourceProducer.class);

    public void send(Resource resource) {
        log.info("sending mention to kafka topic: ", resource.getText());
        kafkaTemplate.send(KAFKA_TOPIC, resource);
    }
}
