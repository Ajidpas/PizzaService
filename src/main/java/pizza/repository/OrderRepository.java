package pizza.repository;

import pizza.domain.Order;

public interface OrderRepository {

	Long saveOrder(Order newOrder);
	
}
