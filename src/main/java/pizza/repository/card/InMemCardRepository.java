package pizza.repository.card;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pizza.domain.AccumulativeCard;
import pizza.repository.CardRepository;

//@Repository(value = "cardRepository")
public class InMemCardRepository implements CardRepository {
	
	private List<AccumulativeCard> cards;
	
	public InMemCardRepository() {
		cards = new ArrayList<AccumulativeCard>();
	}
	
	@Override
	public AccumulativeCard saveCard(AccumulativeCard card) {
		cards.add(card);
		return card;
	}

	public List<AccumulativeCard> getAllCards() {
		return cards;
	}

	@Override
	public AccumulativeCard getAccumulativeCard(int id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AccumulativeCard updateAccumulativeCard(AccumulativeCard accumulativeCard) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAccumulativeCard(int id) {
		throw new UnsupportedOperationException();
	}

}
