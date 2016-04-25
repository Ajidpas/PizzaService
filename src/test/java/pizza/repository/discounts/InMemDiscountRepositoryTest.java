package pizza.repository.discounts;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pizza.domain.Discount;
import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Customer;
import pizza.domain.discounts.FourthPizzaDiscount;
import pizza.domain.order.Order;
import pizza.repository.DiscountRepository;

public class InMemDiscountRepositoryTest {
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	@Test
	public void testSaveDiscount() {
		Pizza pizza1 = new Pizza(1, "First pizza", 1000, PizzaType.MEAT);
		Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
		pizzas.put(pizza1, 1);
		Order order = new Order(customer, pizzas);
		Discount discount = new FourthPizzaDiscount(order);
		DiscountRepository discountRepository = new InMemDiscountRepository();
		Discount expected = discount;
		Discount result = discountRepository.saveDiscount(discount);
		assertEquals(expected, result);
	}

}
