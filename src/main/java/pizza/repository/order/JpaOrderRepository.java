package pizza.repository.order;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pizza.domain.order.Order;
import pizza.repository.AbstractRepository;

@Repository(value = "orderRepository")
@Transactional
public class JpaOrderRepository extends AbstractRepository<Order> {
	
	@PersistenceContext
	private EntityManager em;
	
	public JpaOrderRepository() {}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAll() {
		TypedQuery<Order> query = em.createQuery("SELECT o FROM t_order o", Order.class);
		return query.getResultList();
	}

	@Override
	public Order update(Order order) {
		int id = order.getId();
		Order oldOrder = em.find(Order.class, id);
		oldOrder.setAddress(order.getAddress());
		oldOrder.setCustomer(order.getCustomer());
		oldOrder.setDate(order.getDate());
		oldOrder.setPizzas(order.getPizzas());
		oldOrder.setStatus(order.getStatus());
		oldOrder.setTotalPrice(order.getTotalPrice());
		em.flush();
		return oldOrder;
	}

}
