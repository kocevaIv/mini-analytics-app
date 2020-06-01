package com.brandwatch.ivanatwitterapp.resourcefilterer.solr.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {

    @Bean
    public SolrClient solrClient(@Value("${spring.data.solr.host}") String solrHost) {
        return new HttpSolrClient.Builder(solrHost).build();
    }
}
