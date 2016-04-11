package pizza.repository.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pizza.domain.order.Order;
import pizza.repository.OrderRepository;

@Repository(value = "orderRepository")
public class InMemOrderRepository implements OrderRepository {
	
	private List<Order> orders;

	public Long saveOrder(Order newOrder) {
		if (orders == null) {
			orders = new ArrayList<Order>();
		}
		orders.add(newOrder);
		return newOrder.getId();
	}

	public List<Order> getAllOrders() {
		return orders;
	}

}
