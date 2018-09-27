package com.brandwatch.ivanatwitterapp.common.repositories;

import java.util.List;

import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;

public interface QueryRepository {

    TwitterQuery saveQuery(TwitterQuery query);

    List<TwitterQuery> readQueries();

    TwitterQuery findQueryById(long queryId);

    boolean deleteQuery(long queryId);

    TwitterQuery updateQuery(long queryId, String hashtag);
}
