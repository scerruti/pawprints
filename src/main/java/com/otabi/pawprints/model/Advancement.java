package com.otabi.pawprints.model;

import com.otabi.scoutbook.AdventureHandler;
import com.otabi.scoutbook.RequirementStatus;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stephen on 11/15/2015.
 */
public class Advancement {
    final static Logger logger = LoggerFactory.getLogger(Advancement.class);

    protected final IntegerProperty memberId;
    protected final ObjectProperty<Rank> rank;
    protected Map<Integer, Adventure> adventureMap;

    public Advancement(int memberId, Rank rank) {
        logger.debug("creating advancement {} for scout {}",rank, memberId);
        this.memberId = new SimpleIntegerProperty(memberId);
        this.rank = new SimpleObjectProperty<Rank>(rank);
        initializeAdventureMap();
    }

    private void initializeAdventureMap() {
        logger.debug("initializing {} adventure map for {}", rank, memberId);
        int memberId = this.memberId.get(); Rank rank = this.rank.get();
        if (adventureMap == null || adventureMap.isEmpty()) {
            Map<Integer, ProgramAdventure> programAdventureMap = Program.getAdventureMapByRank(rank);
            this.adventureMap = new HashMap<Integer, Adventure>(programAdventureMap.size());
            for (ProgramAdventure programAdventure : programAdventureMap.values()) {
                Adventure newAdventure = new Adventure(memberId, rank, programAdventure.getAdventureId(), RequirementStatus.LOADING);
                adventureMap.put(programAdventure.getAdventureId(), newAdventure);
            }
        }
    }

    public void loadAdventure(int adventureId) {
        logger.debug("loading adventure {} for {}", adventureId, memberId);
        initializeAdventureMap();
        if (adventureMap.get(adventureId).getStatus().equals(RequirementStatus.LOADING)) {
            load(adventureId);
        } else {
            adventureMap.get(adventureId).load();
        }
    }

    protected void load(final int adventureId) {
        logger.debug("load adventures {} for {}", adventureId, memberId);
        try {
            com.otabi.scoutbook.Advancement.getAdventures(memberId.get(), rank.get(), new AdventureHandler() {
                public void processAdventureMap(Map<Integer, RequirementStatus> scoutbookAdventureMap) {
                    initializeAdventureMap();
                    HashMap<Integer, Adventure> newAdventureMap = new HashMap<Integer, Adventure>(scoutbookAdventureMap.size());
                    for (Integer adventureId : scoutbookAdventureMap.keySet()) {
                        Adventure adventure = adventureMap.get(adventureId);
                        adventure.setStatus(scoutbookAdventureMap.get(adventureId));
                    }
                    adventureMap.get(adventureId).load();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Adventure getAdventure(int adventureId) {
        return (adventureMap == null ? null : adventureMap.get(adventureId));
    }

    public ObjectProperty<RequirementStatus> getRequirementStatus(ProgramRequirement programRequirement) {
        if (adventureMap == null) return null;
        for (Adventure adventure : adventureMap.values()) {
            if (adventure.containsRequirement(programRequirement)) {
                return adventure.getRequirementStatus(programRequirement);
            }
        }
        return null;
    }
}
