package com.otabi.pawprints.model;

import com.otabi.scoutbook.ScoutHandler;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Stephen on 11/15/2015.
 */
public class Den {
    final static Logger logger = LoggerFactory.getLogger(Den.class);

    protected final IntegerProperty unit;
    protected final IntegerProperty den;
    protected final StringProperty denName;
    protected final Rank rank;
    protected final ObservableList<Scout> scoutList;

    public Den(int unit, int denId, String denName, Rank rank) {
        this.unit = new SimpleIntegerProperty(unit);
        this.den = new SimpleIntegerProperty(denId);
        this.denName = new SimpleStringProperty(denName);
        this.rank = rank;
        this.scoutList = FXCollections.observableArrayList(new ArrayList<Scout>());
    }

    protected void load(final ProgramAdventure adventure) {
        try {
            com.otabi.scoutbook.Den.getScouts(unit.get(), den.get(), new ScoutHandler() {
                public void processScoutMap(Map<Integer, String> scoutMap) {
                    ArrayList<Scout> newScoutList = new ArrayList<Scout>(scoutMap.size());
                    for (Integer id : scoutMap.keySet()) {
                        Scout scout = new Scout(id, scoutMap.get(id));
                        scout.loadAdventure(adventure);
                        newScoutList.add(scout);
                    }
                    Collections.sort(newScoutList);
                    scoutList.setAll(newScoutList);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAdventure(ProgramAdventure adventure) {
        if (scoutList == null || scoutList.isEmpty()) {
            load(adventure);
        } else {
            for (Scout scout : scoutList) {
                scout.loadAdventure(adventure);
            }
        }
    }

    public ObservableList<Scout> getScoutList() {
        return scoutList;
    }

    public String getDenName() {
        return denName.get();
    }

    @Override
    public String toString() {
        return String.format("%s Den %s", rank.getScoutbookName(), getDenName());
    }

    public void setName(String name) {
        this.denName.setValue(name);
    }

    public Rank getRank() {
        return rank;
    }
}
