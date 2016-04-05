package pizza.service.cardservice;

import java.util.List;

import pizza.domain.AccumulativeCard;
import pizza.domain.customer.Customer;
import pizza.repository.CardRepository;
import pizza.repository.card.InMemCardRepository;
import pizza.service.CardService;

public class SimpleCardService implements CardService {
	
	private CardRepository cardRepository;
	
	public SimpleCardService() {
		cardRepository = new InMemCardRepository();
	}

	public AccumulativeCard getCardByCustomer(Customer customer) {
		List<AccumulativeCard> cards = cardRepository.getAllCards();
		for (AccumulativeCard card : cards) {
			if (card.getCustomer().getId() == customer.getId()) {
				return card;
			}
		}
		return null;
	}

}
