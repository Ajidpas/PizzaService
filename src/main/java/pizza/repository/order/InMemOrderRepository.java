package pizza.repository.order;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.order.Order;
import pizza.repository.Repository;

//@Repository(value = "orderRepository")
public class InMemOrderRepository implements Repository<Order> {
	
	private List<Order> orders;

	@Override
	public Order insert(Order newOrder) {
		if (orders == null) {
			orders = new ArrayList<Order>();
		}
		orders.add(newOrder);
		return newOrder;
	}

	public List<Order> getAll() {
		return orders;
	}

	@Override
	public Order get(int entityId) {
		return orders.get(entityId);
	}

	@Override
	public Order update(Order entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(int entityId) {
		if (orders.remove(entityId) == null) {
			return false;
		}
		return true;
	}

}
