package pizza;

import java.util.Arrays;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pizza.domain.AccumulativeCard;
import pizza.domain.Pizza;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.domain.order.status.EnumStatusState;
import pizza.domain.order.status.NullOrderStatusException;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

public class JpaWithoutSpring {
	
	public static void main(String[] args) throws NoSuchPizzaException, NullOrderStatusException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		Customer customer = new Customer();
		customer.setName("Vasya with card for repository");
		
		AccumulativeCard accumulativeCard = new AccumulativeCard();
		accumulativeCard.setCustomer(customer);
		
		Address address1 = new Address("Kiev", "Kudryashova", "456", "64");
		Address address2 = new Address("Lviv", "Saksaganska", "87", "31");
		customer.setAddresses(Arrays.asList(address1, address2));
		
		Order order = new Order();
		order.setStatus(EnumStatusState.NEW);
		order.setDate(Calendar.getInstance());
		
//		Pizza pizza1 = new Pizza("Updated pizza", 5000, PizzaType.SEA);
//		Pizza pizza2 = new Pizza("Updated pizza too", 3000, PizzaType.SEA);

		try {
//			em.getTransaction().begin();
//			em.persist(pizza1);
//			em.persist(pizza2);
//			em.getTransaction().commit();
			
			em.getTransaction().begin();
			
			Pizza pizza3 = em.find(Pizza.class, 5);
			Pizza pizza4 = em.find(Pizza.class, 6);
			
//			order.addPizza(pizza1);
//			order.addPizza(pizza2);
			order.addPizza(pizza3, 3);
			order.addPizza(pizza4, 5);
			
			em.persist(order);
			em.persist(customer);
			em.persist(accumulativeCard);
			em.getTransaction().commit();
			
		} finally {
			em.close();
			emf.close();
		}
	}

}
