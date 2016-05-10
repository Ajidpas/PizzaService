package pizza.domain.discounts;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pizza.domain.AccumulativeCard;
import pizza.domain.Discount;
import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Customer;
import pizza.domain.order.Order;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.orderservice.exceptions.NotSupportedPizzasNumberException;
import pizza.service.orderservice.exceptions.WrongStatusException;

public class AccumulativeCardDiscountTest {

	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	@Test
	public void testGetDiscount() throws NotSupportedPizzasNumberException, NoSuchPizzaException, WrongStatusException {
		Pizza pizza1 = new Pizza(1, "First pizza", 1000, PizzaType.MEAT);
		Pizza pizza2 = new Pizza(2, "Second pizza", 2000, PizzaType.SEA);
		Pizza pizza3 = new Pizza(3, "First pizza", 3000, PizzaType.VEGETABLES);
		Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
		pizzas.put(pizza1,  1);
		pizzas.put(pizza2, 1);
		pizzas.put(pizza3, 1);
		Order order = new Order();
		order.setCustomer(customer);
		order.setPizzas(pizzas);
		
		Discount discount = new AccumulativeCardDiscount(null, order);
		
		// accumulative card is null
		double expected = 0; 
		double result = discount.getDiscount();
		assertEquals(expected, result, 0.0001);
		
		// accumulative card exists but there are no money
		AccumulativeCard card = new AccumulativeCard(123);
		discount = new AccumulativeCardDiscount(card, order);
		expected = 0;
		result = discount.getDiscount();
		assertEquals(expected, result, 0.0001);
		
		// let's put money into the accumulative card less then 300% of the order price
		// 300% because 10% of the card money should be less then 30% of the order price
		double money = order.getTotalPrice() * 2; // 200%
		card.setMoney(money);
		expected = card.getMoney() / 10; // because this value is less then 30% of order price
		result = discount.getDiscount();
		assertEquals(expected, result, 0.0001);
		
		// let's put some money into the card that sum will be more then 300% of the order price
		money = order.getTotalPrice() * 5; // 500%
		card.addMoney(money);
		expected = order.getTotalPrice() *30 / 100; // because 30% of card money is bigger then 10% of order price
		result = discount.getDiscount();
		assertEquals(expected, result, 0.0001);
	}

}
