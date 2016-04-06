package pizza.domain.discounts;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import pizza.domain.Discount;
import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

public class FourthPizzaDiscountTest {
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	@Test
	public void getBiggestPricePizzaTest() throws NoSuchPizzaException, 
			NotSupportedPizzasNumberException, WrongStatusException {
		
		Pizza pizza1 = new Pizza(1, "First pizza", 1000, PizzaType.MEAT);
		Pizza pizza2 = new Pizza(2, "Second pizza", 2000, PizzaType.SEA);
		Pizza pizza3 = new Pizza(3, "First pizza", 3000, PizzaType.VEGETABLES);
		Order order;
		
		// add three pizzas
		order = new Order(customer, new ArrayList<Pizza>(Arrays.asList(pizza1, pizza2, pizza3)));
		Discount discount = new FourthPizzaDiscount(order);
		double expected = 0;
		double result = discount.getDiscount();
		assertEquals(expected, result, 0.001);
		
		// add fourth pizza that has biggest price that is 999999999
		Pizza pizza4 = new Pizza(999, "Most expencive pizza", 999999999, PizzaType.MEAT);
		order.addPizza(pizza4);
		expected = pizza4.getPrice() * 30 / 100;
		result = discount.getDiscount();
		assertEquals(expected, result, 0.001);
	}

}
