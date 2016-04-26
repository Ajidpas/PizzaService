package pizza;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pizza.domain.customer.Customer;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.OrderService;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

public class SpringWithJpaApp {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("appContext.xml");
		OrderService orderService = (OrderService) appContext.getBean("orderService");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		Customer customer;
		
		try {
			customer = em.find(Customer.class, 53);
		} finally {
			em.close();
			emf.close();
		}
		
		try {
			System.out.println(orderService.placeNewOrder(customer, 5, 6, 33, 34));
		} catch (NotSupportedPizzasNumberException | NoSuchPizzaException | WrongStatusException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		appContext.close();
	}

}
