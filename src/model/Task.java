package model;

import java.io.Serializable;

public class Task implements Serializable {
    private final String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void markCompleted() {
        isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[ " + (isCompleted ? "✔" : "✗") + " ] " + description;
    }
}