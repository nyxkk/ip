package jarvis;

/** A task assistant that can be used from either the console or JavaFX GUI. */
public class Jarvis {
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;
    private final TaskList tasks;

    /** Creates Jarvis with its user interface, parser, and file storage components. */
    public Jarvis() {
        storage = new Storage();
        parser = new Parser();
        ui = new Ui();
        tasks = loadTasks();
    }

    /** Starts Jarvis and processes commands until the user says goodbye. */
    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();
            ui.showLine();
            try {
                ParsedCommand command = parser.parse(input);
                if (command.getType() == ParsedCommand.Type.BYE) {
                    ui.showGoodbye();
                    ui.showLine();
                    return;
                }
                System.out.println(execute(command));
            } catch (JarvisException exception) {
                ui.showError(exception.getMessage());
            }
            ui.showLine();
        }
    }

    /**
     * Processes one GUI command and returns the text Jarvis should display.
     *
     * @param input the complete command entered by the user
     * @return Jarvis' response to the command
     */
    public String getResponse(String input) {
        try {
            ParsedCommand command = parser.parse(input);
            if (command.getType() == ParsedCommand.Type.BYE) {
                return ui.getGoodbyeMessage();
            }
            return execute(command);
        } catch (JarvisException exception) {
            return ui.getErrorMessage(exception.getMessage());
        }
    }

    /**
     * Returns the short welcome message displayed when the GUI opens.
     *
     * @return Jarvis' welcome message
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /** Loads saved tasks, falling back to an empty list if loading fails. */
    private TaskList loadTasks() {
        try {
            return new TaskList(storage.load());
        } catch (JarvisException exception) {
            ui.showError(exception.getMessage());
            return new TaskList();
        }
    }

    private String execute(ParsedCommand command) {
        return switch (command.getType()) {
            case LIST -> ui.getTasksMessage(tasks);
            case FIND -> ui.getMatchingTasksMessage(tasks, command.getDescription());
            case MARK -> markTask(command.getTaskNumber());
            case UNMARK -> unmarkTask(command.getTaskNumber());
            case DELETE -> deleteTask(command.getTaskNumber());
            case TODO -> addTask(new Todo(command.getDescription()));
            case DEADLINE -> addTask(new Deadline(command.getDescription(),
                    command.getFirstDetail()));
            case EVENT -> addTask(new Event(command.getDescription(), command.getFirstDetail(),
                    command.getSecondDetail()));
            case BYE -> throw new AssertionError("bye is handled before execution");
        };
    }

    private String markTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.markAsDone();
        saveTasks(tasks);
        return ui.getMarkedMessage(task);
    }

    private String unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber);
        task.markAsUndone();
        saveTasks(tasks);
        return ui.getUnmarkedMessage(task);
    }

    private String deleteTask(int taskNumber) {
        Task removedTask = tasks.remove(taskNumber);
        saveTasks(tasks);
        return ui.getDeletedMessage(removedTask, tasks.size());
    }

    private String addTask(Task task) {
        tasks.add(task);
        saveTasks(tasks);
        return ui.getTaskAddedMessage(task, tasks.size());
    }

    private void saveTasks(TaskList tasks) {
        storage.save(tasks.getTasks());
    }

    /**
     * Program entry point.
     *
     * @param args command-line arguments, currently unused
     */
    public static void main(String[] args) {
        new Jarvis().run();
    }
}
