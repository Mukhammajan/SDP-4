package exception;

/** Single, high-level error type the Facade exposes instead of leaking subsystem failures. */
public class CarOperationException extends Exception {
    public CarOperationException(String message) {
        super(message);
    }

    public CarOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}