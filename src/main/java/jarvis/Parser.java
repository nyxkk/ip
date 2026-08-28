package jarvis;

/** Converts raw Jarvis input into structured commands. */
public class Parser {
    /** Creates a parser for Jarvis commands. */
    public Parser() {
    }

    /**
     * Parses one complete line entered by the user.
     *
     * @param command the raw command entered by the user
     * @return the structured command
     * @throws JarvisException if the command is not recognized or is malformed
     */
    public ParsedCommand parse(String command) {
        if (command.equals("bye")) {
            return ParsedCommand.simple(ParsedCommand.Type.BYE);
        } else if (command.equals("list")) {
            return ParsedCommand.simple(ParsedCommand.Type.LIST);
        } else if (command.startsWith("mark ")) {
            return ParsedCommand.forTask(ParsedCommand.Type.MARK,
                    parseTaskNumber(command.substring(5)));
        } else if (command.startsWith("unmark ")) {
            return ParsedCommand.forTask(ParsedCommand.Type.UNMARK,
                    parseTaskNumber(command.substring(7)));
        } else if (command.equals("delete") || command.startsWith("delete ")) {
            return ParsedCommand.forTask(ParsedCommand.Type.DELETE,
                    parseTaskNumber(command.substring(7)));
        } else if (command.equals("todo") || command.startsWith("todo ")) {
            return ParsedCommand.todo(requireText(command.substring(4), "todo"));
        } else if (command.startsWith("deadline ")) {
            return parseDeadline(command);
        } else if (command.startsWith("event ")) {
            return parseEvent(command);
        }
        throw new JarvisException("I'm sorry, but I don't know what that means.");
    }

    private ParsedCommand parseDeadline(String command) {
        int marker = command.indexOf(" /by ");
        if (marker < 0) {
            throw new JarvisException("A deadline must include /by followed by a date or time.");
        }
        String description = requireText(command.substring(9, marker), "deadline");
        String by = requireText(command.substring(marker + 5), "deadline");
        return ParsedCommand.deadline(description, by);
    }

    private ParsedCommand parseEvent(String command) {
        int fromMarker = command.indexOf(" /from ");
        int toMarker = command.indexOf(" /to ", fromMarker + 7);
        if (fromMarker < 0 || toMarker < 0) {
            throw new JarvisException("An event must include /from and /to times.");
        }
        String description = requireText(command.substring(6, fromMarker), "event");
        String from = requireText(command.substring(fromMarker + 7, toMarker), "event");
        String to = requireText(command.substring(toMarker + 5), "event");
        return ParsedCommand.event(description, from, to);
    }

    private int parseTaskNumber(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException exception) {
            throw new JarvisException("The task number must be a whole number.");
        }
    }

    private String requireText(String text, String commandName) {
        String trimmedText = text.trim();
        if (trimmedText.isEmpty()) {
            if (commandName.equals("todo")) {
                throw new JarvisException("The description of a todo cannot be empty.");
            }
            throw new JarvisException("The " + commandName + " details cannot be empty.");
        }
        return trimmedText;
    }
}
