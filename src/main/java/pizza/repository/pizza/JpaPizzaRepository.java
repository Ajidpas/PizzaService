package pizza.repository.pizza;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pizza.domain.Pizza;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

@Repository(value = "pizzaRepository")
@Transactional
public class JpaPizzaRepository implements PizzaRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public JpaPizzaRepository() {}

	@Override
	public Pizza getPizzaByID(int id) throws NoSuchPizzaException {
		Pizza pizza = em.find(Pizza.class, id);
		if (pizza == null) {
			throw new NoSuchPizzaException();
		}
		return pizza;
	}

	@Override
	public Pizza insertPizza(Pizza pizza) {
		em.persist(pizza);
		return pizza;
	}

	@Override
	public Pizza updatePizza(Pizza pizza) {
		Pizza oldPizza = em.find(Pizza.class, pizza.getId());
		oldPizza.setName(pizza.getName());
		oldPizza.setPrice(pizza.getPrice());
		oldPizza.setType(pizza.getType());
		return pizza;
	}

	@Override
	public boolean deletePizza(int id) {
		Pizza pizza = em.find(Pizza.class, id);
		if (pizza == null) {
			return false;
		}
		em.remove(pizza);
		return true;
	}

}
