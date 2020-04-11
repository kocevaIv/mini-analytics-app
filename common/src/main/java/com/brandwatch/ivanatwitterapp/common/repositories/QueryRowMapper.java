package com.brandwatch.ivanatwitterapp.common.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.brandwatch.ivanatwitterapp.common.models.Query;

@Component
public class QueryRowMapper implements RowMapper<Query> {

    @Override
    public Query mapRow(ResultSet resultSet, int i) throws SQLException {

        Query query = new Query();
        query.setId(resultSet.getInt("id"));
        query.setQueryDefinition(resultSet.getString("definition"));
        return query;
    }
}
