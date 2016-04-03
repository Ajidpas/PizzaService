package pizza.domain.order.status;

public class NullOrderStatusException extends Exception {
	
	private static final String EXCEPTION_EXPLANATION = 
			"Order has no any status!";
	
	public NullOrderStatusException() {}
	
	public NullOrderStatusException(String message) {
		super(message);
	}
	
	public String toString() {
		return EXCEPTION_EXPLANATION;
	}

}
