package pizza.service.orderservice.exceptions;

public class EmptyOrderException extends Exception {
	
	public static final String EXCEPTION_DESCRIPTION = 
			"There are no any pizzas in this order!";
	
	public EmptyOrderException() {}
	
	public EmptyOrderException(String message) {
		super(message);
	}
	
	public String toString() {
		return EXCEPTION_DESCRIPTION;
	}

}
