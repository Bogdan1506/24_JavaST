package by.avdeev.task09.car.bean.exception;

public class CarException extends Exception {
    public CarException() {
    }

    public CarException(String message) {
        super(message);
    }

    public CarException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarException(Throwable cause) {
        super(cause);
    }
}
