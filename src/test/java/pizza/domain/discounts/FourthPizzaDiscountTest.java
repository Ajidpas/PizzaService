package pizza.domain.discounts;

import static org.junit.Assert.*;

import org.junit.Test;

import pizza.domain.Customer;
import pizza.domain.Order;
import pizza.repository.pizza.InMemPizzaRepository;
import pizza.service.simple.SimpleOrderService;

public class FourthPizzaDiscountTest {
	
	@Test
	public void getBiggestPricePizzaTest() {
		Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10"); //$NON-NLS-1$
		SimpleOrderService service = new SimpleOrderService();
		Order order = service.placeNewOrder(customer, 1, 1, 1, 1, 1);
		double expected = new InMemPizzaRepository().getPizzaByID(1).getPrice() * 30 / 100;
		double result = new FourthPizzaDiscount(order).getDiscount();
		assertEquals(expected, result, 0.001);
	}
	
	

}
