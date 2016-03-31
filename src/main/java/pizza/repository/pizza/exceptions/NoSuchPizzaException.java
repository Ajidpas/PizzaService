package pizza.repository.pizza.exceptions;

public class NoSuchPizzaException extends Exception {
	
	public NoSuchPizzaException() {}
	
	public NoSuchPizzaException(String message) {
		super(message);
	}
	
	public String toString() {
		return "There is no pizza with such id!";
	}

}
