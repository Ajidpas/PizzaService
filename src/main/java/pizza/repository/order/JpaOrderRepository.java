package pizza.repository.order;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pizza.domain.order.Order;
import pizza.repository.OrderRepository;

@Repository(value = "orderRepository")
public class JpaOrderRepository implements OrderRepository {
	
	private EntityManagerFactory emf;
	
	private EntityManager em;
	
	public JpaOrderRepository() {}

	public JpaOrderRepository(EntityManager em) {
		super();
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAllOrders() {
		Query query = em.createQuery("SELECT orders FROM pizza_service_jpa.order orders", Order.class);
		return query.getResultList();
	}

	@Override
	public Order saveOrder(Order newOrder) {
		em.getTransaction().begin();
		em.persist(newOrder);
		em.getTransaction().commit();
		return newOrder;
	}

	@Override
	public Order getOrder(int id) {
		return em.find(Order.class, id);
	}

	@Override
	public Order updateOrder(Order order) {
		int id = order.getId();
		em.getTransaction().begin();
		Order oldOrder = em.find(Order.class, id);
		oldOrder.setAddress(order.getAddress());
		oldOrder.setCustomer(order.getCustomer());
		oldOrder.setDate(order.getDate());
		oldOrder.setPizzas(order.getPizzas());
		oldOrder.setStatus(order.getStatus());
		oldOrder.setTotalPrice(order.getTotalPrice());
		em.getTransaction().commit();
		return oldOrder;
	}

	@Override
	public void deleteOrder(int id) {
		Order order = em.find(Order.class, id);
		em.getTransaction().begin();
		em.remove(order);
		em.getTransaction().commit();		
	}
	
	@PostConstruct
	private void initEntityManager() {
		emf = Persistence.createEntityManagerFactory("jpa");
		em = emf.createEntityManager();
	}
	
	@PreDestroy
	private void closeEntityManager() {
		em.close();
		emf.close();
	}

}
