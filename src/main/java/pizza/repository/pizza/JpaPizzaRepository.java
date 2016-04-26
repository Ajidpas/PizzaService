package pizza.repository.pizza;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import pizza.domain.Pizza;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

@Repository(value = "pizzaRepository")
public class JpaPizzaRepository implements PizzaRepository {
	
	private EntityManagerFactory emf;
	
	private EntityManager em;
	
	public JpaPizzaRepository() {}

	public JpaPizzaRepository(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public Pizza getPizzaByID(int id) throws NoSuchPizzaException {
		return em.find(Pizza.class, id);
	}

	@Override
	public Pizza insertPizza(Pizza pizza) {
		em.getTransaction().begin();
		em.persist(pizza);
		em.getTransaction().commit();
		return pizza;
	}

	@Override
	public Pizza updatePizza(Pizza pizza) {
		Pizza oldPizza = em.find(Pizza.class, pizza.getId());
		em.getTransaction().begin();
		oldPizza.setName(pizza.getName());
		oldPizza.setPrice(pizza.getPrice());
		oldPizza.setType(pizza.getType());
		em.getTransaction().commit();
		return pizza;
	}

	@Override
	public void deletePizza(int id) {
		Pizza pizza = em.find(Pizza.class, id);
		em.getTransaction().begin();
		em.remove(pizza);
		em.getTransaction().commit();
	}
	
	@PostConstruct
	private void initEntityManager() {
		emf = Persistence.createEntityManagerFactory("jpa");
		em = emf.createEntityManager();
	}
	
	@PreDestroy
	private void closeEntityManager() {
		em.close();
		emf.close();
	}

}
