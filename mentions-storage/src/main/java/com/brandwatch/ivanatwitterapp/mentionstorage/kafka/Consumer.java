package com.brandwatch.ivanatwitterapp.mentionstorage.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.kafka.annotation.KafkaListener;

import com.brandwatch.ivanatwitterapp.common.models.Resource;

@Configuration
public class Consumer {

    @Autowired
    SolrTemplate solrTemplate;

    @KafkaListener(topics = "resources", groupId = "resources-group")
    public void listen(Resource resource) {

        System.out.println("Twitter resource received with contents: " + resource.getText());
    }
}
