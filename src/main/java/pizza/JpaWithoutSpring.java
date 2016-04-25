package pizza;

import java.util.Calendar;
import javax.persistence.*;
import pizza.domain.Pizza;
import pizza.domain.order.Order;
import pizza.domain.order.status.EnumStatusState;

public class JpaWithoutSpring {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		Order order = new Order();
		order.setStatus(EnumStatusState.NEW);
		order.setDate(Calendar.getInstance());

		try {
			em.getTransaction().begin();
			Pizza pizza1 = em.find(Pizza.class, 1);
			Pizza pizza2 = em.find(Pizza.class, 2);
			order.addPizza(pizza1);
			order.addPizza(pizza2);
			em.persist(order);
			em.getTransaction().commit();
		} finally {
			em.close();
			emf.close();
		}
	}

}
