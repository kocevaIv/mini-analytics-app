package com.brandwatch.ivanatwitterapp.api.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brandwatch.ivanatwitterapp.api.exceptions.EmptyQueryDefinition;
import com.brandwatch.ivanatwitterapp.common.models.Query;
import com.brandwatch.ivanatwitterapp.common.repositories.QueryRepository;

@Service
public class QueryService {

    @Autowired
    private QueryRepository queryRepository;

    public void createQuery(String definition) {
        if (StringUtils.isNotBlank(definition)) {
            Query query = new Query(definition);
            queryRepository.saveQuery(query);
        } else {
            throw new EmptyQueryDefinition();
        }
    }

    public List<Query> getAllQueries() {
        return queryRepository.readQueries();
    }

    public Query getQueryById(long queryId) {

        return queryRepository.findQueryById(queryId);
    }

    public void deleteQueryById(long queryId) {
        queryRepository.deleteQuery(queryId);
    }

    public void updateQueryDefinition(long queryId, String definition) {
        if (StringUtils.isNotBlank(definition)) {
            queryRepository.updateQuery(queryId, definition);
        } else {
            throw new EmptyQueryDefinition();
        }
    }
}
