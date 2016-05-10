package pizza.service;

import java.util.Optional;

import pizza.domain.AccumulativeCard;
import pizza.domain.customer.Customer;

public interface CardService {
	
	Optional<AccumulativeCard> getCardByCustomer(Customer customer);
	
	AccumulativeCard insertCard(AccumulativeCard accumulativeCard);
	
	AccumulativeCard getCard(int id);
	
	AccumulativeCard updateCard(AccumulativeCard accumulativeCard, int id);
	
	void deleteCard(int id);

}
