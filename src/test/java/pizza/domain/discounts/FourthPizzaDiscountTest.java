package pizza.domain.discounts;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pizza.domain.Discount;
import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.orderservice.SimpleOrderService;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

public class FourthPizzaDiscountTest {
	
	private SimpleOrderService service = new SimpleOrderService();
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	@Test
	public void getBiggestPricePizzaTest() throws NoSuchPizzaException, 
			NotSupportedPizzasNumberException, WrongStatusException {
		Order order;
		
		// add three pizzas
		order = service.placeNewOrder(customer, 1, 1, 1);
		Discount discount = new FourthPizzaDiscount(order);
		double expected = 0;
		double result = discount.getDiscount();
		assertEquals(expected, result, 0.001);
		
		// add fourth pizza that has biggest price that is 999999999
		Pizza pizza = new Pizza(999, "Most expencive pizza", 999999999, PizzaType.MEAT);
		order.addPizza(pizza);
		expected = pizza.getPrice() * 30 / 100;
		result = discount.getDiscount();
		assertEquals(expected, result, 0.001);
	}

}
