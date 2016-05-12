package pizza.service;

import java.util.List;
import java.util.Optional;

import pizza.domain.AccumulativeCard;
import pizza.domain.customer.Customer;

public interface CardService {
	
	List<AccumulativeCard> getAllCards();
	
	AccumulativeCard insertCard(AccumulativeCard accumulativeCard);
	
	AccumulativeCard getCard(int id);
	
	AccumulativeCard updateCard(AccumulativeCard accumulativeCard);
	
	void deleteCard(int id);

	Optional<AccumulativeCard> getCardByCustomer(Customer customer);

}
