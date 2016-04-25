package pizza;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pizza.domain.Pizza;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;

public class JpaWithoutSpring {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		Pizza pizza = new Pizza();
		pizza.setName("Pizza from JPA 1");
		pizza.setPrice(74.15);
		pizza.setType(Pizza.PizzaType.MEAT);
		
		try {
			em.getTransaction().begin();
			em.persist(pizza);
			em.getTransaction().commit();
		} finally {
			em.close();
			emf.close();
		}
	}

}
