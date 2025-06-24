package model;

import java.io.Serializable;

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

    public void addTask(String taskDescription) {
        taskList.addTask(taskDescription);
    }

    public void markTaskCompleted(int index) {
        taskList.markTaskCompleted(index);
    }

    public void printTasks() {
        System.out.println("🧍Tasks for User: " + name);
        taskList.printTasks();
    }
}