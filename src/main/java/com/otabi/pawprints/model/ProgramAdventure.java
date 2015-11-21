package com.otabi.pawprints.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 11/16/2015.
 */
public class ProgramAdventure {
    final static Logger logger = LoggerFactory.getLogger(ProgramAdventure.class);

    protected final Rank rank;
    protected final IntegerProperty adventureId;
    protected final StringProperty name;
    protected final ObjectProperty<Image> loop;
    protected final Map<Integer, ProgramRequirement> requriementMap;

    public ProgramAdventure(Rank rank, IntegerProperty adventureId, StringProperty name, int[] requirements) {
        this.rank = rank;
        this.adventureId = adventureId;
        this.name = name;
        requriementMap = new HashMap<Integer, ProgramRequirement>(requirements.length);
        for (Integer requirement : requirements) {
            requriementMap.put(requirement, new ProgramRequirement(requirement));
        }
        loop = new SimpleObjectProperty<Image>(this, "adventureImage",
                new Image(String.format("com/otabi/pawprints/view/resources/%s/%d_100.png",
                        rank.toString(),
                        adventureId.getValue())));
    }

    public int getAdventureId() {
        return adventureId.get();
    }

    public IntegerProperty adventureIdProperty() {
        return adventureId;
    }

    public void setAdventureId(int adventureId) {
        this.adventureId.set(adventureId);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ObservableList<ProgramRequirement> getObservableRequirementList() {
        List<Integer> sortedIds = new ArrayList<Integer>(requriementMap.keySet());
        Collections.sort(sortedIds);
        ArrayList<ProgramRequirement> requirementArrayList = new ArrayList<ProgramRequirement>(sortedIds.size());
        for (Integer id : sortedIds) {
            requirementArrayList.add(requriementMap.get(id));
        }
        return FXCollections.observableList(new ArrayList<ProgramRequirement>(requirementArrayList));
    }

    @Override
    public String toString() {
        return getName();
    }

    public Rank getRank() {
        return rank;
    }

    public Image getLoop() {
        return loop.get();
    }

    public ObjectProperty<Image> loopProperty() {
        return loop;
    }

    public List<ProgramRequirement> getRequriementMap() {
        return new ArrayList<ProgramRequirement>(requriementMap.values());
    }
}
