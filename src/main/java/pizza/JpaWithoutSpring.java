package pizza;

import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.domain.order.status.EnumStatusState;

public class JpaWithoutSpring {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		Customer customer = new Customer();
		customer.setName("Kolya");
		
		Address address = new Address("Kiev", "Kudryashova", "456", "64");
		customer.setAddresses(Arrays.asList(address));
		
		Order order = new Order();
		order.setStatus(EnumStatusState.NEW);
		order.setDate(Calendar.getInstance());
		
//		Pizza pizza1 = new Pizza("Pizza 1", 202.3, PizzaType.MEAT);
//		Pizza pizza2 = new Pizza("Pizza 2", 654.12, PizzaType.SEA);

		try {
//			em.getTransaction().begin();
//			em.persist(pizza1);
//			em.persist(pizza2);
//			em.getTransaction().commit();
			
			em.getTransaction().begin();
			
			Pizza pizza3 = em.find(Pizza.class, 1);
			Pizza pizza4 = em.find(Pizza.class, 2);
			
//			order.addPizza(pizza1);
//			order.addPizza(pizza2);
			order.addPizza(pizza3, 3);
			order.addPizza(pizza4, 5);
			
			em.persist(order);
			em.persist(customer);
			em.getTransaction().commit();
		} finally {
			em.close();
			emf.close();
		}
	}

}
