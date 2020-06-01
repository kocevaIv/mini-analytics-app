package com.brandwatch.ivanatwitterapp.resourcefilterer.kafka;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.brandwatch.ivanatwitterapp.common.models.Resource;
import com.brandwatch.ivanatwitterapp.resourcefilterer.service.ResourceFilterService;

@Component
public class ResourceConsumer {

    @Autowired
    private ResourceFilterService resourceFilterService;

    @KafkaListener(topics = "resources", groupId = "resource-filtering")
    public void consumeResources(Resource resource) throws IOException, SolrServerException {
        resourceFilterService.filterResources(resource);
    }
}
