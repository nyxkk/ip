/** A task that must be completed by a stated date or time. */
public class Deadline extends Task {
    private final String by;

    /**
     * Creates a deadline.
     *
     * @param description what must be done
     * @param by the user-provided deadline text
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTaskType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
