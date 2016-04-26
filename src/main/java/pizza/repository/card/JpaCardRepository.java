package pizza.repository.card;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pizza.domain.AccumulativeCard;
import pizza.repository.CardRepository;

public class JpaCardRepository implements CardRepository {
	
	private EntityManager em;

	public JpaCardRepository(EntityManager em) {
		super();
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccumulativeCard> getAllCards() {
		Query query = em.createQuery("SELECT cards FROM pizza_service_jpa.accumulative_card cards", 
				AccumulativeCard.class);
		return query.getResultList();
	}

	@Override
	public AccumulativeCard saveCard(AccumulativeCard card) {
		em.getTransaction().begin();
		em.persist(card);
		em.getTransaction().commit();
		return card;
	}

	@Override
	public AccumulativeCard getAccumulativeCard(int id) {
		return em.find(AccumulativeCard.class, id);
	}

	@Override
	public AccumulativeCard updateAccumulativeCard(AccumulativeCard accumulativeCard) {
		AccumulativeCard oldCard = em.find(AccumulativeCard.class, accumulativeCard.getId());
		em.getTransaction().begin();
		oldCard.setCustomer(accumulativeCard.getCustomer());
		oldCard.setMoney(accumulativeCard.getMoney());
		em.getTransaction().commit();
		return oldCard;
	}

	@Override
	public void deleteAccumulativeCard(int id) {
		AccumulativeCard card = em.find(AccumulativeCard.class, id);
		em.getTransaction().begin();
		em.remove(card);
		em.getTransaction().commit();
	}

}
