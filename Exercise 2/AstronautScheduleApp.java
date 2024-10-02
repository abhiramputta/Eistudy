import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Task {
    String description;
    LocalTime startTime;
    LocalTime endTime;
    String priority;
    boolean completed;

    public Task(String description, LocalTime startTime, LocalTime endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.completed = false;
    }

    @Override
    public String toString() {
        return "Task: " + description + ", Start: " + startTime + ", End: " + endTime + ", Priority: " + priority + ", Completed: " + completed;
    }
}

class Astronaut {
    String username;
    String password;
    List<Task> tasks;

    public Astronaut(String username, String password) {
        this.username = username;
        this.password = password;
        this.tasks = new ArrayList<>();
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int taskId) {
        tasks.remove(taskId);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void markTaskAsCompleted(int taskId) {
        tasks.get(taskId).completed = true;
    }

    public void editTask(int taskId, String newDescription, LocalTime newStartTime, LocalTime newEndTime, String newPriority) {
        Task task = tasks.get(taskId);
        if (newDescription != null && !newDescription.isEmpty()) {
            task.description = newDescription;
        }
        if (newStartTime != null) {
            task.startTime = newStartTime;
        }
        if (newEndTime != null) {
            task.endTime = newEndTime;
        }
        if (newPriority != null && !newPriority.isEmpty()) {
            task.priority = newPriority;
        }
    }

    public List<Task> getTasksByPriority(String priority) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.priority.equalsIgnoreCase(priority)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}

public class AstronautScheduleApp {
    private List<Astronaut> astronauts;
    private Astronaut loggedInAstronaut;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public AstronautScheduleApp() {
        astronauts = new ArrayList<>();
        astronauts.add(new Astronaut("Neil", "apollo11"));
        astronauts.add(new Astronaut("Buzz", "apollo11"));
    }

    public void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (Astronaut astronaut : astronauts) {
            if (astronaut.username.equals(username) && astronaut.validatePassword(password)) {
                loggedInAstronaut = astronaut;
                System.out.println("Login successful!");
                return;
            }
        }
        System.out.println("Invalid username or password!");
    }

    public void register() {
        System.out.print("Enter a new username: ");
        String username = scanner.nextLine();

        for (Astronaut astronaut : astronauts) {
            if (astronaut.username.equals(username)) {
                System.out.println("Username already taken! Please choose another one.");
                return;
            }
        }

        System.out.print("Enter a new password: ");
        String password = scanner.nextLine();

        astronauts.add(new Astronaut(username, password));
        System.out.println("Registration successful! You can now log in.");
    }

    public void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Enter start time (HH:mm): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine(), timeFormatter);

        System.out.print("Enter end time (HH:mm): ");
        LocalTime endTime = LocalTime.parse(scanner.nextLine(), timeFormatter);

        System.out.print("Enter priority (High, Medium, Low): ");
        String priority = scanner.nextLine();

        if (isTaskOverlapping(startTime, endTime)) {
            System.out.println("Task overlaps with existing tasks!");
        } else {
            loggedInAstronaut.addTask(new Task(description, startTime, endTime, priority));
            System.out.println("Task added successfully!");
        }
    }

    private boolean isTaskOverlapping(LocalTime startTime, LocalTime endTime) {
        for (Task task : loggedInAstronaut.getTasks()) {
            if (task.startTime.isBefore(endTime) && task.endTime.isAfter(startTime)) {
                return true; 
            }
        }
        return false;
    }

    public void removeTask() {
        viewTasks();
        System.out.print("Enter task number to remove: ");
        int taskId = Integer.parseInt(scanner.nextLine()) - 1;

        if (taskId >= 0 && taskId < loggedInAstronaut.getTasks().size()) {
            loggedInAstronaut.removeTask(taskId);
            System.out.println("Task removed successfully!");
        } else {
            System.out.println("Invalid task number!");
        }
    }

    public void editTask() {
        viewTasks();
        System.out.print("Enter task number to edit: ");
        int taskId = Integer.parseInt(scanner.nextLine()) - 1;

        if (taskId >= 0 && taskId < loggedInAstronaut.getTasks().size()) {
            Task task = loggedInAstronaut.getTasks().get(taskId);
            System.out.println("Editing task: " + task);

            System.out.print("Enter new description (leave blank to keep current): ");
            String newDescription = scanner.nextLine();

            System.out.print("Enter new start time (leave blank to keep current): ");
            String newStartTimeStr = scanner.nextLine();
            LocalTime newStartTime = newStartTimeStr.isEmpty() ? null : LocalTime.parse(newStartTimeStr, timeFormatter);

            System.out.print("Enter new end time (leave blank to keep current): ");
            String newEndTimeStr = scanner.nextLine();
            LocalTime newEndTime = newEndTimeStr.isEmpty() ? null : LocalTime.parse(newEndTimeStr, timeFormatter);

            System.out.print("Enter new priority (leave blank to keep current): ");
            String newPriority = scanner.nextLine();

            loggedInAstronaut.editTask(taskId, newDescription, newStartTime, newEndTime, newPriority);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Invalid task number!");
        }
    }

    public void markTaskAsCompleted() {
        viewTasks();
        System.out.print("Enter task number to mark as completed: ");
        int taskId = Integer.parseInt(scanner.nextLine()) - 1;

        if (taskId >= 0 && taskId < loggedInAstronaut.getTasks().size()) {
            loggedInAstronaut.markTaskAsCompleted(taskId);
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number!");
        }
    }

    public void viewTasks() {
        List<Task> tasks = loggedInAstronaut.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        tasks.sort(Comparator.comparing(task -> task.startTime));
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void viewTasksByPriority() {
        System.out.print("Enter priority level (High, Medium, Low): ");
        String priority = scanner.nextLine();

        List<Task> filteredTasks = loggedInAstronaut.getTasksByPriority(priority);
        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks found for the specified priority level.");
        } else {
            for (int i = 0; i < filteredTasks.size(); i++) {
                System.out.println((i + 1) + ". " + filteredTasks.get(i));
            }
        }
    }

    public static void main(String[] args) {
        AstronautScheduleApp app = new AstronautScheduleApp();
        String option;

        while (true) {
            System.out.println("Welcome to the Astronaut Schedule App");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Select an option: ");
            option = scanner.nextLine();

            if (option.equals("1")) {
                app.register();
            } else if (option.equals("2")) {
                app.login();
                if (app.loggedInAstronaut != null) {
                    break; 
                }
            } else {
                System.out.println("Invalid option, please try again.");
            }
        }

        while (true) {
            System.out.println("\nTask Management Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Edit Task");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. View All Tasks");
            System.out.println("6. View Tasks by Priority");
            System.out.println("7. Logout");

            System.out.print("Select an option: ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    app.addTask();
                    break;
                case "2":
                    app.removeTask();
                    break;
                case "3":
                    app.editTask();
                    break;
                case "4":
                    app.markTaskAsCompleted();
                    break;
                case "5":
                    app.viewTasks();
                    break;
                case "6":
                    app.viewTasksByPriority();
                    break;
                case "7":
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
