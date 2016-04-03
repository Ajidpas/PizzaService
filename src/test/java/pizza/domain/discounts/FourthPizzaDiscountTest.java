package pizza.domain.discounts;

import static org.junit.Assert.*;

import org.junit.Test;

import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.pizza.InMemPizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.simple.SimpleOrderService;

public class FourthPizzaDiscountTest {
	
	private static final Customer CUSTOMER = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10"); //$NON-NLS-1$
	
	@Test
	public void getBiggestPricePizzaTest() throws NoSuchPizzaException {
//		SimpleOrderService service = new SimpleOrderService();
//		Order order = service.placeNewOrder(CUSTOMER, 1, 1, 1);
//		
//		// add three pizzas
//		double expected = 0;
//		double result = new FourthPizzaDiscount(order).getDiscount();
//		assertEquals(expected, result, 0.001);
//		
//		// add fourth pizza
//		service.addPizzaIntoOrder(order, 1);
//		expected = new InMemPizzaRepository().getPizzaByID(1).getPrice() * 30 / 100;
//		result = new FourthPizzaDiscount(order).getDiscount();
//		assertEquals(expected, result, 0.001);
	}

}
