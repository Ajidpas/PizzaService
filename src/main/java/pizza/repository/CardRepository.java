package pizza.repository;

import pizza.domain.AccumulativeCard;

public interface CardRepository {
	
	Long saveCard(AccumulativeCard card);

}
