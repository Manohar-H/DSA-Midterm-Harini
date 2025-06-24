import model.User;

import java.util.Scanner;

public class Main {
    private static final int MAX_USERS = 3;
    private static final User[] users = new User[MAX_USERS];
    private static int userCount = 0;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== TO-DO LIST MANAGER ===");
            System.out.println("1. Create User");
            System.out.println("2. Add Task");
            System.out.println("3. Mark Task Completed");
            System.out.println("4. View Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> createUser();
                case 2 -> addTask();
                case 3 -> markTaskComplete();
                case 4 -> viewTasks();
                case 5 -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.println("Thanks for using the To-Do List Manager!");
    }

    private static void createUser() {
        if (userCount >= MAX_USERS) {
            System.out.println("❗ Maximum number of users reached.");
            return;
        }
        System.out.print("Enter username: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("❗ Username cannot be empty.");
            return;
        }

        if (findUser(name) != null) {
            System.out.println("❗ User already exists!");
            return;
        }

        users[userCount++] = new User(name);
        System.out.println("✅ User '" + name + "' created.");
    }

    private static void addTask() {
        User user = chooseUser();
        if (user == null) return;

        System.out.print("Enter task description: ");
        String task = scanner.nextLine().trim();

        if (task.isEmpty()) {
            System.out.println("❗ Task description cannot be empty.");
            return;
        }

        user.addTask(task);
        System.out.println("✅ Task added to " + user.getName() + ".");
    }

    private static void markTaskComplete() {
        User user = chooseUser();
        if (user == null) return;

        user.printTasks();
        System.out.print("Enter task index to mark complete: ");
        int index = readInt();
        user.markTaskCompleted(index);
        System.out.println("✅ Task marked complete (if index was valid).");
    }

    private static void viewTasks() {
        if (userCount == 0) {
            System.out.println("No users or tasks to show.");
            return;
        }
        for (int i = 0; i < userCount; i++) {
            users[i].printTasks();
            System.out.println();
        }
    }

    private static User chooseUser() {
        if (userCount == 0) {
            System.out.println("No users available.");
            return null;
        }
        System.out.println("Choose a user:");
        for (int i = 0; i < userCount; i++) {
            System.out.println((i + 1) + ". " + users[i].getName());
        }
        System.out.print("Enter choice: ");
        int index = readInt() - 1;
        if (index < 0 || index >= userCount) {
            System.out.println("Invalid user choice.");
            return null;
        }
        return users[index];
    }

    private static User findUser(String name) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equalsIgnoreCase(name)) {
                return users[i];
            }
        }
        return null;
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }
}