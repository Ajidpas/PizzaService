package pizza.service.cardservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizza.domain.AccumulativeCard;
import pizza.domain.customer.Customer;
import pizza.repository.CardRepository;
import pizza.service.CardService;

@Service(value = "cardService")
public class SimpleCardService implements CardService {
	
	@Autowired
	private CardRepository cardRepository;

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

	@Override
	public AccumulativeCard insertCard(AccumulativeCard accumulativeCard) {
		return cardRepository.saveCard(accumulativeCard);
	}

	@Override
	public AccumulativeCard getCard(int id) {
		return cardRepository.getAccumulativeCard(id);
	}

	@Override
	public AccumulativeCard updateCard(AccumulativeCard accumulativeCard, int id) {
		accumulativeCard.setId(id);
		return cardRepository.updateAccumulativeCard(accumulativeCard);
	}

	@Override
	public void deleteCard(int id) {
		cardRepository.deleteAccumulativeCard(id);
	}

}
