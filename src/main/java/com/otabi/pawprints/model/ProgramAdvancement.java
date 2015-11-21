package com.otabi.pawprints.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 11/16/2015.
 */
public class ProgramAdvancement {
    protected Map<Rank, Map<Integer, ProgramAdventure>> adventureMap;

    public ProgramAdvancement() {
    }

    public Map<Rank, Map<Integer, ProgramAdventure>> getAdventureMap() {
        return adventureMap;
    }

    public void setAdventureMapForRank(Rank rank, List<ProgramAdventure> adventureList) {
        if (this.adventureMap == null) this.adventureMap = new HashMap<Rank, Map<Integer,ProgramAdventure>>(Rank.values().length);
        HashMap<Integer, ProgramAdventure> newAdventureMap = new HashMap<Integer, ProgramAdventure>(adventureList.size());
        for (ProgramAdventure adventure : adventureList) {
            newAdventureMap.put(adventure.getAdventureId(), adventure);
        }
        this.adventureMap.put(rank, newAdventureMap);
    }

    public Map<Integer, ProgramAdventure> getAdventureMapByRank(Rank rank) {
        return adventureMap.get(rank);
    }
}
