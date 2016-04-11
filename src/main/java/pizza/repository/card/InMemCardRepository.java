package pizza.repository.card;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pizza.domain.AccumulativeCard;
import pizza.repository.CardRepository;

@Repository(value = "cardRepository")
public class InMemCardRepository implements CardRepository {
	
	private List<AccumulativeCard> cards;
	
	public InMemCardRepository() {
		cards = new ArrayList<AccumulativeCard>();
	}
	
	public Long saveCard(AccumulativeCard card) {
		cards.add(card);
		return card.getId();
	}

	public List<AccumulativeCard> getAllCards() {
		return cards;
	}

}
