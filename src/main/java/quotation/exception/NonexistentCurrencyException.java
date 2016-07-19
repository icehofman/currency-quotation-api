package quotation.exception;

public class NonexistentCurrencyException extends Exception {
	
	private static final long serialVersionUID = -7027991453890606456L;

	public NonexistentCurrencyException(String error) {
		super(error);
	}
}
