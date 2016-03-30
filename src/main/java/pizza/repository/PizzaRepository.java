package pizza.repository;

import pizza.domain.Pizza;

public interface PizzaRepository {
	
	Pizza getPizzaByID(int id);

}
