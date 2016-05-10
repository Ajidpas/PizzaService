package pizza.repository;

import pizza.domain.Pizza;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

public interface PizzaRepository {

	Pizza getPizzaByID(int id) throws NoSuchPizzaException;
	
	Pizza insertPizza(Pizza pizza);
	
	Pizza updatePizza(Pizza pizza);
	
	boolean deletePizza(int id);

}
