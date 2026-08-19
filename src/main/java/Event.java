/** A task that takes place at a stated time. */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Creates an event.
     *
     * @param description what the event is
     * @param from the user-provided event start text
     * @param to the user-provided event end text
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    protected String getTaskType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
