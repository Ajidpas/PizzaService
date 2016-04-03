package pizza.domain.order;

import pizza.domain.order.status.NullOrderStatusException;

public interface StatusState {
	
	StatusState doAction(Order order) throws NullOrderStatusException;

}
