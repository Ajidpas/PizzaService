package pizza.repository.order;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.Repository;

public class InMemOrderRepositoryTest {
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	@Test
	public void testSaveOrder() {
		Repository<Order> orderRepository = new InMemOrderRepository();
		Pizza pizza1 = new Pizza(1, "First pizza", 1000, PizzaType.MEAT);
		Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
		pizzas.put(pizza1, 1);
		Order newOrder = new Order(customer, pizzas);
		int orderId = 123;
		newOrder.setId(orderId);
		long expected = orderId;
		long result = orderRepository.insert(newOrder).getId();
		assertEquals(expected, result);
	}

}
