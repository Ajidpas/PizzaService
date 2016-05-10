package pizza.repository.order;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pizza.domain.order.Order;
import pizza.repository.OrderRepository;

@Repository(value = "orderRepository")
@Transactional
public class JpaOrderRepository implements OrderRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public JpaOrderRepository() {}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAllOrders() {
		TypedQuery<Order> query = em.createQuery("SELECT o FROM t_order o", Order.class);
		return query.getResultList();
	}

	@Override
	public Order saveOrder(Order newOrder) {
		em.persist(newOrder);
		return newOrder;
	}
	
	@Override
	public Order getOrder(int id) {
		Order order = em.find(Order.class, id);
//		order.getAddress();
//		order.getCustomer();
		return order;
	}

	@Override
	public Order updateOrder(Order order) {
		int id = order.getId();
		Order oldOrder = em.find(Order.class, id);
		oldOrder.setAddress(order.getAddress());
		oldOrder.setCustomer(order.getCustomer());
		oldOrder.setDate(order.getDate());
		oldOrder.setPizzas(order.getPizzas());
		oldOrder.setStatus(order.getStatus());
		oldOrder.setTotalPrice(order.getTotalPrice());
		return oldOrder;
	}

	@Override
	public boolean deleteOrder(int id) {
		Order order = em.find(Order.class, id);
		if (order == null) {
			return false;
		}	
		em.remove(order);
		return true;
	}

	@Override
	public Order getOrderWithPizzas(Integer orderId) {
		Order order = em.find(Order.class, orderId);
		order.getPizzas().size();
		return order;
	}

	@Override
	public void updateOrderPizzas(Order orderWithPizzas) {
		int orderId = orderWithPizzas.getId();
		Order order = em.find(Order.class, orderId);
		order.setPizzas(orderWithPizzas.getPizzas());
	}

}
