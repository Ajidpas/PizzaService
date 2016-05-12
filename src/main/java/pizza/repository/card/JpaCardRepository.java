package pizza.repository.card;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pizza.domain.AccumulativeCard;
import pizza.repository.AbstractRepository;

@Repository(value = "cardRepository")
@Transactional
public class JpaCardRepository extends AbstractRepository<AccumulativeCard> {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<AccumulativeCard> getAll() {
		Query query = em.createQuery("SELECT c FROM AccumulativeCard c", 
				AccumulativeCard.class);
		return query.getResultList();
	}

	@Override
	public AccumulativeCard update(AccumulativeCard accumulativeCard) {
		AccumulativeCard oldCard = em.find(AccumulativeCard.class, accumulativeCard.getId());
		oldCard.setCustomer(accumulativeCard.getCustomer());
		oldCard.setMoney(accumulativeCard.getMoney());
		em.flush();
		return oldCard;
	}

}
