import model.User;

public class Main {
    public static void main(String[] args) {
        User[] users = new User[3];

        users[0] = new User("Alice");
        users[1] = new User("Bob");
        users[2] = new User("Charlie");

        users[0].addTask("Finish DSA homework");
        users[0].addTask("Buy groceries");

        users[1].addTask("Prepare for sprint demo");

        users[0].markTaskCompleted(0);

        for (User user : users) {
            user.printTasks();
            System.out.println();
        }
    }
}