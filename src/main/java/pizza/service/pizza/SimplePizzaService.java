package pizza.service.pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizza.domain.Pizza;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.PizzaService;

@Service
public class SimplePizzaService implements PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Override
	public Pizza insertPizza(Pizza pizza) {
		return pizzaRepository.insertPizza(pizza);
	}

	@Override
	public Pizza updatePizza(Pizza newPizza, int id) {
		newPizza.setId(id);
		return pizzaRepository.updatePizza(newPizza);
	}

	@Override
	public Pizza getPizza(int id) throws NoSuchPizzaException {
		return pizzaRepository.getPizzaByID(id);
	}

	@Override
	public void deletePizza(int id) {
		pizzaRepository.deletePizza(id);
	}

}
