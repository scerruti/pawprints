package com.otabi.pawprints.model;

/**
 * Created by Stephen on 11/16/2015.
 */
public class Unit {
    protected String unitName;
    protected int unitId;

    public Unit(String unitName, int unitId) {
        this.unitName = unitName;
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    @Override
    public String toString() {
        return unitName;
    }
}
