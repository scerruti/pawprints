package com.otabi.pawprints.model;

import com.otabi.scoutbook.RequirementHandler;
import com.otabi.scoutbook.RequirementStatus;
import com.sun.javafx.collections.ObservableMapWrapper;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Stephen on 11/15/2015.
 */
public class Adventure {
    final static Logger logger = LoggerFactory.getLogger(Adventure.class);

    protected final IntegerProperty memberId;
    protected final ObjectProperty<Rank> rank;
    protected final IntegerProperty adventureId;
    protected final SimpleObjectProperty<RequirementStatus> status;
    protected ObservableMap<Integer, Requirement> requirementMap;

    protected boolean loaded = false;

    public Adventure(int memberId, Rank rank, int adventureId, RequirementStatus status) {
        this.memberId = new SimpleIntegerProperty(memberId);
        this.rank = new SimpleObjectProperty<Rank>(rank);
        this.adventureId = new SimpleIntegerProperty(adventureId);
        this.status = new SimpleObjectProperty<RequirementStatus>(status);
        initializeRequirementMap();
    }

    private void initializeRequirementMap() {
        Rank rank = this.rank.get();
        int memberId = this.memberId.get();

        if (requirementMap == null || requirementMap.isEmpty()) {
            List<ProgramRequirement> programRequirementList = Program.getRequirementListByRankAndAdventure(rank, adventureId.get());
            HashMap<Integer, Requirement> newRequirementMap = new HashMap<Integer, Requirement>(programRequirementList.size());
            for (ProgramRequirement programRequirement : programRequirementList) {
                Requirement requirement = new Requirement(programRequirement.getRequirementNumber(), RequirementStatus.LOADING);
                newRequirementMap.put(programRequirement.getRequirementNumber(), requirement);
            }
            requirementMap = new ObservableMapWrapper<Integer, Requirement>(newRequirementMap);
        }
    }

    protected void load(final int adventureId) {

        if (!status.get().equals(RequirementStatus.INVALID) && !status.equals(RequirementStatus.NOT_STARTED)) {
            try {
                initializeRequirementMap();
                new com.otabi.scoutbook.Adventure().getRequirements(memberId.get(), rank.get(), adventureId, new RequirementHandler() {
                    public void processRequirementMap(HashMap<Integer, com.otabi.scoutbook.Requirement> scoutbookRequirementMap) {
                        for (com.otabi.scoutbook.Requirement scoutbookRequirement : scoutbookRequirementMap.values()) {
                            Requirement requirement = getRequirement(scoutbookRequirement.getRequirementNumber());
                            if (requirement == null) {
                                // FIXME Requirement not present in Program possible logging
                                continue;
                            }
                            requirement.setRequirementStatus(scoutbookRequirement.getStatus());
                        }
                    }
                });
            } catch (Exception e) {
            }
        }
    }

    public Requirement getRequirement(int requirementId) {
        if (requirementMap == null) {
            return null;
        } else {
            return requirementMap.get(requirementId);
        }
    }

    public RequirementStatus getStatus() {
        return status.get();
    }

    public SimpleObjectProperty<RequirementStatus> statusProperty() {
        return status;
    }

    public boolean containsRequirement(ProgramRequirement programRequirement) {
        if (requirementMap == null) return false;
        return (requirementMap.keySet().contains(programRequirement.getRequirementNumber()));
    }

    public ObjectProperty<RequirementStatus> getRequirementStatus(ProgramRequirement programRequirement) {
        if (requirementMap == null || requirementMap.get(programRequirement.getRequirementNumber()) == null)
            return null;
        return getRequirement(programRequirement.getRequirementNumber()).getRequirementStatus();
    }

    public void setStatus(RequirementStatus status) {
        this.status.set(status);
    }
}