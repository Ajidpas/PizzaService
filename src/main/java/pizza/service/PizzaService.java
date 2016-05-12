package pizza.service;

import java.util.List;

import pizza.domain.Pizza;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

public interface PizzaService {
	
	Pizza insertPizza(Pizza pizza);
	
	Pizza updatePizza(Pizza newPizza);
	
	Pizza getPizza(int id) throws NoSuchPizzaException;
	
	void deletePizza(int id);

	List<Pizza> getAllPizzas();

}
