package com.example.cvfiltersystem.enums;

public enum ApplicationStatus {
    APPROVED("Approved"),
    REJECTED("Rejected"),
    IN_REVIEW("In review");
    private final String displayName;
    ApplicationStatus(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}

