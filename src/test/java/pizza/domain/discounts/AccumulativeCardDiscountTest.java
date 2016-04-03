package pizza.domain.discounts;

import static org.junit.Assert.*;

import org.junit.Test;

import pizza.domain.Discount;
import pizza.domain.customer.AccumulativeCard;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.simple.SimpleOrderService;
import pizza.service.simple.exceptions.NotSupportedPizzasNumberException;
import pizza.service.simple.exceptions.WrongStatusException;

public class AccumulativeCardDiscountTest {
	
	private Discount discount = new AccumulativeCardDiscount();
	
	private SimpleOrderService service = new SimpleOrderService();
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	@Test
	public void testGetDiscount() throws NotSupportedPizzasNumberException, NoSuchPizzaException, WrongStatusException {
		Order order = service.placeNewOrder(customer, 1, 2, 3);
		
		// accumulative card is null
		double expected = 0; 
		double result = discount.getDiscount(order);
		assertEquals(expected, result, 0.0001);
		
		// accumulative card exists but there are no money
		AccumulativeCard card = new AccumulativeCard(123);
		customer.setAccumulativeCard(card);
		expected = 0;
		result = discount.getDiscount(order);
		assertEquals(expected, result, 0.0001);
		
		// let's put money into the accumulative card less then 300% of the order price
		// 300% because 10% of the card money should be less then 30% of the order price
		double money = order.getOrderPrice() * 2; // 200%
		card.setMoney(money);
		expected = card.getMoney() / 10; // because this value is less then 30% of order price
		result = discount.getDiscount(order);
		assertEquals(expected, result, 0.0001);
		
		// let's put some money into the card that sum will be more then 300% of the order price
		money = order.getOrderPrice() * 5; // 500%
		card.addMoney(money);
		expected = order.getOrderPrice() *30 / 100; // because 30% of card money is bigger then 10% of order price
		result = discount.getDiscount(order);
		assertEquals(expected, result, 0.0001);
	}

}
