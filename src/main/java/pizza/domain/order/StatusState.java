package pizza.domain.order;

public interface StatusState {
	
	StatusState doAction(Order order);

}
