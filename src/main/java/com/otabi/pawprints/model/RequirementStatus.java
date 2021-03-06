package com.otabi.pawprints.model;

import java.util.Arrays;

/**
 * Created by Stephen on 11/14/2015.
 */
public enum RequirementStatus {
    INVALID("emptygray"),
    NOT_STARTED("empty"),
    STARTED("green"),
    COMPLETED("done"),
    APPROVED("approved"),
    AWARDED("awarded"),
    LOADING("loading");

    private final String checkBoxName;

    RequirementStatus(String checkBoxName) {
        this.checkBoxName = checkBoxName;
    }

    public static RequirementStatus fromCheckBoxName(String name) {
        if (name != null) {
            for (RequirementStatus status : RequirementStatus.values()) {
                if (name.equals(status.checkBoxName)) return status;
            }
        }
        return null;

    }

    public static String[] getNames() {
        return Arrays.stream(RequirementStatus.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
