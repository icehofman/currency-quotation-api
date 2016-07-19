package quotation.exception;

public class IllegalDateException extends Exception {

    private static final long serialVersionUID = 1671197479744268255L;

    public IllegalDateException(String error) {
        super(error);
    }
}
