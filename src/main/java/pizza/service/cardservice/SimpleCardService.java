package pizza.service.cardservice;

import java.util.List;
import java.util.Optional;

import pizza.domain.AccumulativeCard;
import pizza.domain.customer.Customer;
import pizza.repository.CardRepository;
import pizza.service.CardService;

public class SimpleCardService implements CardService {
	
	private CardRepository cardRepository;
	
	public SimpleCardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	@Override
	public Optional<AccumulativeCard> getCardByCustomer(Customer customer) {
		List<AccumulativeCard> cards = cardRepository.getAllCards();
		for (AccumulativeCard card : cards) {
			if (card.getCustomer().getId() == customer.getId()) {
				return Optional.of(card);
			}
		}
		return Optional.empty();
	}

}
