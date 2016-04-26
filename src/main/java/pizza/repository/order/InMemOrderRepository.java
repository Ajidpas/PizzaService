package pizza.repository.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pizza.domain.order.Order;
import pizza.repository.OrderRepository;

@Repository(value = "orderRepository")
public class InMemOrderRepository implements OrderRepository {
	
	private List<Order> orders;

	@Override
	public Order saveOrder(Order newOrder) {
		if (orders == null) {
			orders = new ArrayList<Order>();
		}
		orders.add(newOrder);
		return newOrder;
	}

	public List<Order> getAllOrders() {
		return orders;
	}

	@Override
	public Order getOrder(int id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Order updateOrder(Order order) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteOrder(int id) {
		throw new UnsupportedOperationException();
	}

}
