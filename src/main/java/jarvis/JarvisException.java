package jarvis;

/** Represents an input error that can be explained to a Jarvis user. */
public class JarvisException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /** Creates an input error with a user-facing explanation. */
    public JarvisException(String message) {
        super(message);
    }
}
 
