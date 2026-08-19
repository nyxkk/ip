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
        while (true) {
            String command = scanner.nextLine();

            System.out.println(separator);
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(separator);
                break;
            }

            System.out.println(" " + command);
            System.out.println(separator);
        }
    }
}
