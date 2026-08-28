package jarvis;

/** A command-line task assistant. */
public class Jarvis {
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;

    /** Creates Jarvis with its console, parser, and file storage components. */
    public Jarvis() {
        storage = new Storage();
        parser = new Parser();
        ui = new Ui();
    }

    /** Starts Jarvis and processes commands until the user says goodbye. */
    public void run() {
        ui.showWelcome();
        TaskList tasks = loadTasks();

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
                execute(command, tasks);
            } catch (JarvisException exception) {
                ui.showError(exception.getMessage());
            }
            ui.showLine();
        }
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

    private void execute(ParsedCommand command, TaskList tasks) {
        switch (command.getType()) {
        case LIST -> ui.showTasks(tasks);
        case MARK -> {
            Task task = tasks.get(command.getTaskNumber());
            task.markAsDone();
            saveTasks(tasks);
            ui.showMarked(task);
        }
        case UNMARK -> {
            Task task = tasks.get(command.getTaskNumber());
            task.markAsUndone();
            saveTasks(tasks);
            ui.showUnmarked(task);
        }
        case DELETE -> {
            Task removedTask = tasks.remove(command.getTaskNumber());
            saveTasks(tasks);
            ui.showDeleted(removedTask, tasks.size());
        }
        case TODO -> {
            tasks.add(new Todo(command.getDescription()));
            saveTasks(tasks);
            ui.showTaskAdded(tasks.get(tasks.size()), tasks.size());
        }
        case DEADLINE -> {
            tasks.add(new Deadline(command.getDescription(), command.getFirstDetail()));
            saveTasks(tasks);
            ui.showTaskAdded(tasks.get(tasks.size()), tasks.size());
        }
        case EVENT -> {
            tasks.add(new Event(command.getDescription(), command.getFirstDetail(),
                    command.getSecondDetail()));
            saveTasks(tasks);
            ui.showTaskAdded(tasks.get(tasks.size()), tasks.size());
        }
        case BYE -> throw new AssertionError("bye is handled before execution");
        }
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
