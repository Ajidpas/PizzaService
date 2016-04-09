package pizza.service;

import java.util.Optional;

import pizza.domain.AccumulativeCard;
import pizza.domain.customer.Customer;

public interface CardService {
	
	Optional<AccumulativeCard> getCardByCustomer(Customer customer);

}
