package com.otabi.pawprints.model;

/**
 * Created by Stephen on 11/15/2015.
 */
public enum Rank {

    TIGER(8, "Tiger Cub"),
    WOLF(9, "Wolf"),
    BEAR(10, "Bear"),
    WEBELOS(11, "Webelos");

    public static final Rank[] ACTIVE_RANKS = {TIGER};


    private final int scoutbookId;
    private final String scoutbookName;

    Rank(int scoutbookId, String scoutbookName) {
        this.scoutbookId = scoutbookId;
        this.scoutbookName = scoutbookName;
    }

    public int getScoutbookId() {
        return scoutbookId;
    }

    public String getScoutbookName() {
        return scoutbookName;
    }

    public static Rank forScoutbookName(String scoutbookName) {
        for (Rank rank : Rank.values()) {
            if (scoutbookName.equals(rank.scoutbookName)) {
                return rank;
            }
        }
        return null;
    }
}
