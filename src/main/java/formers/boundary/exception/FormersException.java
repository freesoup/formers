package formers.boundary.exception;

import javax.servlet.ServletException;

public class FormersException extends ServletException {

    public FormersException() {
        super();
    }

    public FormersException(String message) {
        super(message);
    }

    public FormersException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public FormersException(Throwable rootCause) {
        super(rootCause);
    }
}
