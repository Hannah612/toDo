package org.example.types;

public enum SortType {
    PRIORITY_ID("priority_id"), COMPLETED("completed"), DUE_DATE("due_date");

    private String label;

    SortType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

