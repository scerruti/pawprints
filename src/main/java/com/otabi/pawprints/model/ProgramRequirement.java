package com.otabi.pawprints.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Stephen on 11/18/2015.
 */
public class ProgramRequirement {
    final static Logger logger = LoggerFactory.getLogger(ProgramRequirement.class);

    protected final IntegerProperty requirementNumber;
    protected final StringProperty requirementName;
    
    public ProgramRequirement(int requirement, String requirementName) {
        this.requirementNumber = new SimpleIntegerProperty(requirement);
        this.requirementName = new SimpleStringProperty(requirementName);
    }

    public int getRequirementNumber() {
        return requirementNumber.get();
    }

    public IntegerProperty requirementNumberProperty() {
        return requirementNumber;
    }

    public String getRequirementName() {
        return requirementName.get();
    }

    public StringProperty requirementNameProperty() {
        return requirementName;
    }
}
