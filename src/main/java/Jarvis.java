public class Jarvis {
    public static void main(String[] args) {
        String banner = """
                     ██╗ █████╗ ██████╗ ██╗   ██╗██╗███████╗
                     ██║██╔══██╗██╔══██╗██║   ██║██║██╔════╝
                     ██║███████║██████╔╝██║   ██║██║███████╗
                ██   ██║██╔══██║██╔══██╗╚██╗ ██╔╝██║╚════██║
                ╚█████╔╝██║  ██║██║  ██║ ╚████╔╝ ██║███████║
                 ╚════╝ ╚═╝  ╚═╝╚═╝  ╚═╝  ╚═══╝  ╚═╝╚══════╝
                """.stripTrailing().substring(1);

        String separator = "_".repeat(60);
        System.out.println(separator);
        System.out.println(banner);
        System.out.println("Hello! I'm Jarvis.");
        System.out.println("What can I do for you?");
        System.out.println(separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
