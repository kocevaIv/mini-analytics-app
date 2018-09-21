package com.brandwatch.ivanatwitterapp.common.repositories;

import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;

import java.util.List;

public interface QueryRepository {

    TwitterQuery saveQuery(TwitterQuery query);

    List<TwitterQuery> readQueries();

    TwitterQuery findQueryById(long queryId);

    boolean deleteQuery(long queryId);

    boolean updateQuery(long queryId, String hashtag);
}
