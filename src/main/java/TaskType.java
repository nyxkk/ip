/** The finite set of task categories supported by Jarvis. */
public enum TaskType {
    /** A task without a date or time. */
    TODO("[T]"),
    /** A task that must be completed by a date or time. */
    DEADLINE("[D]"),
    /** A task that happens between a start and end time. */
    EVENT("[E]"),
    /** A generic task retained for compatibility with the original model. */
    GENERIC("");

    private final String displayIcon;

    TaskType(String displayIcon) {
        this.displayIcon = displayIcon;
    }

    /**
     * Returns the marker printed before a task's status icon.
     *
     * @return this type's display marker
     */
    public String getDisplayIcon() {
        return displayIcon;
    }
}
