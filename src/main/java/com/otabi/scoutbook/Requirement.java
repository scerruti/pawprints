package com.otabi.scoutbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Stephen on 11/20/2015.
 */
public class Requirement {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected int requirementNumber;
    protected int adventureNumber;
    protected RequirementStatus status;

    public Requirement(int requirementNumber, int adventureNumber, RequirementStatus status) {
        this.requirementNumber = requirementNumber;
        this.adventureNumber = adventureNumber;
        this.status = status;
    }

    public int getRequirementNumber() {
        return requirementNumber;
    }

    public int getAdventureNumber() {
        return adventureNumber;
    }

    public RequirementStatus getStatus() {
        return status;
    }
}
