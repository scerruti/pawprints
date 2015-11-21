package com.otabi.pawprints.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Stephen on 11/18/2015.
 */
public class ProgramRequirement {
    protected final IntegerProperty requirementNumber;
    protected final StringProperty requirementName;

    public ProgramRequirement(int requirement) {
        this.requirementNumber = new SimpleIntegerProperty(requirement);
        this.requirementName = new SimpleStringProperty(Integer.toString(requirement));
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
