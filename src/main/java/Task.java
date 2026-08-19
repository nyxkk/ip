/**
 * Represents one task and whether it has been completed.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates an incomplete task with the given description.
     *
     * @param description the text describing the task
     */
    public Task(String description) {
        this.description = description;
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
        return "[" + getStatusIcon() + "] " + description;
    }
}
