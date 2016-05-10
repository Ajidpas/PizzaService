package pizza.repository.card;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pizza.domain.AccumulativeCard;
import pizza.repository.CardRepository;

@Repository(value = "cardRepository")
@Transactional
public class JpaCardRepository implements CardRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public JpaCardRepository() {}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccumulativeCard> getAllCards() {
		Query query = em.createQuery("SELECT cards FROM pizza_service_jpa.accumulative_card cards", 
				AccumulativeCard.class);
		return query.getResultList();
	}

	@Override
	public AccumulativeCard saveCard(AccumulativeCard card) {
		em.persist(card);
		return card;
	}

	@Override
	public AccumulativeCard getAccumulativeCard(int id) {
		return em.find(AccumulativeCard.class, id);
	}

	@Override
	public AccumulativeCard updateAccumulativeCard(AccumulativeCard accumulativeCard) {
		AccumulativeCard oldCard = em.find(AccumulativeCard.class, accumulativeCard.getId());
		oldCard.setCustomer(accumulativeCard.getCustomer());
		oldCard.setMoney(accumulativeCard.getMoney());
		return oldCard;
	}

	@Override
	public boolean deleteAccumulativeCard(int id) {
		AccumulativeCard card = em.find(AccumulativeCard.class, id);
		if (card == null) {
			return false;
		}
		em.remove(card);
		return true;
	}

}
