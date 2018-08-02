package formers.core.exception;

public class InsufficientAuthorityException extends Exception {

    public InsufficientAuthorityException() {
        super();
    }

    public InsufficientAuthorityException(String message) {
        super(message);
    }

    public InsufficientAuthorityException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public InsufficientAuthorityException(Throwable rootCause) {
        super(rootCause);
    }
}
