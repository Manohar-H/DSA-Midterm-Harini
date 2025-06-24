import model.Priority;
import model.User;

import java.time.LocalDate;
import java.util.Scanner;

import java.io.*;

public class Main {
    private static final int MAX_USERS = 3;
    private static final User[] users = new User[MAX_USERS];
    private static int userCount = 0;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        loadData();

        while (running) {
            System.out.println("\n==============================");
            System.out.println("📋 TO-DO LIST MANAGER MENU");
            System.out.println("==============================");
            System.out.println("1️⃣  Create User");
            System.out.println("2️⃣  Add Task");
            System.out.println("3️⃣  Mark Task Completed");
            System.out.println("4️⃣  View Tasks");
            System.out.println("5️⃣  Exit");
            System.out.println("6️⃣  Delete Task");
            System.out.print("Enter your choice: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> createUser();
                case 2 -> addTask();
                case 3 -> markTaskComplete();
                case 4 -> viewTasks();
                case 5 -> {
                    System.out.print("Are you sure you want to exit? (y/n): ");
                    String confirm = scanner.nextLine().trim().toLowerCase();
                    saveData();
                    if (confirm.equals("y")) running = false;
                }
                case 6 -> deleteTask();
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
        String desc = scanner.nextLine().trim();
        if (desc.isEmpty()) {
            System.out.println("❗ Task description cannot be empty.");
            return;
        }

        System.out.print("Enter due date (YYYY-MM-DD): ");
        LocalDate dueDate;
        try {
            dueDate = LocalDate.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("❗ Invalid date format.");
            return;
        }

        System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
        Priority priority;
        try {
            priority = Priority.valueOf(scanner.nextLine().trim().toUpperCase());
        } catch (Exception e) {
            System.out.println("❗ Invalid priority.");
            return;
        }

        user.addTask(desc, dueDate, priority);
        System.out.println("✅ Task added.");
    }

    private static void deleteTask() {
        User user = chooseUser();
        if (user == null) return;

        user.printTasks();
        System.out.print("Enter task index to delete: ");
        int index = readInt();
        user.deleteTask(index);
    }

    private static void markTaskComplete() {
        User user = chooseUser();
        if (user == null) return;

        user.printTasks();
        System.out.print("Enter task index to mark complete: ");
        int index = readInt();
        user.markTaskCompleted(index);
        System.out.println("✅ Task marked as complete!");
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
        System.out.println("Choose a user (0 to cancel):");
        for (int i = 0; i < userCount; i++) {
            System.out.println((i + 1) + ". " + users[i].getName());
        }
        System.out.print("Enter choice: ");
        int index = readInt() - 1;

        if (index == -1) {
            System.out.println("🔙 Action cancelled.");
            return null;
        }

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

    private static final String DATA_FILE = "data.ser";

    @SuppressWarnings("unchecked")
    private static void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            User[] loadedUsers = (User[]) in.readObject();
            for (User user : loadedUsers) {
                if (user != null) {
                    users[userCount++] = user;
                }
            }
            System.out.println("✅ Data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
        } catch (Exception e) {
            System.out.println("⚠️ Failed to load data: " + e.getMessage());
        }
    }

    private static void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(users);
            System.out.println("✅ Data saved.");
        } catch (IOException e) {
            System.out.println("⚠️ Failed to save data: " + e.getMessage());
        }
    }
}