package pizza.repository;

import java.util.List;

import pizza.domain.order.Order;

public interface OrderRepository {

	List<Order> getAllOrders();
	
	Order saveOrder(Order newOrder);
	
	Order getOrder(int id);
	
	Order updateOrder(Order order);
	
	boolean deleteOrder(int id);

	Order getOrderWithPizzas(Integer orderId);

	void updateOrderPizzas(Order orderWithPizzas);
	
}
