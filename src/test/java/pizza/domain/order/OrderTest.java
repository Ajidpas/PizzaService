package pizza.domain.order;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Customer;

public class OrderTest {
	
	private Customer customer;
	
	private Pizza pizza;
	
	private Order order;
	
	@Before
	public void init() {
		customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
		pizza = new Pizza(999, "Most expencive pizza", 999999999, PizzaType.MEAT);
		order = new Order(customer, new ArrayList<Pizza>(Arrays.asList(pizza)));
	}
	
	@Test
	public void testAddPizza() {
		
		// let's check that pizza list has size 1
		int expected = 1;
		int result = order.getPizzaList().size();
		assertEquals(expected, result);
		
		// let's add one pizza
		pizza = new Pizza(111, "Cheapest pizza", 0.1, PizzaType.VEGETABLES);
		order.addPizza(pizza);
		expected = 2;
		result = order.getPizzaList().size();
		assertEquals(expected, result);
		
		// let's add null value
		order.addPizza(null);
		expected = 2;
		result = order.getPizzaList().size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testDeletePizza() {
		// check the number of pizzas
		int expected = 1;
		int result = order.getPizzaList().size();
		assertEquals(expected, result);
		
		// delete one pizza with id 111 (there is no such pizza in this order)
		order.deletePizza(111);
		expected = 1;
		result = order.getPizzaList().size();
		assertEquals(expected, result);
		
		// try to delete pizza is presented in the order
		order.deletePizza(999);
		expected = 0;
		result = order.getPizzaList().size();
		assertEquals(expected, result);
		
		// try to delete this pizza again
		order.deletePizza(999);
		expected = 0;
		result = order.getPizzaList().size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetOrderPrice() {
		// there is one pizza in the order
		double expected = pizza.getPrice();
		double result = order.getOrderPrice();
		assertEquals(expected, result, 0.0001);
		
		// let's add one pizza again
		Pizza nextPizza = new Pizza(333, "Cheaper pizza", 11111111, PizzaType.VEGETARIAN);
		order.addPizza(nextPizza);
		expected = pizza.getPrice() + nextPizza.getPrice();
		result = order.getOrderPrice();
		assertEquals(expected, result, 0.0001);
		
		// let's add one pizza again
		Pizza nextNextPizza = new Pizza(444, "Medium pizza", 44444, PizzaType.SEA);
		order.addPizza(nextNextPizza);
		expected = pizza.getPrice() + nextPizza.getPrice() + nextNextPizza.getPrice();
		result = order.getOrderPrice();
		assertEquals(expected, result, 0.0001);
	}

}
