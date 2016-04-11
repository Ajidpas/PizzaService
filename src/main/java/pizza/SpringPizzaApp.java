package pizza;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.OrderService;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

public class SpringPizzaApp {

	public static void main(String[] args) {
		Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("appContext.xml");
		OrderService orderService = (OrderService) appContext.getBean("orderService");
		Order order = null;
		try {
			order = orderService.placeNewOrder(customer, 1, 1, 1);
		} catch (NotSupportedPizzasNumberException e) {
			e.printStackTrace();
		} catch (NoSuchPizzaException e) {
			e.printStackTrace();
		} catch (WrongStatusException e) {
			e.printStackTrace();
		}
		System.out.println(order);
		appContext.close();
	}

}
