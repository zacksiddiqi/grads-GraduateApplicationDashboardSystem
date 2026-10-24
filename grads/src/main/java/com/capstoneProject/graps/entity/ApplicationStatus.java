package com.capstoneProject.graps.entity;

public enum ApplicationStatus {
    not_started("Not started", "text-bg-secondary"),
    in_progress("In progress", "text-bg-primary"),
    submitted("Submitted", "text-bg-info"),
    interview("Interview", "text-bg-warning"),
    accepted("Accepted", "text-bg-success"),
    rejected("Rejected", "text-bg-danger");

    private final String label;
    private final String badgeClass;

    ApplicationStatus(String label, String badgeClass){
        this.label = label;
        this.badgeClass = badgeClass;
    }

    public String getLabel() { return label; }
    public String getBadgeClass() { return badgeClass; }
}
