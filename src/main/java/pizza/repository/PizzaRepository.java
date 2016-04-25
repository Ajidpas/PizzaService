package pizza.repository;

import pizza.domain.Pizza;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

public interface PizzaRepository {

	Pizza getPizzaByID(int id) throws NoSuchPizzaException;

}
