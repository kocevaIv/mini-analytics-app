package com.brandwatch.ivanatwitterapp.common.models;

/**
 * MentionID class represents the composite key for the Mention class
 */
public class MentionID {

    private long id;
    private long queryId;

    public MentionID(long id, long queryId) {
        this.id = id;
        this.queryId = queryId;
    }

    public MentionID() {
    }

    public long getId() {
        return id;
    }

    public long getQueryId() {
        return queryId;
    }
}
