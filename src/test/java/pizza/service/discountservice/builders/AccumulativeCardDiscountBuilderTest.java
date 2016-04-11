package pizza.service.discountservice.builders;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;

import pizza.domain.AccumulativeCard;
import pizza.domain.Discount;
import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Customer;
import pizza.domain.discounts.AccumulativeCardDiscount;
import pizza.domain.order.Order;
import pizza.repository.CardRepository;
import pizza.repository.card.InMemCardRepository;
import pizza.service.CardService;
import pizza.service.cardservice.SimpleCardService;
import pizza.service.discountservice.DiscountBuilder;

public class AccumulativeCardDiscountBuilderTest {
	
	private Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	private Customer otherCustomer = new Customer(2, "Petya", "Kharkiv", "Petrova", "7", "3");
	
	@Test
	public void testBuildDiscount() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// create repository with one card with some customer
		CardRepository cardRepository = new InMemCardRepository();
		AccumulativeCard card = new AccumulativeCard(50, customer);
		cardRepository.saveCard(card);
		CardService cardService = new SimpleCardService();
		
		// setting repository by reflection
		Field repositoryField = cardService.getClass().getDeclaredField("cardRepository");
		repositoryField.setAccessible(true);
		repositoryField.set(cardService, cardRepository);
		
		DiscountBuilder discountBuilder = new AccumulativeCardDiscountBuilder(cardService);
		
		// create discount basis on the following order
		Pizza pizza = new Pizza(1, "Some pizza", 50.0, PizzaType.MEAT);
		Order order = new Order(customer, new ArrayList<>(Arrays.asList(pizza)));
		
		// get discount
		double expected = Optional.ofNullable(new AccumulativeCardDiscount(card, order)).get().getDiscount();
		double result = discountBuilder.buildDiscount(order).get().getDiscount();
		assertEquals(expected, result, 0.0001);
		
		// create order with other customer without card
		order = new Order(otherCustomer, new ArrayList<>(Arrays.asList(pizza)));
		Optional<Discount> expectedObject = Optional.empty();
		Optional<Discount> resultObject = discountBuilder.buildDiscount(order);
		assertEquals(expectedObject, resultObject);
	}

}
