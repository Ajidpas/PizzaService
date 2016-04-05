package pizza.service.orderservice.exceptions;

public class WrongStatusException extends Exception {
	
	private static final String EXCEPTION_DESCRIPTION = 
			"Wrong order status for this operation!";
	
	public WrongStatusException() {}
	
	public WrongStatusException(String message) {
		super(message);
	}
	
	public String toString() {
		return EXCEPTION_DESCRIPTION;
	}

}
