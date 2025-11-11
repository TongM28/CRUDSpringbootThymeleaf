package com.t2404e.jobboard.model;

public enum JobType {
    FULL_TIME("Toàn thời gian"),
    PART_TIME("Bán thời gian"),
    REMOTE("Làm việc từ xa"),
    INTERNSHIP("Thực tập");

    private final String displayName;

    JobType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}