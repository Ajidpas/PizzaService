package pizza.service.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizza.domain.Pizza;
import pizza.repository.Repository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.PizzaService;

@Service
public class SimplePizzaService implements PizzaService {

	@Autowired
	private Repository<Pizza> pizzaRepository;

	@Override
	public List<Pizza> getAllPizzas() {
		return pizzaRepository.getAll();
	}

	@Override
	public Pizza insertPizza(Pizza pizza) {
		return pizzaRepository.insert(pizza);
	}

	@Override
	public Pizza updatePizza(Pizza newPizza) {
		return pizzaRepository.update(newPizza);
	}

	@Override
	public Pizza getPizza(int id) throws NoSuchPizzaException {
		return pizzaRepository.get(id);
	}

	@Override
	public void deletePizza(int id) {
		pizzaRepository.delete(id);
	}

}
