package com.brandwatch.ivanatwitterapp.common.repositories;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;

import com.brandwatch.ivanatwitterapp.common.models.Query;

@Repository
public class QueryRepositoryImpl implements QueryRepository {

    private static final String QUERIES_TABLE = "queries";
    private static final String ALL_FIELDS = " id,definition ";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final QueryRowMapper queryRowMapper;

    @Autowired
    public QueryRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, QueryRowMapper queryRowMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.queryRowMapper = queryRowMapper;
    }

    @Override
    public void saveQuery(Query query) {

        String sql = " INSERT INTO " + QUERIES_TABLE +
                " (definition,created_on) VALUES (:definition, :created_on)";

        Map<String, Object> parameters = ImmutableMap.of(
                "definition", query.getQueryDefinition(),
                "created_on", Timestamp.from(Instant.now())
        );

        namedParameterJdbcTemplate.update(sql, parameters);
    }

    @Override
    public List<Query> readQueries() {
        String sql = " SELECT " + ALL_FIELDS + " FROM " + QUERIES_TABLE;
        return namedParameterJdbcTemplate.query(sql, queryRowMapper);
    }

    @Override
    public Query findQueryById(long queryId) {
        String sql = " SELECT " + ALL_FIELDS + " FROM " + QUERIES_TABLE + " WHERE "
                + " id = :queryId ";

        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("queryId", queryId), queryRowMapper);
    }

    @Override
    public void deleteQuery(long queryId) {
        String sql = " DELETE FROM " + QUERIES_TABLE + " WHERE id = :queryId ";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("queryId", queryId));
    }

    @Override
    public void updateQuery(long queryId, String definition) {
        String sql = " UPDATE " + QUERIES_TABLE + " SET definition = :definition WHERE id = :queryId ";
        Map<String, Object> parameters = ImmutableMap.of(
                "definition", definition,
                "id", queryId
        );
        namedParameterJdbcTemplate.update(sql, parameters);
    }
}
