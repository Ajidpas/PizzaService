package pizza.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import pizza.domain.Order.OrderStatus;
import pizza.service.simple.SimpleOrderService;

public class OrderTest {
	
	private Order order;
	
	private static final SimpleOrderService SERVICE = new SimpleOrderService();
	
	private static final Customer CUSTOMER = 
			new Customer(1, "Vasya", "Kiev", 
					"Chervonoarmiyska", "3", "10"); //$NON-NLS-1$
	
	@Test
	public void testDeletePizza() {
		order = SERVICE.placeNewOrder(CUSTOMER, 1);
		
		// try to delete pizza with wrong id
		boolean expected = false;
		boolean result = order.deletePizza(2);
		assertEquals(expected, result);
		
		// try to delete first pizza (existing)
		expected = true;
		result = order.deletePizza(1);
		assertEquals(expected, result);
		
		// try to delete second pizza (non existing)
		expected = false;
		result = order.deletePizza(1);
		assertEquals(expected, result);
	}
	
	@Test
	public void testNextStatus() {
		order = SERVICE.placeNewOrder(CUSTOMER, 1, 2, 3);
		OrderStatus expected = OrderStatus.NEW;
		OrderStatus result = order.getStatus();
		assertEquals(expected, result);
		
		expected = OrderStatus.IN_PROGRSS;
		order.nextStatus();
		result = order.getStatus();
		assertEquals(expected, result);
		
		expected = OrderStatus.DONE;
		order.nextStatus();
		result = order.getStatus();
		assertEquals(expected, result);
	}
	
	@Test
	public void testCancelOrder() {
		order = SERVICE.placeNewOrder(CUSTOMER, 1, 2, 3);
		OrderStatus expected = OrderStatus.NEW;
		boolean isCanceled = order.cancelOrder();
		OrderStatus result = order.getStatus();
		assertEquals(expected, result);
		assertEquals(false, isCanceled);
		
		expected = OrderStatus.DONE;
		order.nextStatus(); // set to IN_PROGRESS
		order.nextStatus(); // set to DONE
		isCanceled = order.cancelOrder();
		result = order.getStatus();
		assertEquals(expected, result);
		assertEquals(false, isCanceled);
		
		order = SERVICE.placeNewOrder(CUSTOMER, 1, 2, 3);
		order.nextStatus(); // set to IN_PROGRESS
		expected = OrderStatus.CANCELED;
		isCanceled = order.cancelOrder();
		result = order.getStatus();
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetAccumulativePrice() {
		CUSTOMER.setAccumulativeCard(null);
		order = SERVICE.placeNewOrder(CUSTOMER, 1, 2, 3);
		double expected = 0;
		double result = order.getAccumulativePrice();
		assert(expected == result);
		
		AccumulativeCard card = CUSTOMER.getAccumulativeCard();
		double money = 150;
		card.addMoney(money);
		expected = money;
		result = order.getAccumulativePrice();
		assertEquals(expected, result, 0.001);
	}

}
