package by.avdeev.task10final.textfile.bean.exception;

public class FileException extends Exception {
    private static final long serialVersionUID = 1L;

    public FileException() {
    }

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileException(Throwable cause) {
        super(cause);
    }
}
