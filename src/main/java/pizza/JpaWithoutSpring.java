package pizza;

import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pizza.domain.AccumulativeCard;
import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
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

		try {
			persistPizzas(em);
			persistOrder(em, customer, accumulativeCard);
			
		} finally {
			em.close();
			emf.close();
		}
	}

	private static void persistPizzas(EntityManager em) {
		Pizza pizza1 = new Pizza("Alpachino 2", 300, PizzaType.MEAT);
		Pizza pizza2 = new Pizza("Americano 2", 700, PizzaType.VEGETABLES);
		em.getTransaction().begin();
		em.persist(pizza1);
		em.persist(pizza2);
		em.getTransaction().commit();
	}

	private static void persistOrder(EntityManager em, Customer customer, AccumulativeCard accumulativeCard) {
		Order order = new Order();
		order.setStatus(EnumStatusState.NEW);
		order.setDate(Calendar.getInstance());
		Pizza pizza3 = em.find(Pizza.class, 1); // hard code
		Pizza pizza4 = em.find(Pizza.class, 2); // hard code
		em.getTransaction().begin();
		order.addPizza(pizza3, 9);
		order.addPizza(pizza4, 8);
		em.persist(order);
		em.persist(customer);
		em.persist(accumulativeCard);
		em.getTransaction().commit();
	}

}
