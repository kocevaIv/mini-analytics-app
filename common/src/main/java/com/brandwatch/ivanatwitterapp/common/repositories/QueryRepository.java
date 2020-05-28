package com.brandwatch.ivanatwitterapp.common.repositories;

import java.util.List;

import com.brandwatch.ivanatwitterapp.common.models.Query;

public interface QueryRepository {

    void saveQuery(Query query);

    List<Query> readQueries();

    Query findQueryById(long queryId);

    void deleteQuery(long queryId);

    void updateQuery(long queryId, String definition);
}
