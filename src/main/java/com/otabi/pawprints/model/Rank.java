package com.otabi.pawprints.model;

/**
 * Created by Stephen on 11/15/2015.
 */
public enum Rank {
    TIGER(8);

    public static final Rank[] ACTIVE_RANKS = {TIGER};


    private final int scoutbookId;

    Rank(int scoutbookId) {
        this.scoutbookId = scoutbookId;
    }

    public int getScoutbookId() {
        return scoutbookId;
    }
}
