package com.otabi.scoutbook;

/**
 * Created by Stephen on 11/21/2015.
 */
public class Unit {
    protected final String unitDesc;
    protected final int unitId;
    protected final boolean rosterPending;

    public Unit(String unitDesc, int unitId, boolean rosterPending) {
        this.unitDesc = unitDesc;
        this.unitId = unitId;
        this.rosterPending = rosterPending;
    }

    public String getUnitDesc() {
        return unitDesc;
    }

    public int getUnitId() {
        return unitId;
    }

    public boolean getRosterPending() {
        return rosterPending;
    }
}
