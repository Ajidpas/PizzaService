package pizza.repository.order;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pizza.domain.Pizza;
import pizza.domain.customer.*;
import pizza.domain.order.*;
import pizza.domain.order.status.EnumStatusState;
import pizza.repository.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repositoryTestContext.xml"})
public class JpaOrderRepositoryInMemTest {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	private Order order;
	
	@Before
	public void initMethod() {
		order = createOrder();
	}
	
	private Order createOrder() {
		Pizza pizza1 = new Pizza("Pizza 1", 100, Pizza.PizzaType.SEA);
		Pizza pizza2 = new Pizza("Pizza 2", 200, Pizza.PizzaType.VEGETABLES);
		pizzaRepository.insertPizza(pizza1);
		pizzaRepository.insertPizza(pizza2);
		Address address = new Address("Kiev", "Kudryashova", "12", "4");
		Customer customer = new Customer("Customer name", address);
		Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
		pizzas.put(pizza1, 3);
		pizzas.put(pizza2, 4);
		return new Order(customer, pizzas);
	}
	
	@Test
	public void testSaveOrderAndGetOrder() {
		orderRepository.saveOrder(order);
		Order expected = order;
		Order result = orderRepository.getOrder(order.getId());
		assertEquals(expected, result);
		assertEquals(expected.getDate(), result.getDate());
		assertEquals(expected.getStatus(), result.getStatus());
		assertEquals(expected.getTotalPrice(), result.getTotalPrice(), 0.0001);
		assertEquals(expected.getAddress(), result.getAddress());
		assertTrue(expected.getCustomer().getId() == result.getCustomer().getId()); // compare POJO with proxy
	}
	
	@Test
	public void testUpdateOrder() {
		orderRepository.saveOrder(order);
		Address updatedAddress = new Address("Lviv", "Evreyska", "1", "5");
		Customer updatedCustomer = new Customer("Updated customer", updatedAddress);
		Date updatedDate = new Date(new java.util.Date().getTime());
		Map<Pizza, Integer> updatedPizzas = new HashMap<Pizza, Integer>();
		StatusState updatedStatus = EnumStatusState.NEW;
		order.setAddress(updatedAddress);
		order.setCustomer(updatedCustomer);
		order.setDate(updatedDate);
		order.setPizzas(updatedPizzas);
		order.setStatus(updatedStatus);
		order.setTotalPrice(order.countPrice());
		orderRepository.updateOrder(order);
		Order expected = order;
		Order result = orderRepository.getOrder(order.getId());
		assertEquals(expected, result);
		assertEquals(expected.getAddress(), result.getAddress());
		assertTrue(expected.getCustomer().getId() == result.getCustomer().getId());
//		assertTrue(expected.getDate().equals(result.getDate())); // not solved
		assertEquals(expected.getStatus(), result.getStatus());
		assertEquals(expected.getTotalPrice(), result.getTotalPrice(), 0.0001);
		
		result = orderRepository.getOrderWithPizzas(order.getId());
		assertEquals(expected.getPizzas(), result.getPizzas());
	}
	
	@Test
	public void testDeleteOrder() {
		orderRepository.saveOrder(order);
		boolean expected = true;
		boolean result = orderRepository.deleteOrder(order.getId());
		assertEquals(expected, result);
		
		expected = false;
		result = orderRepository.deleteOrder(100500);
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetOrderWithPizzas() {
		orderRepository.saveOrder(order);
		Map<Pizza, Integer> expected = order.getPizzas();
		Map<Pizza, Integer> result = 
				orderRepository.getOrderWithPizzas(order.getId()).getPizzas();
		assertEquals(expected, result);
	}
	
	@Test
	public void testUpdateOrderPizzas() {
		orderRepository.saveOrder(order);
		
		// create and insert new pizza
		Pizza newPizza = new Pizza("Updated pizza", 500, Pizza.PizzaType.SEA);
		pizzaRepository.insertPizza(newPizza);
		
		Map<Pizza, Integer> updatedPizzas = new HashMap<>();
		updatedPizzas.put(newPizza, 5);
		order.setPizzas(updatedPizzas);
		orderRepository.updateOrderPizzas(order);
		Map<Pizza, Integer> expected = updatedPizzas;
		Map<Pizza, Integer> result = 
				orderRepository.getOrderWithPizzas(order.getId()).getPizzas();
		assertEquals(expected, result);
	}

}
