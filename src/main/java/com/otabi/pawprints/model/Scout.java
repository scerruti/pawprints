package com.otabi.pawprints.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by Stephen on 11/15/2015.
 */
public class Scout implements Comparable<Scout> {
    final static Logger logger = LoggerFactory.getLogger(Scout.class);

    protected final IntegerProperty memberId;
    protected final StringProperty name;
    protected ObservableMap<Rank, Advancement> advancementMap;

    public Scout(Integer memberId, String name) {
        this.memberId = new SimpleIntegerProperty(this, "Member ID", memberId);
        this.name = new SimpleStringProperty(this, "Scout Name", name);
    }

    public void loadAdventure(ProgramAdventure adventure) {
        if (advancementMap == null || advancementMap.isEmpty()) {
            load(adventure);
        } else {
            advancementMap.get(adventure.getRank()).loadAdventure(adventure.getAdventureId());
        }
    }

    protected void load(final ProgramAdventure adventure) {
        Rank rank = adventure.getRank();
        HashMap<Rank, Advancement> newAdvancementMap = new HashMap<Rank, Advancement>(Rank.values().length);
        newAdvancementMap.put(rank, new Advancement(memberId.get(), rank));
        newAdvancementMap.get(adventure.getRank()).loadAdventure(adventure.getAdventureId());
        advancementMap = FXCollections.observableMap(newAdvancementMap);
    }

    public String getName() {
        return name.get();
    }

    public Advancement getAdvancement(Rank rank) {
        return (advancementMap != null ? advancementMap.get(rank) : null);
    }

    public ObjectProperty<RequirementStatus> getRequirementStatus(Rank rank, ProgramRequirement requirement) {
        Advancement advancement = getAdvancement(rank);
        return ((advancement != null) ? advancement.getRequirementStatus(requirement) : null);
    }

    @Override
    public int compareTo(Scout otherScout) {
        return this.getName().compareTo(otherScout.getName());
    }
}
