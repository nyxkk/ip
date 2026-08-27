/**
 * Represents one task and whether it has been completed.
 */
public class Task {
    private final String description;
    private final TaskType type;
    private boolean isDone;

    /**
     * Creates an incomplete task with the given description.
     *
     * @param description the text describing the task
     */
    public Task(String description) {
        this(description, TaskType.GENERIC);
    }

    /**
     * Creates a task with a category.
     *
     * @param description the text describing the task
     * @param type the task category
     */
    protected Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Returns the symbol used to show the task's completion status.
     *
     * @return {@code X} when the task is complete, or a blank space otherwise
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /** Returns whether this task has been completed. */
    public boolean isDone() {
        return isDone;
    }

    /** Returns the task description without its display markers. */
    public String getDescription() {
        return description;
    }

    /** Returns this task's category for persistence. */
    public TaskType getType() {
        return type;
    }

    /** Marks this task as complete. */
    public void markAsDone() {
        this.isDone = true;
    }

    /** Marks this task as incomplete. */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns this task in the format used in Jarvis' task list.
     *
     * @return the task's status and description
     */
    @Override
    public String toString() {
        return type.getDisplayIcon() + "[" + getStatusIcon() + "] " + description;
    }
}
