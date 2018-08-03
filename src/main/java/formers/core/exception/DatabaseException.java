package formers.core.exception;

public class DatabaseException extends Exception {

    public DatabaseException() {
        super();
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public DatabaseException(Throwable rootCause) {
        super(rootCause);
    }
}
