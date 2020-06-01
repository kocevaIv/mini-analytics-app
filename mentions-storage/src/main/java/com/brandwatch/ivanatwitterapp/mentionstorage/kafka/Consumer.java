package com.brandwatch.ivanatwitterapp.mentionstorage.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.mentionstorage.repository.MentionRepository;

@Component
public class Consumer {

    @Autowired
    SolrTemplate solrTemplate;

    @Autowired
    MentionRepository mentionRepository;
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "mentions", groupId = "mentions-group")
    public void listen(Mention mention) {

        log.info("Mention received with contents: ", mention.getText());
        mentionRepository.save(mention);
    }
}
