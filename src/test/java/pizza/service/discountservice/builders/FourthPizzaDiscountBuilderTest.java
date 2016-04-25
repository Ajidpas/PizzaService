package pizza.service.discountservice.builders;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Customer;
import pizza.domain.discounts.FourthPizzaDiscount;
import pizza.domain.order.Order;
import pizza.service.discountservice.DiscountBuilder;

public class FourthPizzaDiscountBuilderTest {
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	@Test
	public void testBuildDiscount() {
		DiscountBuilder discountBuilder = new FourthPizzaDiscountBuilder();
		
		// create discount basis on the following order
		Pizza pizza = new Pizza(1, "Some pizza", 50.0, PizzaType.MEAT);
		Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
		pizzas.put(pizza, 1);
		Order order = new Order(customer, pizzas);
		
		// get discount for order with one pizza
		double expected = Optional.of(new FourthPizzaDiscount(order)).get().getDiscount();
		double result = discountBuilder.buildDiscount(order).get().getDiscount();
		assertEquals(expected, result, 0.0001);
		
		// create order with 5 pizzas
		Pizza pizza1 = new Pizza(1, "Some pizza 1", 10.0, PizzaType.MEAT);
		Pizza pizza2 = new Pizza(2, "Some pizza 2", 20.0, PizzaType.MEAT);
		Pizza pizza3 = new Pizza(3, "Some pizza 3", 30.0, PizzaType.MEAT);
		Pizza pizza4 = new Pizza(4, "Some pizza 4", 40.0, PizzaType.MEAT);
		Pizza pizza5 = new Pizza(5, "Some pizza 5", 50.0, PizzaType.MEAT);
		pizzas = new HashMap<Pizza, Integer>();
		pizzas.put(pizza1, 1);
		pizzas.put(pizza2, 1);
		pizzas.put(pizza3, 1);
		pizzas.put(pizza4, 1);
		pizzas.put(pizza5, 1);
		order = new Order(customer, pizzas);
		expected = Optional.of(new FourthPizzaDiscount(order)).get().getDiscount();
		result = discountBuilder.buildDiscount(order).get().getDiscount();
		assertEquals(expected, result, 0.0001);
	}

}
