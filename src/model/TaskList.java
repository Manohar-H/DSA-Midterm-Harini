package model;

import java.io.Serializable;

public class TaskList implements Serializable {
    private class Node implements Serializable {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
        }
    }

    private Node head;

    public void addTask(String description) {
        Task task = new Task(description);
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        }
    }

    public void markTaskCompleted(int index) {
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (i == index) {
                temp.task.markCompleted();
                return;
            }
            temp = temp.next;
            i++;
        }
        System.out.println("Invalid task index.");
    }

    public void printTasks() {
        Node temp = head;
        int i = 0;
        if (temp == null) {
            System.out.println("  No tasks found.");
            return;
        }

        while (temp != null) {
            String status = temp.task.isCompleted() ? "✅" : "❌";
            System.out.println("  [" + i + "] " + status + " " + temp.task.getDescription());
            temp = temp.next;
            i++;
        }
    }
}