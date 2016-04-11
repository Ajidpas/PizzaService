package pizza.service.cardservice;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Optional;

import org.junit.Test;

import pizza.domain.AccumulativeCard;
import pizza.domain.customer.Customer;
import pizza.repository.CardRepository;
import pizza.repository.card.InMemCardRepository;
import pizza.service.CardService;

public class SimpleCardServiceTest {
	
	private Customer customer1 = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
	
	private Customer customer2 = new Customer(2, "Petya", "Kharkiv", "Petrova", "5", "20");
	
	@Test
	public void testGetCardByCustomer() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		AccumulativeCard card = new AccumulativeCard(1, customer1);
		CardRepository repository = new InMemCardRepository();
		
		// add into repository card with customer1
		repository.saveCard(card);
		
		// create service with this repository (setting repository by reflection)
		CardService service = new SimpleCardService();
		Field repositoryField = service.getClass().getDeclaredField("cardRepository");
		repositoryField.setAccessible(true);
		repositoryField.set(service, repository);

		// get card by customer1
		Optional<AccumulativeCard> expected = Optional.of(card);
		Optional<AccumulativeCard> result = service.getCardByCustomer(customer1);
		assertEquals(expected, result);
		
		// try to get card by customer2
		expected = Optional.empty();
		result = service.getCardByCustomer(customer2);
		assertEquals(expected, result);
	}

}
