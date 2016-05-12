package pizza.repository.pizza;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pizza.domain.Pizza;
import pizza.repository.AbstractRepository;

@Repository(value = "pizzaRepository")
@Transactional
public class JpaPizzaRepository extends AbstractRepository<Pizza> {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Pizza> getAll() {
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p", Pizza.class);
		return query.getResultList();
	}

	@Override
	public Pizza update(Pizza pizza) {
		Pizza oldPizza = em.find(Pizza.class, pizza.getId());
		oldPizza.setName(pizza.getName());
		oldPizza.setPrice(pizza.getPrice());
		oldPizza.setType(pizza.getType());
		em.flush();
		return pizza;
	}

}
