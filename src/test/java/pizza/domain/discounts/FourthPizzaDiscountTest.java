package pizza.domain.discounts;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Customer;
import pizza.domain.discountprovider.Discount;
import pizza.domain.discountprovider.discounts.FourthPizzaDiscount;
import pizza.domain.order.Order;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.orderservice.SimpleOrderService;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

public class FourthPizzaDiscountTest {
	
	private Discount discount = new FourthPizzaDiscount();
	
	private SimpleOrderService service = new SimpleOrderService();
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	@Test
	public void getBiggestPricePizzaTest() throws NoSuchPizzaException, 
			NotSupportedPizzasNumberException, WrongStatusException {
		Order order;
		
		// add three pizzas
		order = service.placeNewOrder(customer, 1, 1, 1);
		double expected = 0;
		double result = discount.getDiscount(order);
		assertEquals(expected, result, 0.001);
		
		// add fourth pizza that has biggest price that is 999999999
		Pizza pizza = new Pizza(999, "Most expencive pizza", 999999999, PizzaType.MEAT);
		order.addPizza(pizza);
		expected = pizza.getPrice() * 30 / 100;
		result = discount.getDiscount(order);
		assertEquals(expected, result, 0.001);
	}

}
