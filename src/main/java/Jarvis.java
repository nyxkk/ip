import java.util.Scanner;

/**
 * A simple command-line assistant that echoes commands until the user says goodbye.
 */
public class Jarvis {
    /**
     * Starts Jarvis and reads commands from standard input.
     *
     * @param args command-line arguments, which are not used
     */
    public static void main(String[] args) {
        String banner = """
                     ██╗ █████╗ ██████╗ ██╗   ██╗██╗███████╗
                     ██║██╔══██╗██╔══██╗██║   ██║██║██╔════╝
                     ██║███████║██████╔╝██║   ██║██║███████╗
                ██   ██║██╔══██║██╔══██╗╚██╗ ██╔╝██║╚════██║
                ╚█████╔╝██║  ██║██║  ██║ ╚████╔╝ ██║███████║
                 ╚════╝ ╚═╝  ╚═╝╚═╝  ╚═╝  ╚═══╝  ╚═╝╚══════╝
                """.stripTrailing();

        String separator = "_".repeat(60);
        System.out.println(separator);
        System.out.println(banner.substring(1));
        System.out.println("Hello! I'm Jarvis.");
        System.out.println("What can I do for you?");
        System.out.println(separator);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String command = scanner.nextLine();

            // Looking through the text to find keywords that initiates actions
            System.out.println(separator);
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(separator);
                break;
            } else if (command.equals("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
            } else if (command.startsWith("mark ")) {
                int index = Integer.parseInt(command.substring(5)) - 1;
                tasks[index].markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[index]);
            } else if (command.startsWith("unmark ")) {
                int index = Integer.parseInt(command.substring(7)) - 1;
                tasks[index].markAsUndone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[index]);
            } else if (command.startsWith("todo ")) {
                tasks[taskCount] = new Todo(command.substring(5));
                taskCount++;
                printTaskAdded(tasks[taskCount - 1], taskCount);
            } else if (command.startsWith("deadline ")) {
                int marker = command.indexOf(" /by ");
                String description = command.substring(9, marker);
                String by = command.substring(marker + 5);
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                printTaskAdded(tasks[taskCount - 1], taskCount);
            } else if (command.startsWith("event ")) {
                int fromMarker = command.indexOf(" /from ");
                int toMarker = command.indexOf(" /to ", fromMarker + 7);
                String description = command.substring(6, fromMarker);
                String from = command.substring(fromMarker + 7, toMarker);
                String to = command.substring(toMarker + 5);
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                printTaskAdded(tasks[taskCount - 1], taskCount);
            } else {
                tasks[taskCount] = new Task(command);
                taskCount++;
                System.out.println(" added: " + command);
            }

            System.out.println(separator);
        }
    }

    /** Prints the confirmation shared by the typed task commands. */
    private static void printTaskAdded(Task task, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }
}
