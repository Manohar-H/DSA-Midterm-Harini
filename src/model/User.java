package model;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    private final String name;
    private final TaskList taskList;

    public User(String name) {
        this.name = name;
        this.taskList = new TaskList();
    }

    public String getName() {
        return name;
    }

    public void addTask(String description, LocalDate dueDate, Priority priority) {
        taskList.addTask(description, dueDate, priority);
    }

    public void deleteTask(int index) {
        taskList.deleteTask(index);
    }

    public void markTaskCompleted(int index) {
        taskList.markTaskCompleted(index);
    }

    public void printTasks() {
        System.out.println("🧍Tasks for User: " + name);
        taskList.printTasks();
    }
}