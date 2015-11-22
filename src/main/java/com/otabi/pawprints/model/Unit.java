package com.otabi.pawprints.model;

import com.otabi.scoutbook.Den;
import com.otabi.scoutbook.DenHandler;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by Stephen on 11/16/2015.
 */
public class Unit implements DenHandler {
    final static Logger logger = LoggerFactory.getLogger(Unit.class);

    protected String unitName;
    protected int unitId;
    protected ListProperty<com.otabi.pawprints.model.Den> denListProperty = new SimpleListProperty<com.otabi.pawprints.model.Den>(FXCollections.observableArrayList());

    public Unit(String unitName, int unitId) {
        this.unitName = unitName;
        this.unitId = unitId;

        com.otabi.scoutbook.Unit.getDens(unitId);
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

    @Override
    public void processDenList(ArrayList<com.otabi.scoutbook.Den> scoutbookDenList) {
        for (com.otabi.scoutbook.Den scoutbookDen : scoutbookDenList) {
            denListProperty.add(new com.otabi.pawprints.model.Den(unitId, scoutbookDen.getId(), scoutbookDen.getName()));
        }
    }

    public Property<ObservableList<com.otabi.pawprints.model.Den>> getDenListProperty() {
        return denListProperty;
    }
}
