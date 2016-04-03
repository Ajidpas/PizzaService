package pizza.repository;

import pizza.domain.order.Order;

public interface OrderRepository {

	Long saveOrder(Order newOrder);
	
}
