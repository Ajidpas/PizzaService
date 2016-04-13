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
		ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext("repositoryContext.xml");
		ConfigurableApplicationContext serviceContext = new ClassPathXmlApplicationContext(new String[]{"serviceContext.xml"}, repositoryContext);
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, serviceContext);
		OrderService orderService = (OrderService) appContext.getBean("orderService");
		Order order = null;
		Order order1 = null;
		try {
			order = orderService.placeNewOrder(customer, 1, 1, 1);
			order1 = orderService.placeNewOrder(customer, 1);
		} catch (NotSupportedPizzasNumberException e) {
			e.printStackTrace();
		} catch (NoSuchPizzaException e) {
			e.printStackTrace();
		} catch (WrongStatusException e) {
			e.printStackTrace();
		}
		System.out.println(order.hashCode() + ": " + order);
		System.out.println(order1.hashCode() + ": " + order1);
		repositoryContext.close();
		serviceContext.close();
		appContext.close();
		
	}

}
