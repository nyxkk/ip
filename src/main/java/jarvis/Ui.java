package jarvis;

import java.util.Scanner;

/** Handles all interaction between Jarvis and the console user. */
public class Ui {
    private static final String SEPARATOR = "_".repeat(60);
    private static final String BANNER = """
                 ██╗ █████╗ ██████╗ ██╗   ██╗██╗███████╗
                 ██║██╔══██╗██╔══██╗██║   ██║██║██╔════╝
                 ██║███████║██████╔╝██║   ██║██║███████╗
            ██   ██║██╔══██║██╔══██╗╚██╗ ██╔╝██║╚════██║
            ╚█████╔╝██║  ██║██║  ██║ ╚████╔╝ ██║███████║
             ╚════╝ ╚═╝  ╚═╝╚═╝  ╚═╝  ╚═══╝  ╚═╝╚══════╝
            """.stripTrailing();

    private final Scanner scanner;

    /** Creates a console UI that reads from standard input. */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /** Displays Jarvis' welcome message. */
    public void showWelcome() {
        showLine();
        System.out.println(BANNER.substring(1));
        System.out.println("Hello! I'm Jarvis.");
        System.out.println("What can I do for you?");
        showLine();
    }

    /** Reads one command from the console. */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Displays the standard divider line. */
    public void showLine() {
        System.out.println(SEPARATOR);
    }

    /** Displays a user-facing error. */
    public void showError(String message) {
        System.out.println(" OOPS!!! " + message);
    }

    /** Displays the tasks in their current order. */
    public void showTasks(TaskList tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i + 1));
        }
    }

    /** Displays the confirmation for a newly added task. */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    /** Displays the confirmation for marking a task done. */
    public void showMarked(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    /** Displays the confirmation for marking a task undone. */
    public void showUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }

    /** Displays the confirmation for deleting a task. */
    public void showDeleted(Task task, int remainingTasks) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + remainingTasks + " tasks in the list.");
    }

    /** Displays the farewell message. */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
