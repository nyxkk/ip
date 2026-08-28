package jarvis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/** Tests conversion of user input into structured commands. */
public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    public void parse_eventCommand_returnsAllParts() {
        ParsedCommand command = parser.parse(
                "event project meeting /from 2019-12-09 1400 /to 2019-12-09 1600");

        assertEquals(ParsedCommand.Type.EVENT, command.getType());
        assertEquals("project meeting", command.getDescription());
        assertEquals("2019-12-09 1400", command.getFirstDetail());
        assertEquals("2019-12-09 1600", command.getSecondDetail());
    }

    @Test
    public void parse_deleteCommand_returnsOneBasedTaskNumber() {
        ParsedCommand command = parser.parse("delete 3");

        assertEquals(ParsedCommand.Type.DELETE, command.getType());
        assertEquals(3, command.getTaskNumber());
    }

    @Test
    public void parse_findCommand_returnsKeyword() {
        ParsedCommand command = parser.parse("find Book");

        assertEquals(ParsedCommand.Type.FIND, command.getType());
        assertEquals("Book", command.getDescription());
    }

    @Test
    public void parse_todoWithoutDescription_throwsException() {
        JarvisException exception = assertThrows(JarvisException.class,
                () -> parser.parse("todo"));

        assertEquals("The description of a todo cannot be empty.", exception.getMessage());
    }
}
