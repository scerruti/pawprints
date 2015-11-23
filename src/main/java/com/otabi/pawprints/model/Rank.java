package com.otabi.pawprints.model;

/**
 * Created by Stephen on 11/15/2015.
 */
public enum Rank {

    //FIXME Correct scoutbook den ids
    TIGER(8, "Tiger Cub"),
    WOLF(8, "Wolf"),
    BEAR(8, "Bear"),
    WEBELOS(8, "Webelos");

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
