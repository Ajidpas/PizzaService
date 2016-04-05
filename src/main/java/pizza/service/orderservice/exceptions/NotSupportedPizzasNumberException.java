package pizza.service.orderservice.exceptions;

public class NotSupportedPizzasNumberException extends Exception {
	
	private static final String EXCEPTION_EXPLANATION = 
			"Pizzas number should be in the range from 1 to 10!";
	
	public NotSupportedPizzasNumberException() {}
	
	public NotSupportedPizzasNumberException(String message) {
		super(message);
	}
	
	public String toString() {
		return EXCEPTION_EXPLANATION;
	}

}
