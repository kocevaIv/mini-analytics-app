package com.brandwatch.ivanatwitterapp.common.repositories;

import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;

import java.util.List;

public interface QueryRepository {

    void saveQueriesToDB(TwitterQuery query);

    List<TwitterQuery> getQueriesFromDB();

    TwitterQuery getQueriesByID(long queryId);

    boolean deleteQuery(long queryId);

    boolean updateQuery(long queryId, String hashTag);
}
