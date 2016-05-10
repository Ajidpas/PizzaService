package pizza.service.orderservice;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pizza.domain.Pizza;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.OrderService;
import pizza.service.PizzaService;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repositoryTestContext.xml"})
public class SimpleOrderServiceInMemTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@Test
	public void testPlaceNewOrder() throws NotSupportedPizzasNumberException, NoSuchPizzaException, WrongStatusException {
		Address address = new Address("Kiev", "Kudriashova", "18", "1");
		Customer customer = new Customer("Customer name", address);
		Pizza pizza1 = new Pizza("Pizza 1", 100, Pizza.PizzaType.SEA);
		Pizza pizza2 = new Pizza("Pizza 2", 200, Pizza.PizzaType.VEGETABLES);
		Pizza pizza3 = new Pizza("Pizza 3", 300, Pizza.PizzaType.VEGETARIAN);
		pizzaService.insertPizza(pizza1);
		pizzaService.insertPizza(pizza2);
		pizzaService.insertPizza(pizza3);
		Order order = orderService.placeNewOrder(customer, address, pizza1.getId(), pizza2.getId(), pizza3.getId());
		Order expected = order;
		Order result = orderService.getOrder(order.getId());
		assertEquals(expected, result);
		assertEquals(expected.getCustomer(), result.getCustomer());
		assertEquals(expected.getAddress(), result.getAddress());
		
		Map<Pizza, Integer> pizzas = orderService.getOrderPizzas(order.getId());
		int expectedPizzasNumber = 3;
		int resultPizzasNumber = pizzas.keySet().size();
		assertEquals(expectedPizzasNumber, resultPizzasNumber);
	}
	
	

}
