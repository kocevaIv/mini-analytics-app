package com.brandwatch.ivanatwitterapp.mentionsmatcher.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.brandwatch.ivanatwitterapp.common.models.Mention;

@Component
public class MentionProducer {

    @Autowired
    private KafkaTemplate<String, Mention> kafkaTemplate;

    private static final String KAFKA_TOPIC = "mentions";
    private static final Logger log = LoggerFactory.getLogger(MentionProducer.class);

    public void send(Mention mention) {
        log.info("sending mention to kafka topic: ", mention.getText());
        kafkaTemplate.send(KAFKA_TOPIC, mention);
    }
}
