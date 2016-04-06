package pizza.repository.order;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.OrderRepository;

public class InMemOrderRepositoryTest {
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	@Test
	public void testSaveOrder() {
		OrderRepository orderRepository = new InMemOrderRepository();
		Pizza pizza1 = new Pizza(1, "First pizza", 1000, PizzaType.MEAT);
		Order newOrder = new Order(customer, new ArrayList<Pizza>(Arrays.asList(pizza1)));
		long orderId = 123;
		newOrder.setId(orderId);
		long expected = orderId;
		long result = orderRepository.saveOrder(newOrder);
		assertEquals(expected, result);
	}

}
