package pizza.service.cardservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizza.domain.AccumulativeCard;
import pizza.domain.customer.Customer;
import pizza.repository.Repository;
import pizza.service.CardService;

@Service(value = "cardService")
public class SimpleCardService implements CardService {
	
	@Autowired
	private Repository<AccumulativeCard> cardRepository;

	@Override
	public Optional<AccumulativeCard> getCardByCustomer(Customer customer) {
		List<AccumulativeCard> cards = cardRepository.getAll();
		for (AccumulativeCard card : cards) {
			if (card.getCustomer().getId() == customer.getId()) {
				return Optional.of(card);
			}
		}
		return Optional.empty();
	}
	
	@Override
	public List<AccumulativeCard> getAllCards() {
		return cardRepository.getAll();
	}

	@Override
	public AccumulativeCard insertCard(AccumulativeCard accumulativeCard) {
		return cardRepository.insert(accumulativeCard);
	}

	@Override
	public AccumulativeCard getCard(int id) {
		return cardRepository.get(id);
	}

	@Override
	public AccumulativeCard updateCard(AccumulativeCard accumulativeCard) {
		return cardRepository.update(accumulativeCard);
	}

	@Override
	public void deleteCard(int id) {
		cardRepository.delete(id);
	}

}
