import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<Object>> taskList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            refreshScreen();
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Complete Task");
            System.out.println("3. Update Task");
            System.out.println("4. Remove Task");
            System.out.println("5. View All Tasks");
            System.out.println("6. Update Data");
            System.out.println("7. Exit Program");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter due date (mm/dd): ");
                    String dueDate = scanner.nextLine();
                    addTask(taskList, name, dueDate);
                    break;
                case 2:
                    viewAllTasks(taskList);
                    System.out.print("Enter the task number to mark as completed: ");
                    int taskNumber = scanner.nextInt();
                    completeTask(taskList, taskNumber);
                    break;
                case 3:
                    viewAllTasks(taskList);
                    System.out.print("Enter the task number to update: ");
                    int taskToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter updated name (press Enter to keep the same): ");
                    String updatedName = scanner.nextLine();
                    System.out.print("Enter updated due date (mm/dd) (press Enter to keep the same): ");
                    String updatedDueDate = scanner.nextLine();
                    updateTask(taskList, taskToUpdate, updatedName, updatedDueDate);
                    break;
                case 4:
                    viewAllTasks(taskList);
                    System.out.print("Enter the task number to remove: ");
                    int taskToRemove = scanner.nextInt();
                    removeTask(taskList, taskToRemove);
                    break;
                case 5:
                    viewAllTasks(taskList);
                    break;
                case 6:
                    updateData(taskList);
                    break;
                case 7:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void addTask(ArrayList<ArrayList<Object> > taskList, String name, String dueDate) {
        ArrayList<Object> task = new ArrayList<>();
        task.add(name);
        task.add(false);
        task.add(dueDate);
        taskList.add(task);
        System.out.println("Task added: " + name);
    }

    public static void completeTask(ArrayList<ArrayList<Object> > taskList, int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskList.size()) {
            taskList.get(taskNumber - 1).set(1, true);
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public static void updateTask(ArrayList<ArrayList<Object> > taskList, int taskNumber, String updatedName, String updatedDueDate) {
        if (taskNumber >= 1 && taskNumber <= taskList.size()) {
            ArrayList<Object> task = taskList.get(taskNumber - 1);
            if (!updatedName.isEmpty()) {
                task.set(0, updatedName);
            }
            if (!updatedDueDate.isEmpty()) {
                task.set(2, updatedDueDate);
            }
            System.out.println("Task updated.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public static void removeTask(ArrayList<ArrayList<Object> > taskList, int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskList.size()) {  // complex if statement
            String name = (String) taskList.get(taskNumber - 1).get(0);
            taskList.remove(taskNumber - 1);
            System.out.println("Task removed: " + name);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public static void viewAllTasks(ArrayList<ArrayList<Object> > taskList) {
        System.out.println("\nAll Tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            ArrayList<Object> task = taskList.get(i);
            String name = (String) task.get(0);
            boolean completed = (boolean) task.get(1);
            String dueDate = (String) task.get(2);
            String status = completed ? "[Completed]" : "[Pending]"; // could also change this to an if-statement if its better for requirements, but ternary operators are a lot cleaner
            System.out.println((i + 1) + ". " + name + " " + status + " Due: " + dueDate); // does this count as a string method (concatenation)?
        }
    }

    public static void updateData(ArrayList<ArrayList<Object> > taskList) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd"); // can this count as string stuff
        String currentDate = dateFormat.format(new Date());

        for (ArrayList<Object> task : taskList) {
            String dueDate = (String) task.get(2);
            if (dueDate.equals(currentDate) && !(boolean) task.get(1)) {
                task.set(1, true);
            }
        }
        System.out.println("Data updated for today's date: " + currentDate);
    }

    public static void refreshScreen() {
        System.out.println("\033[H\033[2J");
    }
}


// TODO: make entire screen refresh after each command