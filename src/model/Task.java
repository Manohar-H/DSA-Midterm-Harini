package model;

import java.io.Serializable;
import java.time.LocalDate;
import model.Priority;

public class Task implements Serializable {
    private final String description;
    private boolean isCompleted;
    private final LocalDate dueDate;
    private final Priority priority;

    public Task(String description, LocalDate dueDate, Priority priority) {
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "[ " + (isCompleted ? "✔" : "✗") + " ] " + description +
                " (Due: " + dueDate + ", Priority: " + priority + ")";
    }
}