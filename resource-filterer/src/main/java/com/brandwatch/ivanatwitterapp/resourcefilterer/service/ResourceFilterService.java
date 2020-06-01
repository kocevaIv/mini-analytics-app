package com.brandwatch.ivanatwitterapp.resourcefilterer.service;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brandwatch.ivanatwitterapp.common.models.Resource;
import com.brandwatch.ivanatwitterapp.resourcefilterer.kafka.UniqueResourceProducer;

@Service
public class ResourceFilterService {

    @Autowired
    private SolrClient client;

    @Autowired
    private UniqueResourceProducer uniqueResourceProducer;

    private static final String SOLR_RESOURCES_COLLECTION = "resources";

    public void filterResources(Resource resource) throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();

        solrQuery.set("q", "resourceId:" + resource.getResourceId());
        QueryResponse queryResponse = client.query(SOLR_RESOURCES_COLLECTION, solrQuery);
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        //means there is no such resource stored in the DB and we can save it to the resources collection
        //and send it to mentions-matcher
        if (solrDocumentList.isEmpty()) {
            SolrInputDocument solrInputDocument = new SolrInputDocument();
            solrInputDocument.addField("resourceId", resource.getResourceId());
            solrInputDocument.addField("text", resource.getText());
            solrInputDocument.addField("author", resource.getAuthor());
            solrInputDocument.addField("createdAt", resource.getCreatedAt().toString());
            solrInputDocument.addField("source", resource.getSource());
            client.add(SOLR_RESOURCES_COLLECTION, solrInputDocument);
            client.commit(SOLR_RESOURCES_COLLECTION);
            uniqueResourceProducer.send(resource);
        }
    }
}
