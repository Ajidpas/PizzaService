package pizza.repository.card;

import java.util.ArrayList;
import java.util.List;


import pizza.domain.AccumulativeCard;
import pizza.repository.Repository;

//@Repository(value = "cardRepository")
public class InMemCardRepository implements Repository<AccumulativeCard> {
	
	private List<AccumulativeCard> cards;
	
	public InMemCardRepository() {
		cards = new ArrayList<AccumulativeCard>();
	}
	
	@Override
	public AccumulativeCard insert(AccumulativeCard card) {
		cards.add(card);
		return card;
	}

	public List<AccumulativeCard> getAllCards() {
		return cards;
	}

	@Override
	public AccumulativeCard get(int id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AccumulativeCard update(AccumulativeCard accumulativeCard) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(int id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<AccumulativeCard> getAll() {
		return cards;
	}

}
