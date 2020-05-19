package by.avdeev.pizzeria.service;

/**
 * Exception for sevice layer.
 */
public class ServiceException extends Exception {
    private static final long serialVersionUID = 1698451651215165115L;

    /**
     * Public constructor with no args.
     */
    public ServiceException() {
    }

    /**
     * @param message ${@link String} parameter.
     */
    public ServiceException(final String message) {
        super(message);
    }

    /**
     * @param message ${@link String} parameter.
     * @param cause   ${@link Throwable} parameter.
     */
    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause ${@link Throwable} parameter.
     */
    public ServiceException(final Throwable cause) {
        super(cause);
    }
}
