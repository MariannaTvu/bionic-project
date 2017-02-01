package com.bionic.edu.mariana.persistence;

public enum UsefulnessLevel {
    VERY_USEFUL(0, "Very useful"), USEFUL(1, "Useful"), RELATED(2, "Related"), NOT_RELATED(3, "Not related");

    private int value;
    private String description;

    UsefulnessLevel(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static UsefulnessLevel byValue(int value) {
        for (UsefulnessLevel level : UsefulnessLevel.values()) {
            if (level.value == value) {
                return level;
            }
        }
        throw new IllegalArgumentException("no such level: " + value);
    }

    public int getValue() {
        return value;
    }

    public UsefulnessLevel setValue(int value) {
        this.value = value;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UsefulnessLevel setDescription(String description) {
        this.description = description;
        return this;
    }
}
