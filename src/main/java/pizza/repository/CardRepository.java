package pizza.repository;

import java.util.List;

import pizza.domain.AccumulativeCard;

public interface CardRepository {
	
	List<AccumulativeCard> getAllCards();
	
	AccumulativeCard saveCard(AccumulativeCard card);
	
	AccumulativeCard getAccumulativeCard(int id);
	
	AccumulativeCard updateAccumulativeCard(AccumulativeCard accumulativeCard);
	
	boolean deleteAccumulativeCard(int id);

}
