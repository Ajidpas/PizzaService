package pizza.service;

import pizza.domain.Pizza;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

public interface PizzaService {
	
	Pizza insertPizza(Pizza pizza);
	
	Pizza updatePizza(Pizza newPizza, int id);
	
	Pizza getPizza(int id) throws NoSuchPizzaException;
	
	void deletePizza(int id);

}
