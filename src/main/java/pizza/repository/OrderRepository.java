package pizza.repository;

import java.util.List;

import pizza.domain.order.Order;

public interface OrderRepository {

	Long saveOrder(Order newOrder);
	
	List<Order> getAllOrders();
	
}
