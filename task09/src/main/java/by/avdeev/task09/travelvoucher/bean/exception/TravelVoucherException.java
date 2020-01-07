package by.avdeev.task09.travelvoucher.bean.exception;

public class TravelVoucherException extends Exception {
    private static final long serialVersionUID = 1L;

    public TravelVoucherException() {
    }

    public TravelVoucherException(String message) {
        super(message);
    }

    public TravelVoucherException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelVoucherException(Throwable cause) {
        super(cause);
    }
}
