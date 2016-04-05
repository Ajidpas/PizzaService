package pizza.repository;

import java.util.List;

import pizza.domain.AccumulativeCard;

public interface CardRepository {
	
	Long saveCard(AccumulativeCard card);
	
	List<AccumulativeCard> getAllCards();

}
