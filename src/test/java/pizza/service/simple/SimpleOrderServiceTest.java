package pizza.service.simple;

import static org.junit.Assert.*;

import org.junit.Test;

import pizza.domain.Customer;
import pizza.domain.Order;

public class SimpleOrderServiceTest {
	
	private static final Customer CUSTOMER = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10"); //$NON-NLS-1$
	
	@Test
	public void placeNewOrderTest() {
		SimpleOrderService service = new SimpleOrderService();
		Order expected = null;
		Order result = service.placeNewOrder(CUSTOMER, -1);
		assertEquals(expected, result);
	}

}
