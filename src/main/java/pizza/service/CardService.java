package pizza.service;

import pizza.domain.AccumulativeCard;
import pizza.domain.customer.Customer;

public interface CardService {
	
	AccumulativeCard getCardByCustomer(Customer customer);

}
