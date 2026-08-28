/** The structured meaning of one line entered by a Jarvis user. */
public final class ParsedCommand {
    /** The command forms understood by Jarvis. */
    public enum Type {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    private final Type type;
    private final String description;
    private final String firstDetail;
    private final String secondDetail;
    private final int taskNumber;

    private ParsedCommand(Type type, String description, String firstDetail,
                          String secondDetail, int taskNumber) {
        this.type = type;
        this.description = description;
        this.firstDetail = firstDetail;
        this.secondDetail = secondDetail;
        this.taskNumber = taskNumber;
    }

    /** Creates a command with no arguments. */
    public static ParsedCommand simple(Type type) {
        return new ParsedCommand(type, "", "", "", 0);
    }

    /** Creates a command that refers to a task number. */
    public static ParsedCommand forTask(Type type, int taskNumber) {
        return new ParsedCommand(type, "", "", "", taskNumber);
    }

    /** Creates a todo command. */
    public static ParsedCommand todo(String description) {
        return new ParsedCommand(Type.TODO, description, "", "", 0);
    }

    /** Creates a deadline command. */
    public static ParsedCommand deadline(String description, String by) {
        return new ParsedCommand(Type.DEADLINE, description, by, "", 0);
    }

    /** Creates an event command. */
    public static ParsedCommand event(String description, String from, String to) {
        return new ParsedCommand(Type.EVENT, description, from, to, 0);
    }

    /** Returns the command type. */
    public Type getType() {
        return type;
    }

    /** Returns the task description, when present. */
    public String getDescription() {
        return description;
    }

    /** Returns the first detail, such as a deadline or event start. */
    public String getFirstDetail() {
        return firstDetail;
    }

    /** Returns the second detail, such as an event end. */
    public String getSecondDetail() {
        return secondDetail;
    }

    /** Returns the one-based task number, when present. */
    public int getTaskNumber() {
        return taskNumber;
    }
}
