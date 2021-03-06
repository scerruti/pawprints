package com.otabi.pawprints.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Stephen on 11/15/2015.
 */
public class Requirement {
    final static Logger logger = LoggerFactory.getLogger(Requirement.class);

    protected IntegerProperty requirementNumber;
    protected ObjectProperty<RequirementStatus> requirementStatus;

    public Requirement(int requirement, RequirementStatus status) {
        this.requirementNumber = new SimpleIntegerProperty(requirement);
        this.requirementStatus = new SimpleObjectProperty<RequirementStatus>(status);
    }

    public ObjectProperty<RequirementStatus> getRequirementStatus() {
        return requirementStatus;
    }

    public void setRequirementStatus(RequirementStatus requirementStatus) {
        this.requirementStatus.set(requirementStatus);
    }
}
