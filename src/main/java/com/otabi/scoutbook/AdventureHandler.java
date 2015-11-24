package com.otabi.scoutbook;

import com.otabi.pawprints.model.RequirementStatus;

import java.util.Map;

/**
 * Created by Stephen on 11/17/2015.
 */
public interface AdventureHandler {
    public void processAdventureMap(Map<Integer, RequirementStatus> adventureMap);
}

