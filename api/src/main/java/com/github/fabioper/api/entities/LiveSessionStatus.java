package com.github.fabioper.api.entities;

public enum LiveSessionStatus {
    PREPARING("preparing"),
    LIVE("live"),
    FINISHED("finished");

    private final String label;

    LiveSessionStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
