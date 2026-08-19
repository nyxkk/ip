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

            System.out.println(separator);
            try {
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
                    int index = getTaskIndex(command, 5, tasks, taskCount);
                    tasks[index].markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks[index]);
                } else if (command.startsWith("unmark ")) {
                    int index = getTaskIndex(command, 7, tasks, taskCount);
                    tasks[index].markAsUndone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks[index]);
                } else if (command.equals("todo") || command.startsWith("todo ")) {
                    String description = requireText(command.substring(4), "todo");
                    tasks[taskCount] = new Todo(description);
                    taskCount++;
                    printTaskAdded(tasks[taskCount - 1], taskCount);
                } else if (command.startsWith("deadline ")) {
                    int marker = command.indexOf(" /by ");
                    if (marker < 0) {
                        throw new JarvisException("A deadline must include /by followed by a date or time.");
                    }
                    String description = requireText(command.substring(9, marker), "deadline");
                    String by = requireText(command.substring(marker + 5), "deadline");
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    printTaskAdded(tasks[taskCount - 1], taskCount);
                } else if (command.startsWith("event ")) {
                    int fromMarker = command.indexOf(" /from ");
                    int toMarker = command.indexOf(" /to ", fromMarker + 7);
                    if (fromMarker < 0 || toMarker < 0) {
                        throw new JarvisException("An event must include /from and /to times.");
                    }
                    String description = requireText(command.substring(6, fromMarker), "event");
                    String from = requireText(command.substring(fromMarker + 7, toMarker), "event");
                    String to = requireText(command.substring(toMarker + 5), "event");
                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;
                    printTaskAdded(tasks[taskCount - 1], taskCount);
                } else {
                    throw new JarvisException("I'm sorry, but I don't know what that means.");
                }
            } catch (JarvisException exception) {
                System.out.println(" OOPS!!! " + exception.getMessage());
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

    /** Validates and converts a one-based task number into an array index. */
    private static int getTaskIndex(String command, int argumentStart, Task[] tasks, int taskCount) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(command.substring(argumentStart).trim());
        } catch (NumberFormatException exception) {
            throw new JarvisException("The task number must be a whole number.");
        }

        if (taskNumber < 1 || taskNumber > taskCount || tasks[taskNumber - 1] == null) {
            throw new JarvisException("That task number does not exist.");
        }
        return taskNumber - 1;
    }

    /** Rejects missing descriptions and date/time values. */
    private static String requireText(String text, String commandName) {
        String trimmedText = text.trim();
        if (trimmedText.isEmpty()) {
            if (commandName.equals("todo")) {
                throw new JarvisException("The description of a todo cannot be empty.");
            }
            throw new JarvisException("The " + commandName + " details cannot be empty.");
        }
        return trimmedText;
    }
}
