package model;

import java.io.Serializable;
import java.time.LocalDate;

public class TaskList implements Serializable {
    private class Node implements Serializable {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
        }
    }

    private Node head;

    public void addTask(String description, LocalDate dueDate, Priority priority) {
        Task task = new Task(description, dueDate, priority);
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

    public void deleteTask(int index) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (index == 0) {
            head = head.next;
            System.out.println("Task deleted.");
            return;
        }
        Node prev = head;
        Node curr = head.next;
        int i = 1;
        while (curr != null) {
            if (i == index) {
                prev.next = curr.next;
                System.out.println("Task deleted.");
                return;
            }
            prev = curr;
            curr = curr.next;
            i++;
        }
        System.out.println("Invalid task index.");
    }
}