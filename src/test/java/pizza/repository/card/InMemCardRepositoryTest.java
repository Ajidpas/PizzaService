package pizza.repository.card;

import static org.junit.Assert.*;

import org.junit.Test;

import pizza.domain.AccumulativeCard;
import pizza.repository.CardRepository;

public class InMemCardRepositoryTest {
	
	@Test
	public void testSaveCard() {
		AccumulativeCard card = new AccumulativeCard(1);
		CardRepository cardRepository = new InMemCardRepository();
		
		// check that repository is empty 
		int expected = 0;
		int result = cardRepository.getAllCards().size();
		assertEquals(expected, result);
		
		// add one card into the repository and check it
		cardRepository.saveCard(card);
		AccumulativeCard expectedCard = card;
		AccumulativeCard resultCard = cardRepository.getAllCards().get(0);
		assertEquals(expectedCard, resultCard);
	}

}
