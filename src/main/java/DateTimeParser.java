import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Locale;

/** Parses and formats the date and time values used by Jarvis tasks. */
public final class DateTimeParser {
    private static final DateTimeFormatter DISPLAY_DATE =
            DateTimeFormatter.ofPattern("MMM dd uuuu", Locale.ENGLISH);
    private static final DateTimeFormatter DISPLAY_DATE_TIME =
            DateTimeFormatter.ofPattern("MMM dd uuuu HH:mm", Locale.ENGLISH);
    private static final DateTimeFormatter STORAGE_DATE_TIME =
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm", Locale.ENGLISH);
    private static final List<DateTimeFormatter> DATE_FORMATTERS = List.of(
            DateTimeFormatter.ISO_LOCAL_DATE,
            strictFormatter("d/M/uuuu"));
    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = List.of(
            strictFormatter("uuuu-MM-dd HHmm"),
            strictFormatter("uuuu-MM-dd HH:mm"),
            strictFormatter("d/M/uuuu HHmm"),
            strictFormatter("d/M/uuuu HH:mm"));

    private DateTimeParser() {
        // Utility class; do not instantiate.
    }

    /** Parses a date in ISO format or day/month/year format. */
    public static LocalDate parseDate(String text) {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(text, formatter);
            } catch (DateTimeParseException exception) {
                // Try the next supported format.
            }
        }
        throw new DateTimeParseException("Unsupported date", text, 0);
    }

    /** Parses a date and time in ISO-style or day/month/year format. */
    public static LocalDateTime parseDateTime(String text) {
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(text, formatter);
            } catch (DateTimeParseException exception) {
                // Try the next supported format.
            }
        }
        throw new DateTimeParseException("Unsupported date and time", text, 0);
    }

    /** Formats a date for display to a Jarvis user. */
    public static String formatDate(LocalDate date) {
        return date.format(DISPLAY_DATE);
    }

    /** Formats a date and time for display to a Jarvis user. */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_DATE_TIME);
    }

    /** Formats a date in the stable representation used by the save file. */
    public static String formatStorageDate(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /** Formats a date and time in the stable representation used by the save file. */
    public static String formatStorageDateTime(LocalDateTime dateTime) {
        return dateTime.format(STORAGE_DATE_TIME);
    }

    private static DateTimeFormatter strictFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)
                .withResolverStyle(ResolverStyle.STRICT);
    }
}
