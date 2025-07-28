package org.example.types;

public enum SortType {
    PRIORITYID("priorityId"), COMPLETED("completed"), DUEDATE("dueDate");

    private String label;

    SortType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

